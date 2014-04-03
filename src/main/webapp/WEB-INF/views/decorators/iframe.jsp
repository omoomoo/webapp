<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><decorator:title default="权限管理" /></title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>
	<div id="content" style="overflow: hidden">
		<iframe id="iframe" name="iframe" frameborder="0" style="overflow: hidden; height: 100%; width: 100%" height="100%"
			width="100%" onload='javascript:resizeIframe(this);'> </iframe>
	</div>
	<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
	<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
	<script type="text/javascript">
		$('#sidebar a').click(function(e) {
			var url = $(this).attr('href');
			if (url[0] == undefined || url[0] == '#') {
				console.log('url is: ' + url);
				return;
			}

			e.preventDefault();
			url += '?_decorator=none';

			$('#iframe')[0].src = url;
		});
		function resizeIframe(obj) {
			obj.style.height = obj.contentWindow.document.body.scrollHeight
					+ 'px';
		}
	</script>
</body>
</html>