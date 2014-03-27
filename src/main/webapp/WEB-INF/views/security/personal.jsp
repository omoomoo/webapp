<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>个人中心</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>

	<div id="content">
		<!-- 书签导航 -->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="个人信息" class="tip-bottom"><i class="icon-home"></i>个人中心</a>
			</div>
		</div>
		<!-- 详细信息   -->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>个人信息</h5>
						</div>
						<div class="widget-content no-padding">
							<c:url var="url" value="/security/personal" />
							<form:form action="${url }" method="PUT" class="form-horizontal">
								<div class="control-group">
									<label class="control-label"> 用户名 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="用户名" name="username" value="<c:out value="${user.username}"/>"
											readonly="readonly" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">密码 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="密码 " name="password" value="<c:out value="${user.password}"/>" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">邮箱 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="邮箱" name="email" value="<c:out value="${user.email}"/>" />
									</div>
								</div>
								<div class="form-actions">
									<c:if test="${param.success != nul}">
										<div class="alert alert-success alert-block">
											<a class="close" data-dismiss="alert" href="#">×</a> <span>您的信息已成功更新！</span>
										</div>
									</c:if>
									<button type="submit" class="btn btn-success">更新</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>所在权限组</h5>
						</div>
						<div class="widget-content no-padding">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>权限组</th>
										<th>说明</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${user.groups }" var="group">
										<tr>
											<td><c:out value="${group.name}" /></td>
											<td><c:out value="${group.name}" />-权限说明</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>拥有权限</h5>
						</div>
						<div class="widget-content no-padding">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>权限</th>
										<th>说明</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${user.authorities }" var="authority">
										<tr>
											<td><c:out value="${authority.name}" /></td>
											<td><c:out value="${authority.name}" />-权限说明</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
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