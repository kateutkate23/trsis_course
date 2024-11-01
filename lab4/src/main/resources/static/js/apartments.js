var app = angular.module('apartments', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
});

app.controller("ApartmentsController", function ($scope, $http) {

    $scope.successGetApartmentsCallback = function (response) {
        $scope.apartmentsList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetApartmentsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of apartments";
    };

    $scope.getApartments = function () {
        $http.get('/public/rest/apartments').then($scope.successGetApartmentsCallback, $scope.errorGetApartmentsCallback);
    };

    $scope.successDeleteApartmentCallback = function (response) {
        for (var i = 0; i < $scope.apartmentsList.length; i++) {
            var j = $scope.apartmentsList[i];
            if (j.id === $scope.deletedId) {
                $scope.apartmentsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete apartments; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteApartment = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/apartments/' + id).then($scope.successDeleteApartmentCallback, $scope.errorDeleteApartmentCallback);
    };


    $scope.successGetApartmentCallback = function (response) {
        $scope.apartmentsList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on apartment by name "+$scope.inputnumber;
    };

    $scope.successAddApartmentCallback = function (response) {
        $scope.apartmentsList.push(response.data);
        $scope.errormessage = "";
    };

    $scope.errorAddApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new apartment";
    };

    $scope.addApartment = function () {
        const apartmentsData = {
            "city": $scope.inputCity,
            "address": $scope.inputAddress,
            "price": $scope.inputPrice,
          };
        $http.post('/public/rest/apartments', apartmentsData)
            .then($scope.successAddApartmentCallback, $scope.errorAddApartmentCallback);    };

});
