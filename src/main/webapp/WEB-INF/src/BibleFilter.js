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

  return module;
});