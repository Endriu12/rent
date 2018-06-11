var app = angular.module("demo", []);

app.controller("OrdinaryCtrl", function($scope, $http){
    var id;
    var militaryBaseId;
    var soldierName;
    var soldierAge;

    $scope.ordinary = [];
    var time = performance.now();
    $http.get('/api/ordinary/showall').then(function(response){
        time = performance.now() - time;
        window.alert("Виведення відбулося за " + time + " мс.");
        $scope.ordinary = response.data;

    });

    var soldiers = [];
    $http.get('/api/soldier/showall').then(function(response){
        soldiers = response.data;
        var select = document.getElementById('selectSoldier');
        var selectSoldierUpd = document.getElementById('updSelectSoldier');

        for (var i = 0; i < soldiers.length; i++) {
            var option = document.createElement("option");
            option.text = soldiers[i].name;
            option.value = soldiers[i].id;

            select.add(option);

            console.log(select);
        }

        for (var j = 0; j < soldiers.length; j++){
            var option2 = document.createElement("option");
            option2.text = soldiers[j].name;
            option2.value = soldiers[j].id;

            selectSoldierUpd.add(option2);

            console.log(selectSoldierUpd);
        }
    });

    $http.get('/api/militarybase/showall').then(function(response){
        var militaryBases = response.data;
        var select = document.getElementById('rangBaseQueryBase');

        for (var i = 0; i < militaryBases.length; i++){
            var option = document.createElement("option");
            option.text = militaryBases[i].name;
            option.value = militaryBases[i].id;

            select.add(option);
            console.log(select);
        }
    });

    $http.get('/api/division/showall').then(function(response){
        var divisions = response.data;
        var select = document.getElementById('rangDivisionQueryDivision');

        for (var i = 0; i < divisions.length; i++){
            var option = document.createElement("option");
            option.text = divisions[i].name;
            option.value = divisions[i].id;

            select.add(option);
            console.log(select);
        }
    });

    $http.get('/api/army/showall').then(function(response){
        var armys = response.data;
        var select = document.getElementById('rangArmyQueryArmy');

        for (var i = 0; i < armys.length; i++){
            var option = document.createElement("option");
            option.text = armys[i].name;
            option.value = armys[i].id;

            select.add(option);
            console.log(select);
        }
    });

    this.createOrdinary = function createOrdinary(){
        var soldierId = document.getElementById('selectSoldier').value;
        var rang = document.getElementById('selectRang').value;

        var request = {
            method: 'PUT',
            url : '/api/ordinary/insert?soldierId=' + soldierId + '&rang=' + rang
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Створення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.startUpdate = function startUpdate(idToUpdate, soldierId, rang){
        id = idToUpdate;
        document.getElementById('updSelectSoldier').value = soldierId;
        document.getElementById('updSelectRang').value = rang;

    };

    this.update = function update(){
        var soldierId = document.getElementById('updSelectSoldier').value;
        var rang = document.getElementById('updSelectRang').value;

        var request = {
            method: 'POST',
            url: '/api/ordinary/update?id=' + id + '&soldierId=' + soldierId + '&rang=' + rang
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            window.alert("Оновлення відбулося за " + time + " мс.");
            console.log(response);
            window.location.reload();
        });
    };

    this.del = function del(ordinaryId){
        var time = performance.now();
        $http.post('/api/ordinary/delete?id=' + ordinaryId).then(function(response){
            time = performance.now() - time;
            window.alert("Видалення відбулося за " + time + " мс.");
            window.location.reload();
        });
    };

    /**
     * QUERY
     */

    this.findByRang = function findByRang(){
        var rang = $('#selectRangQuery').val();

        $http.get('/api/ordinary/findAllByRang?rang=' + rang).then(function(response){
            $scope.ordinary = response.data;
        });

    };

    this.findByRangAndMilitaryBaseId = function findByRangAndMilitaryBaseId(){
        var rang = $('#rangBaseQueryRang').val();
        var id = $('#rangBaseQueryBase').val();

        $http.get('/api/ordinary/findAllByRangAndMilitaryBaseId?rang=' + rang + '&id=' + id).then(function(response){
            $scope.ordinary = response.data;
        });

    };

    this.findAllByRangAndDivisionId = function findAllByRangAndDivisionId(){
        var rang = $('#rangDivisionQueryRang').val();
        var id = $('#rangDivisionQueryDivision').val();

        $http.get('/api/ordinary/findAllByRangAndDivisionId?rang=' + rang + '&id=' + id).then(function(response){
            $scope.ordinary = response.data;
        });

    };

    this.findAllByRangAndArmyId = function findAllByRangAndArmyId(){
        var rang = $('#rangArmyQueryRang').val();
        var id = $('#rangArmyQueryArmy').val();

        $http.get('/api/ordinary/findAllByRangAndArmyId?rang=' + rang + '&id=' + id).then(function(response){
            $scope.ordinary = response.data;
        });

    }
});



