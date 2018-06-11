var app = angular.module("demo", []);

app.controller("CorpsCtrl", function($scope, $http){
    var idToUpdate;

    var time = performance.now();
    $scope.corps = [];
     $http.get('/api/corps/showall').then(function (response){
         time = performance.now() - time;
         window.alert("Виведення відбулося за " + time + " мс.");
        $scope.corps=response.data;
        console.log(response);
    });

    this.deleteCorps = function deleteCorps(id){
        var time = performance.now();
        $http.get('/api/corps/delete?id=' + id).then(function(){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    this.createCorps = function createCorps(){
        var name = document.getElementById('corpsName').value;
        var codeNumber = document.getElementById('corpsCode').value;
        var createRequest = {
            method: 'PUT',
            url: '/api/corps/create',
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

    this.startUpdateCorps = function startUpdateCorps(id, name, code){
        document.getElementById('updateCorpsName').value = name;
        document.getElementById('updateCorpsCode').value = code;
        idToUpdate = id;
    };

    this.updateCorps = function updateCorps(){
        var name = document.getElementById('updateCorpsName').value;
        var codeNumber = document.getElementById('updateCorpsCode').value;
        var request = {
            method: 'POST',
            url : '/api/corps/update?id=' + idToUpdate,
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

    this.getCorps = function getCorps(id){
        $http.get('/api/corps/get?id=' + id);
    };

    this.selectByMaxBases = function selectByMaxBases(){
        $http.get('/api/corps/findByMaxBases').then(function(response){
            $scope.corps = response.data;
        });
    };

    this.selectByMinBases = function selectByMinBases(){
        $http.get('/api/corps/findByMinBases').then(function(response){
            $scope.corps = response.data;
        });
    };
});



