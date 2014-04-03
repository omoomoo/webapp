<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<title><decorator:title /></title>
<decorator:head />
<style>
body {
	background-color: rgb(238, 238, 238);
}
#content-header {
	margin-top: 9px;
}
</style>
<body>
	<decorator:body />
	<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</body>
</html>