var pokeApp = angular.module('pokedex', ['ngResource']);

// With this you can inject POKEAPI url wherever you want
pokeApp.constant('POKEAPI', 'http://pokeapi.co');

pokeApp.config(['$resourceProvider', function ($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);



pokeApp.controller("pokedexsearch", function ($scope, $log, $http, factoryPoke, $rootScope) {
    //permet d'obtenir la liste de tout les pokemons
    $http({
        method: 'GET',
        url: 'http://pokeapi.co/api/v1/pokedex/1'
    }).then(function successCallback(response) {
        $scope.listpoke = response.data.pokemon;

    }, function errorCallback(response) {
        console.log("fail " + response)

    });


    $scope.$log = $log;
    //permet d'afficher la partie de droite seulement apres une recherche
    $rootScope.recherche = false;


    // quand on clique sur le bouton recherche change les informations afficher à l'ecran par celle du pokemon recherché
    $scope.get = function () {
        var pokemon = factoryPoke.get({ id: $scope.nom });
        pokemon.$promise.then(function (data) {
            console.log(data);
            $scope.nombis = data.name;
            $scope.id = data.id;
            $scope.atk = data.moves;
            $scope.image_avant = data.sprites.front_default;
            $scope.image_arriere = data.sprites.back_default;
            $rootScope.recherche = true;



        }, function (reason) {
            console.log("Raté" + reason);
        });
    }
}
);


pokeApp.factory("factoryPoke", function ($resource) {


    return $resource("http://pokeapi.co/api/v2/pokemon/:id/");
});



pokeApp.directive("pokedex", function () {
    return {

        link: function (scope, element, attrs) {
            scope.getContentUrl = function () {
                return '/TP_POKESIR/pokedex.html';
            }
        },
        template: '<div ng-include="getContentUrl()"></div>'
    };
});