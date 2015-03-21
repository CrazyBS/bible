/**
 * Created by CrazyBS on 3/13/2015.
 */
define(['angular'],function (angular) {

  var module = angular.module('BibleServices', []);

  module.service("BibleService", ['$http',
    function bibleServiceFunc ($http) {
      /**
       *
       *
       * @param {string} [version]
       * @param {string} [book]
       * @param {number} [chapter]
       * @returns {Promise}
       */
      var getBibleVerses = function (version, book, chapter) {
        var request;

        if(version && book && chapter) {
          request = $http.get('/data/' + version + '/book/' + chapter);
        } else if (version && book) {
          request = $http.get('/data/' + version + '/book)');
        } else if (version) {
          request = $http.get('/data/' + version);
        } else {
          request = $http.get('/data/');
        }

        return request;
      };

      var saveBibleVerse = function (verse) {
        return $http.put('/data', {
          version : verse.version,
          book : verse.book,
          chapter : verse.chapter,
          verse : verse.verse,
          text : verse.text
        });
      };

      var deleteBibleVerse = function (verse) {
        return $http.delete('/data/' + verse.version + '/' + verse.book + '/' + verse.chapter + '.' + verse.verse);
      };

      return {
        getBibleVerses : getBibleVerses,
        saveBibleVerse : saveBibleVerse,
        deleteBibleVerse : deleteBibleVerse
      }
    }
  ]);

  return module;
});