<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/extjs/resources/css/ext-all.css"/>" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/extjs/ext-all.js"/>" type="text/javascript"></script>
<script>
	Ext.onReady(function() {
		Ext.create('Ext.Panel', {
			title : 'Hello World Panel',
			items : [ Ext.create('Ext.form.field.Text', {
				fieldLabel : "Name",
				id : 'nametext'
			}), Ext.create('Ext.Button', {
				text : 'Cllick',
				handler : function() {
					Ext.Msg.alert(Ext.getCmp('nametext').getValue());
				}
			}) ],
			renderTo : Ext.getBody()
		});
	});
</script>
</head>
<body>
</body>
</html>