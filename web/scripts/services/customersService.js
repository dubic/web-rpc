//This handles retrieving data and is used by controllers. 3 options (server, factory, provider) with 
//each doing the same thing just structuring the functions/data differently.
app.service('customersService', function($http) {
    this.getCustomers = function() {

        var promise = $http({
            method: 'GET', 
            url: 'ajs?action=findAll'
        }).success(function(data, status, headers, config) {
            customers = data;
            return customers;
        }).error(function(data, status, headers, config) {
            console.log("error" + status);
        });

        return promise;
    };

    this.insertCustomer = function(firstName, lastName, address, city) {
        
       var promise = $http({
            method: 'GET', 
            url: 'ajs',
            params : {
                action : 'insert',
                firstName : firstName,
                address : address,
                lastName : lastName,
                city : city
            }
        }).success(function(data, status, headers, config) {
            customers = data;
            return customers;
        }).error(function(data, status, headers, config) {
            console.log("error" + status);
        });

        return promise;        
        
        
//        var topID = customers.length + 1;
//        customers.push({
//            id: topID,
//            firstName: firstName,
//            lastName: lastName,
//            city: city
//        });
    };

    this.deleteCustomer = function(id) {
//        for (var i = customers.length - 1; i >= 0; i--) {
//            if (customers[i].id === id) {
//                customers.splice(i, 1);
//                break;
//            }
//        }

        var promise = $http({method: 'GET', url: 'ajs?action=delete&id=' + id}).success(function(data, status, headers, config) {
            customers = data;
            return customers;
        }).error(function(data, status, headers, config) {
            console.log("error" + status);
        });
        return promise;
    };

    this.getCustomer = function(id) {
        for (var i = 0; i < customers.length; i++) {
            if (customers[i].id === id) {
                return customers[i];
            }
        }
        return null;
    };
    var customers;


    function logObjectFields(objArray) {
        for (var i = 0; i < objArray.length; i++) {
            var obj = objArray[i];
            for (x in obj) {
                console.log(x + " ====== " + obj[x]);
            }
        }

    }



});