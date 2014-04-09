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
	Ext
			.onReady(function() {
				var headerHtml = '<div style="height:60px;background-image:url(<c:url value="/resources/extjs/customer/header_bg.gif"/>);background-repeat:repeat-x;">权限管理系统</div>';

				var treeStore = Ext.create('Ext.data.TreeStore', {
					root : {
						text : 'Webapp System',
						expanded : true,
						children : [ {
							text : '权限管理菜单',
							expanded : true,
							children : [ {
								text : '用户管理',
								leaf : true
							}, {
								text : '权限管理',
								leaf : true
							}, {
								text : '权限组管理',
								leaf : true
							} ]
						}, {
							text : '流程管理菜单',
							expanded : true,
							children : [ {
								text : '流程管理1',
								leaf : true
							}, {
								text : '流程管理2',
								leaf : true
							}, {
								text : '流程管理3',
								leaf : true
							} ]
						}, {
							text : '文档管理菜单',
							expanded : true,
							children : [ {
								text : '文档管理1',
								leaf : true
							}, {
								text : '文档管理2',
								leaf : true
							}, {
								text : '文档管理3',
								leaf : true
							} ]
						}, ]
					}
				});

				var contentTab = Ext.create('Ext.tab.Panel', {
					activeTab : 0,
					border : 0,
					defaults : {
						padding : '10',
						margin : '20',
						border : '0',
						frame : true
					},
					items : [ {
						title : 'Simple tab',
						html : 'This is a simple tab.'
					}, {
						title : 'I am closable',
						html : 'Please close when done reading.',
						closable : true
					}, {
						title : 'Disabled tab',
						itemId : 'disabledTab',
						html : 'Peekaboo!',
						closable : true
					}, {
						title : 'Simple tab',
						html : 'This is a simple tab.'
					}, {
						title : 'I am closable',
						html : 'Please close when done reading.',
						closable : true
					}, {
						title : 'Disabled tab',
						itemId : 'disabledTab',
						html : 'Peekaboo!',
						closable : true
					} ]
				});

				new Ext.Viewport({
					title : 'Viewport',
					layout : 'border',
					defaults : {
						
					},
					items : [ {
						region : 'west',
						title : '侧边导航',
						width : 200,
						margin : '0 3 3 3',
						collapsible : true,
						items : {
							xtype : 'treepanel',
							border : false,
							store : treeStore,
							rootVisible : false
						}
					}, {
						region : 'north',
						html : headerHtml,
						height : 60,
						border : false
					}, {
						region : 'center',
						margin : '0 0 3 0',
						split : true,
						items : contentTab
					} ]
				});

			});
</script>
</head>
<body>
</body>
</html>