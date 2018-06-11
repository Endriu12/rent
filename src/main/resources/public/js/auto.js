var app = angular.module("demo", []);

app.controller("AutoCtrl", function($scope, $http){
    var idToUpdate;
    var time = performance.now();
    $scope.autos = [];
     $http.get('/users').then(function (response){
        $scope.autos=response.data;
        console.log(response);
    });

    this.deleteAuto = function deleteAuto(id){
        $http.delete('/users/delete?id=' + id).then(function(){
            window.location.reload();
        });
    };

    this.createAuto = function createAuto(){
        var q = document.getElementById('autoName').value;
        var qw = document.getElementById('autoDescription').value;
        var w = document.getElementById('autoPrice').value;
        var e = document.getElementById('isAv').value;
        var r = document.getElementById('isD').value;
        var t = document.getElementById('autoPH').value;
        var y = document.getElementById('autoNC').value;
        var createRequest = {
            method: 'PUT',
            url: '/users',
            data : {
                name : q,
                company: y,
                description: qw,
                hours: w,
                isAvailable: e,
                isNew: r,
                model: t
            }
        };

        $http(createRequest).then(function(response){
            console.log(response);
            window.location.reload();
        });
    };

    this.startUpdateArmy = function startUpdateArmy(id, name, code){
        document.getElementById('updateArmyName').value = name;
        document.getElementById('updateArmyCode').value = code;
        idToUpdate = id;
    };

    this.updateArmy = function updateArmy(){
        var name = document.getElementById('updateArmyName').value;
        var codeNumber = document.getElementById('updateArmyCode').value;
        var request = {
            method: 'POST',
            url : '/api/army/update?id=' + idToUpdate,
            data: {
                codeNumber : codeNumber,
                name : name
            }
        };

        $http(request).then(function (response){
            window.location.reload();
        });
    };

    this.getAuto = function getAuto(id){
        $http.get('/users?id=' + id);
    };


});