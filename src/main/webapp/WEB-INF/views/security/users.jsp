<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SDK Analytics2.0</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>

	<div id="content">
		<!-- 书签导航 -->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="用户列表" class="tip-bottom"><i class="icon-home"></i>用户列表</a>
			</div>
		</div>

		<!-- what kind of table?  -->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>用户列表</h5>
						</div>
						<div class="widget-content nopadding">
							<table class="table table-bordered users-table">
								<thead>
									<tr>
										<th>ID</th>
										<th>用户名</th>
										<th>邮箱</th>
										<th>密码</th>
										<th>状态</th>
										<th>修改信息</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${users }" var="user">
										<tr>
											<td><c:out value="${user.id }" /></td>
											<td><c:out value="${user.username }" /></td>
											<td><c:out value="${user.email }" /></td>
											<td><c:out value="${fn:substring(user.password, 0, 4)}" />******</td>
											<td><c:choose>
													<c:when test="${user.enabled}">启用</c:when>
													<c:otherwise>禁用</c:otherwise>
												</c:choose></td>
											<td><span>修改信息</span></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 用户信息   -->
		<div class="container-fluid">
			<hr />
			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>用户管理</h5>
						</div>
						<div class="widget-content no-padding">
							<form action="#" method="get" class="form-horizontal">
								<div class="control-group">
									<label class="control-label"> 用户名 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="用户名" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">密码 :</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="密码 " />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">邮箱 :</label>
									<div class="controls">
										<input type="password" class="span11" placeholder="邮箱" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">状态 :</label>
									<div class="controls">
                						<label><input type="checkbox" name="radios" />启用</label>
              						</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-success">更新</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>权限组</h5>
						</div>
						<div class="widget-content no-padding">
							<form action="#" method="get" class="form-horizontal">
								<div class="control-group">
									<label class="control-label">状态 :</label>
									<div class="controls">
                						<label><input type="checkbox" name="radios" />启用</label>
              						</div>
								</div><div class="control-group">
									<label class="control-label">状态 :</label>
									<div class="controls">
                						<label><input type="checkbox" name="radios" />启用</label>
              						</div>
								</div><div class="control-group">
									<label class="control-label">状态 :</label>
									<div class="controls">
                						<label><input type="checkbox" name="radios" />启用</label>
              						</div>
								</div><div class="control-group">
									<label class="control-label">状态 :</label>
									<div class="controls">
                						<label><input type="checkbox" name="radios" />启用</label>
              						</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-success">更新</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>

	<!-- 脚本  -->
	<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
	<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
	<script type="text/javascript">
		$('.users-table').dataTable({
			'bJQueryUI' : true,
			'sPaginationType' : "full_numbers",
			'sDom' : '<""l>t<"F"fp>',
			'bSort' : true,
			'bRetrieve' : true,
			'aaSorting' : [ [ 2, "desc" ] ],
		});
		$('select').select2();
	</script>
</body>
</html>