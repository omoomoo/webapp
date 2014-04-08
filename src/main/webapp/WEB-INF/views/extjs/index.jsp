<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/extjs/resources/css/ext-all.css"/>" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/extjs/ext-all.js"/>" type="text/javascript"></script>
<script>
	Ext.onReady(function() {
		Ext.create("Ext.panel.Panel", {
			title : "Fit layout panel",
			items : [ {
				xtype : "textfield",
				fieldLabel : "Email"
			} ],
			renderTo : Ext.getBody()
		});
	});
</script>
</head>
<body>
</body>
</html>