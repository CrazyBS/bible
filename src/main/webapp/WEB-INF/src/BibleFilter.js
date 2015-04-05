/**
 * Created by CrazyBS on 3/13/2015.
 */
define(['angular','lodash'], function angular(angular,_) {

  var module = angular.module('BibleFilters', []);

  module.filter('groupBy',
      function chapterFilterProvider () {
        return _.memoize(
            function groupByFunc (collection, property) {
              return _.groupBy(collection, property);
            },
            function cacheKey (collection, property) {
              return "obj:" + angular.toJson(collection) + ",prop:" + property;
            });
      });

  module.filter('sortBy',
      function sortByProvider () {
        return _.memoize(
            function sortByFunc (collection, property) {
              return _.sortBy(collection, function (verseSet) {
                return verseSet[0][property];
              })
            },
            function cacheKeyFunc (collection, property) {
              return "obj:" + angular.toJson(collection) + ",prop:" + property;
            }
        )
      });

  module.filter('max',
      function maxFilterProvider () {
        return _.memoize(
            function maxFunc (collection, property) {
              return _.max(collection, property);
            },
            function maxCacheKey (collection, property) {
              return "obj:" + angular.toJson(collection) + ",prop:" + property;
            }
        )
      });

  module.filter('renderAsHtml',['$sce',
      function renderAsHtmlProvider($sce) {
        return function (input) {
          if(typeof input === 'object') {
            return $sce.trustAsHtml($sce.getTrustedHtml(input)); //.replace(/\n/g, "<br>"));
          } else {
            return $sce.trustAsHtml(input); //.replace(/\n/g, "<br>"));
          }
        }
      }]);

  module.filter('renderLORD', ['$sce',
      function renderLORDProvider($sce) {
        return function (input) {
          if(typeof input === 'object') {
            return $sce.trustAsHtml($sce.getTrustedHtml(input).replace(/LORD/g, "<span style=\"font-variant: small-caps; font-size:small;\">Lord</span>"));
          } else {
            return $sce.trustAsHtml(input.replace(/LORD/g, "<span style=\"font-variant: small-caps; font-size: small;\">Lord</span>"))
          }
        }
      }]);

  module.filter('withFormatting', ['$sce',
      function withFormattingProvider($sce) {
        /**
         * Consumes verses and formatting and outputs an array of formatted input blocks.
         *
         * ASSUMPTION: Verses and Formatting are both from the same chapter!
         *
         * @param {[{version:int,book:string,chapter:int,verse:int,text:string,properties:string}]} verses
         * @param {[{version:int,book:string,chapter:int,start:string,end:string,properties:string}]} format
         * @returns {[string]}
         */
        var func = function (verses, format) {
          var lastVerse = verses.length - 1;
          var lastFormat = format.length - 1;
          var finalOutput = [];

          var getVerseIdentifier = function (indentifier) {
            return indentifier.substring(0, indentifier.indexOf("."));
          };

          var getLineIdentifier = function (indentifer) {
            return indentifer.substring(indentifer.indexOf("."));
          };

          var getStringByLine = function (input, left, right) {
            var words = input.split("\n");
            return words.slice(left - 1, right - 1).join("\n");
          };

          var getIdentifierOrder = function (n) {
            var verse = getVerseIdentifier(n.start);
            var line = getLineIdentifier(n.start);
            return line ? parseFloat(verse + "." + _.padLeft(line, 2, "0")) : _.parseInt(verse);
          };

          var sortedVerses = _.sortBy(verses, 'verse');
          var sortedFormats = _.sortBy(format, getIdentifierOrder);

          // Apply formatting to the verses
          // Formatting will span over multiple verses
          // or multiple formats will be applied to a single verse
          // The final output will be a formatted string array
          do {
            var curFormat = sortedFormats[lastFormat];

            // Loop through verses until the curFormat end identifier is found
            while(sortedVerses[lastVerse].verse != getVerseIdentifier(curFormat.end) || !lastVerse) {
              --lastVerse;
            }

            // We have the verse this format is referring to (or we ran out...)

          } while(lastFormat-- && lastVerse);

          return finalOutput;
        };

        return func;
      }]);

  return module;
});