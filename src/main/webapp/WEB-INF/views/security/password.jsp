<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>个人信息</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>

	<div id="content">
		<!-- 书签导航 -->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="个人信息" class="tip-bottom"><i class="icon-home"></i>个人信息</a>
			</div>
		</div>

		<!-- 详细信息   -->
		<div class="container-fluid">
			<hr />
			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>修改密码</h5>
						</div>
						<div class="widget-content no-padding">
							<form:form action="" method="PUT" class="form-horizontal" modelAttribute="user">
								<div class="control-group">
									<label class="control-label">输入旧密码 :</label>
									<div class="controls">
										<input type="password" class="span11" placeholder="输入新密码" name="oldPassword" value="<c:out value="${oldPassword }"/>" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">输入新密码 :</label>
									<div class="controls">
										<form:password path="password" cssClass="span11" placeholder="输入新密码 " />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">确认新密码 :</label>
									<div class="controls">
										<input type="password" class="span11" placeholder="确认新密码" name="passwordConfirm" value="<c:out value="${newPassword }"/>" />
									</div>
								</div>
								<div class="form-actions">
									<form:errors path="*" cssClass="alert alert-error alert-block" element="div"/>
									<c:if test="${status eq 'error'}">
										<div class="alert alert-error alert-block">${message }</div>
									</c:if>
									<c:if test="${param.success != null}">
										<div class="alert alert-success alert-block">您的密码修改成功，请记住新密码！</div>
									</c:if>
									<button type="submit" class="btn btn-success">修改密码</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 脚本  -->
	<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
	<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</body>
</html>