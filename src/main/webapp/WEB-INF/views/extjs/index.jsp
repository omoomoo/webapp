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
		new Ext.Viewport({
			title : 'Viewport',
			layout : 'border',
			items : [ {
				region : 'west',
				title : '侧边导航',
				width : 200,
				margin: '0 3 3 3',
				collapsible : true
			}, {
				region : 'north',
				html : '<div style="height:60px;background-image:url(<c:url value="/resources/extjs/customer/header_bg.gif"/>);background-repeat:repeat-x;">权限管理系统</div>',
				height : 60,
				border : false
			}, {
				region : 'center',
				title : '主体内容',
				margin: '0 0 3 0',
				split : true
			} ]
		});
	});
</script>
</head>
<body>
</body>
</html>