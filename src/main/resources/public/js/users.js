var app = angular.module("demo", []);

app.controller("WeaponryCtrl", function($scope, $http){
    var weaponryId;

    $scope.weaponry = [];
    var time = performance.now();
    $http.get('/api/weaponry/showall').then(function(response){
        time = performance.now() - time;
        window.alert("Виведення відбулося за " + time + " мс.");
        $scope.weaponry = response.data;
    });

    this.createWeaponry = function createWeaponry(){
        var name = document.getElementById('weaponryName').value;
        var caliber = document.getElementById('weaponryCaliber').value;
        var silencer = document.getElementById('hasSilencer').value;
        var type = document.getElementById('weaponryType').value;

        var request = {
            method: 'PUT',
            url : '/api/weaponry/insert',
            data: {
                name : name,
                caliber : caliber,
                silencer : silencer,
                weaponryType : type
            }
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.startUpdate = function startUpdate(id, name, caliber, silencer, type){
        weaponryId = id;
        document.getElementById('updateWeaponryName').value = name;
        document.getElementById('updateWeaponryCaliber').value = caliber;
        document.getElementById('updateWeaponrySilencer').value = silencer;
        document.getElementById('updateWeaponryType').value = type;
    };

    this.update = function update(){
        var weaponryName = document.getElementById('updateWeaponryName').value;
        var weaponryCaliber = document.getElementById('updateWeaponryCaliber').value;
        var weaponrySilencer = document.getElementById('updateWeaponrySilencer').value;
        var weaponryType = document.getElementById('updateWeaponryType').value;

        var request = {
            method: 'POST',
            url: '/api/weaponry/update?weaponryId=' + weaponryId,
            data: {
                name : weaponryName,
                caliber : weaponryCaliber,
                silencer : weaponrySilencer,
                weaponryType : weaponryType
            }
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.del = function del(weaponryId){
        var time = performance.now();
        $http.post('/api/weaponry/delete?id=' + weaponryId).then(function(response){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    }
});



