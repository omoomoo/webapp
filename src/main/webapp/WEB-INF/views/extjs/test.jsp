<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/extjs/resources/css/ext-all-gray.css"/>" rel="stylesheet" />
<script src="<c:url value="/resources/extjs/ext-all.js"/>" type="text/javascript"></script>
<style type="text/css">
</style>
<script>
	Ext.onReady(function() {
		Ext.define("User", {
			extend : "Ext.data.Model",
			fields : [ 'username', 'password', 'email', 'enabled' ]
		});

		var userStore = Ext.create('Ext.data.Store', {
			model : 'User',
			proxy : {
				type : "ajax",
				api : {
					create : '<c:url value="/security/user"/>',
					read : '<c:url value="/security/users"/>',
					update : '<c:url value="/security/users"/>',
					destroy : '<c:url value="/security/users"/>'
				},
				extraParams : {
					_format : 'json',
					_csrf : '${_csrf.token}'
				},
				reader : {
					type : "json",
					root : "users"
				}
			}
		});

		console.log(userStore.load());

		//userStore.getAt(1).set("username", "Kingdom of Denmark");
		userStore.sync(); //invokes proxy's update method

		userStore.add({
			username : "Portugadl",
			password : 'Lisbon@qq.com',
			email : "Lisbon@qq.com"
		});
		userStore.sync();
	});
</script>
</head>
<body>
</body>
</html>