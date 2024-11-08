var app = angular.module('apartments', []);

app.controller("ApartmentsController", function ($scope, $http) {

    $scope.successGetApartmentsCallback = function (response) {
        $scope.apartmentsList = response.data;
        $scope.errormessage = "";
    };

    $scope.errorGetApartmentsCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Не удалось получить список квартир";
    };

    $scope.getApartments = function () {
        $http.get('/public/rest/apartments').then($scope.successGetApartmentsCallback, $scope.errorGetApartmentsCallback);
    };

    $scope.successDeleteApartmentCallback = function (response) {
        $http.get('/public/rest/apartments').then($scope.successGetApartmentsCallback, $scope.errorGetApartmentsCallback);
                $scope.errormessage = "";
    };

    $scope.errorDeleteApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Не удалось удалить квартиру; проверьте, существуют ли связанные записи.";
    };

    $scope.deleteApartment = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/apartments/' + id).then($scope.successDeleteApartmentCallback, $scope.errorDeleteApartmentCallback);
    };

    $scope.successGetApartmentCallback = function (response) {
        $scope.apartmentsList.splice(0, 0, response.data);
        $scope.errormessage = "";
    };

    $scope.errorGetApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Не удалось получить информацию о квартире с ID " + $scope.inputID;
    };

    $scope.successAddApartmentCallback = function (response) {
        $http.get('/public/rest/apartments').then($scope.successGetApartmentCallback, $scope.errorGetApartmentsCallback);
        $scope.errormessage = "";
    };

    $scope.errorAddApartmentCallback = function (error) {
        console.log(error);
        $scope.errormessage = "Невозможно добавить новую квартиру; проверьте уникальность.";
    };

    $scope.addApartment = function () {
        $http.post('/public/rest/apartments/' + $scope.inputcity + "/" + $scope.inputaddress + "/" + $scope.inputprice)
        .then($scope.successAddApartmentCallback, $scope.errorAddApartmentCallback)
        .then(() => location.reload())


    };

});

