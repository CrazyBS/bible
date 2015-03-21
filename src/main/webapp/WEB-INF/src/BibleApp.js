/**
 * Created by CrazyBS on 3/13/2015.
 */
define(['angular','service','filter','controller'], function (angular) {
  var bibleApp = angular.module('bibleApp', ['BibleControllers','BibleServices','BibleFilters']);

  bibleApp.init = function () {
    //noinspection JSCheckFunctionSignatures
    angular.bootstrap(document, ['bibleApp']);
  };

  bibleApp.directive('focusMe', ['$timeout', '$parse',
      function focusMeProviderFunc($timeout, $parse) {
        return {
          link : function (scope, element, attrs) {
            var shouldFocus = $parse(attrs.focusMe);
            if(shouldFocus) {
              element[0].focus();
              scope.$parent.verse.focusMe = false;
            }
          }
        }
      }]);

  return bibleApp;
});


