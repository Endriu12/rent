var app = angular.module("demo", []);

app.controller("DislocationCtrl", function($scope, $http){
    var idToUpdate;

    $scope.dislocation = [];
    var time = performance.now();
    $http.get('/api/dislocation/showall').then(function(response){
        $scope.dislocation = response.data;

        time = performance.now() - time;

        window.alert("Виведення відбулося за " + time + " мс.");
    });

    $http.get('/api/militarybase/showall').then(function(response){
        var local = [];

        local = response.data;

        var selectBaseQuery = document.getElementById('selectBaseQuery');
        var selectBaseUpdate = document.getElementById('updSelectBase');
        var selectBase = document.getElementById('selectBase');

        for (var k = 0; k < local.length; k++){
            var option3 = document.createElement("option");
            option3.text = local[k].name;
            option3.value = local[k].id;

            selectBase.add(option3);
            console.log(selectBase);
        }

        for (var p = 0; p < local.length; p++){
            var option4 = document.createElement("option");
            option4.text = local[p].name;
            option4.value = local[p].id;

            selectBaseQuery.add(option4);
            console.log(selectBaseQuery);
        }

        for (var i = 0; i < local.length; i++){
            var option5 = document.createElement("option");
            option5.text = local[i].name;
            option5.value = local[i].id;

            selectBaseUpdate.add(option5);
            console.log(selectBaseUpdate);
        }
    });

    $http.get('/api/buildings/showall').then(function(response){
        console.log(response);
        var buildings = [];
        buildings = response.data;

        var selectDislocation = document.getElementById('selectDislocation');
        var selectUpdDislocation = document.getElementById('updSelectDislocation');

        for (var k = 0; k < buildings.length; k++){
            var option3 = document.createElement("option");
            option3.text = buildings[k].name;
            option3.value = buildings[k].id;

            selectDislocation.add(option3);
            console.log(selectDislocation);
        }

        for (var p = 0; p < buildings.length; p++){
            var option2 = document.createElement("option");
            option2.text = buildings[p].name;
            option2.value = buildings[p].id;

            selectUpdDislocation.add(option2);
            console.log(selectUpdDislocation);
        }
    });

    $http.get('/api/army/showall').then(function(response) {
        console.log(response);
        var armies = response.data;
        var selectArmyQuery = document.getElementById('selectArmyQuery');

        for (var k = 0; k < armies.length; k++){
            var option3 = document.createElement("option");
            option3.text = armies[k].name;
            option3.value = armies[k].id;

            selectArmyQuery.add(option3);
            console.log(selectArmyQuery);
        }

    });

    $http.get('/api/division/showall').then(function(response) {
        console.log(response);
        var divisions = response.data;
        var selectDivisionQuery = document.getElementById('selectDivisionQuery');

        for (var k = 0; k < divisions.length; k++){
            var option3 = document.createElement("option");
            option3.text = divisions[k].name;
            option3.value = divisions[k].id;

            selectDivisionQuery.add(option3);
            console.log(selectDivisionQuery);
        }


    });

    $http.get('/api/corps/showall').then(function(response) {
        console.log(response);
        var corps = response.data;
        var selectCorpsQuery = document.getElementById('selectCorpsQuery');

        for (var k = 0; k < corps.length; k++){
            var option3 = document.createElement("option");
            option3.text = corps[k].name;
            option3.value = corps[k].id;

            selectCorpsQuery.add(option3);
            console.log(selectCorpsQuery);
        }

    });

    $http.get('/api/buildings/showall').then(function(response) {
        console.log(response);
        var buildings = response.data;
        var select = document.getElementById('selectDislocation');
        var selectBuildingsUpd = document.getElementById('updSelectDislocation');

        for (var i = 0; i < buildings.length; i++) {
            var option = document.createElement("option");
            option.text = buildings[i].name;
            option.value = buildings[i].id;

            select.add(option);

            console.log(select);
        }

        for (var j = 0; j < buildings.length; j++){
            var option2 = document.createElement("option");
            option2.text = buildings[j].name;
            option2.value = buildings[j].id;

            selectBuildingsUpd.add(option2);
            console.log(selectBuildingsUpd);
        }
    });



    this.create = function create(){
        var militaryBaseId = document.getElementById('selectBase').value;
        var dislocationId = document.getElementById('selectDislocation').value;

        var request = {
            method: 'PUT',
            url: '/api/dislocation/insert?baseId=' + militaryBaseId + '&dislocationId=' + dislocationId
        };

        var time = performance.now();
        $http(request).then(function(response){
            time = performance.now() - time;
            console.log("Створення відбулося за " + time + " мс.");
            console.log(response);
        });

        window.location.reload();
    };

        this.startUpdate = function startUpdate(id, baseId, dislocationId){
            idToUpdate = id;
            document.getElementById('updSelectBase').value = baseId;
            document.getElementById('updSelectDislocation').value = dislocationId;
        };

        this.update = function update(){
            var baseId = document.getElementById('updSelectBase').value;
            var dislocationId = document.getElementById('updSelectDislocation').value;

            var request = {
                method: 'POST',
                url: '/api/dislocation/update?id=' + idToUpdate + '&baseId=' + baseId + '&dislocationId=' + dislocationId
            };

            var time = performance.now();
            $http(request).then(function(response){
                time = performance.now() - time;
                console.log("Оновлення відбулося за " + time + " мс.");
                console.log(response);
            });

            window.location.reload();
        };

        this.del = function del(id){
            $http.post('/api/dislocation/delete?id=' + id);
            window.location.reload();
        };

        /**********
         * QUERIES
         *********/

        this.selectByBase = function selectByBase(){
            var id = $('#selectBaseQuery').val();

            $http.get('/api/dislocation/findAllByMilitaryBaseId?id=' + id).then(function (response){
                $scope.dislocation = response.data;
            });
        };

        this.selectByArmy = function selectByArmy(){
            var id = $('#selectArmyQuery').val();

            $http.get('/api/dislocation/findAllByMilitaryBaseArmyId?id=' + id).then(function (response){
                $scope.dislocation = response.data;
            });
        };

        this.selectByDivision = function selectByDivision(){
            var id = $('#selectDivisionQuery').val();

            $http.get('/api/dislocation/findAllByMilitaryBaseDivisionId?id=' + id).then(function (response){
                $scope.dislocation = response.data;
            });
        };

        this.selectByCorps = function selectByCorps(){
            var id = $('#selectCorpsQuery').val();

            $http.get('/api/dislocation/findAllByMilitaryBaseCorpsId?id=' + id).then(function(response){
                $scope.dislocation = response.data;
            });
        }
});



