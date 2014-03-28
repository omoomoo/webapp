<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<title>Softwinners</title>
<link rel="icon" href="<c:url value="/resources/images/large.ico"/>">
<style type="text/css">
body {
	color: #444;
}

#loginForm {
	margin: 200px auto;
	width: 350px;
	height: 250px;
}

#loginForm fieldset {
	padding: 35px 25px 20px;
	border: dashed 1px #2f6fab;
	border-radius: 3px;
	box-shadow: 3px 3px 6px 2px #999;
}

#loginForm .inputText {
	margin: 8px 0px;
	padding: 5px 7px;
	width: 270px;
	color: #444;
}

#loginForm .inputSubmit {
	float: right;
	padding: 5px 15px;
	cursor: pointer;
	letter-spacing: 5px;
}

#loginForm .msg {
	margin: 15px 0px 0px;
}
</style>
</head>
<body>
	<c:url var="loginUrl" value="/login" />
	<form:form action="${loginUrl}" method="POST" id="loginForm">
		<fieldset>
			<legend>
				<strong>登录系统</strong>
			</legend>
			<!-- 登陆表单 -->
			<label for="username">用户名 </label> <input type="text" name="username" id="username" class="inputText" style="margin-bottom: 15px;" /> <label
				for="password" style="margin-top: 5px;">密&nbsp;&nbsp;码 </label> <input type="password" name="password" id="password" class="inputText" /> <input
				type="submit" value="登录" class="inputSubmit" />
			<!-- 登陆验证信息 -->
			<c:if test="${param.error != null}">
				<div class="msg">
					Failed to login.
					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
              			Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION. 
              			message}" />
					</c:if>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="msg">You have been logged out.</div>
			</c:if>
		</fieldset>
	</form:form>
</body>
</html>