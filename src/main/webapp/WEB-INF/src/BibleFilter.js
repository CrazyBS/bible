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

  return module;
});