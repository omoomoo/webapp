<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SDK Analytics2.0</title>
<%@ include file="/WEB-INF/views/includes/headScriptsAndLinks.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>

	<div id="content">
		<!-- 书签导航 -->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="错误页面" class="tip-bottom"><i class="icon-home"></i>400</a>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-info-sign"></i>
							</span>
							<h5>Error 404</h5>
						</div>
						<div class="widget-content">
							<div class="error_ex">
								<h1>404</h1>
								<h3>Opps, You're lost.</h3>
								<p>We can not find the page you're looking for.</p>
								<a class="btn btn-warning btn-big" href="index.html">Back to Home</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- 脚本  -->
	<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
	<%@ include file="/WEB-INF/views/includes/footScriptsAndLinks.jspf"%>
</body>
</html>