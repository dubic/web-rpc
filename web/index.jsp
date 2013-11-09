<%-- 
    Document   : index
    Created on : Nov 3, 2013, 10:58:58 AM
    Author     : DUBIC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app="rpcApp">
    <head>
        <script src="scripts/jquery-1.7.1.js"></script>
        <script src="scripts/rpc-jquery.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <button onclick="call()">post rpc</button>
    </body>
    <script>
        function call(){
            var rpc = {
                className : "test.Test",
                method : "testService",
                parameters : []
            };
            alert(JSON.stringify(rpc));
            RPC.call("test.Test", "testService", rpc.parameters, function(data){
                alert("success - "+data);
            }, function(r){
                alert("error - "+JSON.stringify(r));
            })
        }
    </script>
</html>
