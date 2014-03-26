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
	<div class="container-fluid">
		<form:form method="PUT" class="form-horizontal">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>用户信息</h5>
						</div>
						<div class="widget-content no-padding">
							<div class="control-group">
								<label class="control-label"> 用户名 :</label>
								<div class="controls">
									<input type="text" class="span11" placeholder="用户名" readonly="readonly"
										value="<c:url value="${user.username }"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">密码 :</label>
								<div class="controls">
									<input type="text" class="span11" placeholder="密码 " name="password" value="<c:url value="${user.password }"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">邮箱 :</label>
								<div class="controls">
									<input type="password" class="span11" placeholder="邮箱" name="email" value="<c:url value="${user.email }"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">状态 :</label>
								<div class="controls">
									<c:if test="${user.enabled eq true }">
										<c:set var="checked" value="checked='checked'" />
									</c:if>
									<label><input type="checkbox" name="enabled" ${checked } />启用</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>权限组</h5>
						</div>
						<div class="widget-content no-padding">
							<table class="table table-bordered groups-table">
								<thead>
									<tr>
										<th></th>
										<th>权限组</th>
										<th>说明</th>
									</tr>
								</thead>
								<c:forEach items="${groups}" var="group">
									<tbody>
										<tr>
											<c:set var="isGroupChecked" value="" />
											<c:forEach items="${user.groups }" var="uGroup">
												<c:if test="${uGroup.id == group.id}">
													<c:set var="isGroupChecked" value="checked=checked" />
												</c:if>
											</c:forEach>
											<td><input type="checkbox" name="group.id" value="<c:out value="${group.id }" />" ${isGroupChecked} /></td>
											<td><c:out value="${group.name }" /></td>
											<td>权限组说明</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>权限</h5>
						</div>
						<div class="widget-content no-padding">
							<table class="table table-bordered groups-table">
								<thead>
									<tr>
										<th></th>
										<th>权限</th>
										<th>说明</th>
									</tr>
								</thead>
								<c:forEach items="${authorities}" var="authority" varStatus="status">
									<tbody>
										<tr>
											<c:set var="isAuthorityChecked" value="" />
											<c:forEach items="${user.authorities }" var="uAuthority">
												<c:if test="${uAuthority.id == authority.id}">
													<c:set var="isAuthorityChecked" value="checked=checked" />
												</c:if>
											</c:forEach>
											<td><input type="checkbox" name="authority.id" value="${authority.id}" ${isAuthorityChecked} /></td>
											<td><c:out value="${authority.name }" /></td>
											<td>权限说明</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
							<div class="form-actions">
								<button type="submit" class="btn btn-success">全部更新</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<!-- 脚本  -->
		<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
		<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
		<script type="text/javascript">
			$('.groups-table .authoritie-table').dataTable({
				'bJQueryUI' : true,
				'sPaginationType' : "full_numbers",
				'sDom' : '<""l>t<"F"fp>',
				'bSort' : true,
				'bRetrieve' : true,
				'aaSorting' : [ [ 2, "desc" ] ],
			});

			$('select').select2();
		</script>
	</div>
</body>
</html>