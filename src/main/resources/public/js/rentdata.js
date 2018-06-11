var app = angular.module("demo", []);

app.controller("BrigadeCtrl", function($scope, $http){
    var idToUpdate;

    var time = performance.now();
    $scope.brigades = [];
     $http.get('/api/brigade/showall').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.brigades=response.data;
        console.log(response);
    });

    this.deleteBrigade = function deleteBrigade(id){
        var time = performance.now();
        $http.get('/api/brigade/delete?id=' + id).then(function(){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    this.createBrigade = function createBrigade(){
        var name = document.getElementById('brigadeName').value;
        var codeNumber = document.getElementById('brigadeCode').value;
        var createRequest = {
            method: 'PUT',
            url: '/api/brigade/create',
            data : {
                codeNumber : codeNumber,
                name : name
            }
        };

        var time = performance.now();
        $http(createRequest).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.startUpdateBrigade = function startUpdateBrigade(id, name, code){
        document.getElementById('updateBrigadeName').value = name;
        document.getElementById('updateBrigadeCode').value = code;
        idToUpdate = id;
    };

    this.updateBrigade = function updateBrigade(){
        var name = document.getElementById('updateBrigadeName').value;
        var codeNumber = document.getElementById('updateBrigadeCode').value;
        var request = {
            method: 'POST',
            url : '/api/brigade/update?id=' + idToUpdate,
            data: {
                codeNumber : codeNumber,
                name : name
            }
        };

        var time = performance.now();
        $http(request).then(function (response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.getBrigade = function getBrigade(id){
        $http.get('/api/brigade/get?id=' + id);
    }
});



