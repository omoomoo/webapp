<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/extjs/resources/css/ext-all.css"/>" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/extjs/ext-all.js"/>" type="text/javascript"></script>
<script>
	Ext.onReady(function() {
		new Ext.Viewport({
			title : 'Viewport',
			layout : 'border',
			defaults : {
				bodyStyle : 'background-color: #FFFFFF;',
				frame : true
			},
			items : [ {
				region : 'west',
				title : ' 侧边导航',
				width : 200,
				collapsible : true
			}, {
				region : 'north',
				html:'<h2>权限管理系统</h1>',
				height : 60,
				border: false
			}, {
				region : 'center',
				title : '主体内容',
				split : true,
				border : true
			} ]
		});
	});
</script>
</head>
<body>
</body>
</html>