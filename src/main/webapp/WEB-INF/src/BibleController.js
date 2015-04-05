/**
 * Created by CrazyBS on 3/13/2015.
 */
define(['angular','angular-debounce','lodash', 'angular-elastic'], function (angular, debounce, _) {

  var module = angular.module('BibleControllers', ['BibleServices','rt.debounce','monospaced.elastic']);

  module.controller("BibleController", ['$scope','BibleService',
    function bibleControllerFunc ($scope, bibleService) {
      $scope.bible = [];

      $scope.getVerses = function () {
        bibleService.getBibleVerses().success(
            function setBibleVerses (data) {
              $scope.bible = data.content;
              $scope.versionSelection = _.uniq(_.pluck($scope.bible, 'version'), true)[0];
              $scope.bookSelection = _.uniq(_.pluck(_.filter($scope.bible, {version: $scope.versionSelection}), 'book'), true)[0];
              $scope.chapterSelection = 1;
            }
        );
      };

      $scope.getVerses();

      $scope.newVerse = function (verse) {
        $scope.bible.push({
          version : verse.version,
          book : verse.book,
          chapter : verse.chapter,
          verse: _.max(_.filter($scope.bible, {version:verse.version,book:verse.book,chapter:verse.chapter}), 'verse').verse + 1,
          text : "",
          header : null,
          focusMe : true
        });
        return true;
      };

      $scope.setChapter = function (chapter) {
        $scope.chapterSelection = chapter;
      };

      $scope.addChapter = function (chapter) {
        var newChapter = _.max(_.filter($scope.bible, {version:$scope.versionSelection, book: $scope.bookSelection }), 'chapter').chapter + 1;
        $scope.bible.push({
          version : $scope.versionSelection,
          book : $scope.bookSelection,
          chapter : newChapter,
          verse: 1,
          text : "",
          header : null,
          focusMe : true
        });
        $scope.chapterSelection = newChapter;
        return true;
      };

      $scope.headerSorter = function (verseArray) {
        console.log("verseArray", verseArray);
        return verseArray[0].verse;
      };
    }
  ]);

  module.controller("VerseController", ['$scope', 'BibleService', 'debounce','$sce',
      function verseControlFunc ($scope, bibleService, debounce, $sce) {
        $scope.$watch('verse.text',
            debounce(1000,
                function verseWatcher (newValue, oldValue, scope) {
                  if(newValue !== oldValue) {
                    console.log("Updated value: " + newValue, scope.$parent.verse); //, scope.$parent.verse);

                    if(newValue) {
                      // Save new value
                      bibleService.saveBibleVerse($scope.$parent.verse).success(
                          function successfullySavedVerseFunc () {
                            // update verse to indicate saved state
                            console.log("save complete");
                          }
                      );
                    } else {
                      // delete verse
                      bibleService.deleteBibleVerse($scope.$parent.verse).success(
                          function successfullyDeletedBibleVerse () {
                            console.log("delete successful");
                            _.remove($scope.$parent.$parent.bible, {version: $scope.$parent.verse.version, book: $scope.$parent.verse.book, chapter: $scope.$parent.verse.chapter, verse: $scope.$parent.verse.verse});
                          }
                      );
                    }
                  }
                }), true);
      }]);

  return module;
});