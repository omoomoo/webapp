<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="decorator" content="${param._decorator}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>权限组</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<c:set var="method" value="POST" />
		<c:set var="readonly" value="false" />
		<c:if test="${group.id != 0 }">
			<c:set var="method" value="PUT" />
			<c:set var="readonly" value="true" />
		</c:if>
		<form:form action="${action }" method="${method }" class="form-horizontal" modelAttribute="group">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>权限组信息</h5>
						</div>
						<div class="widget-content no-padding">
							<div class="control-group">
								<label class="control-label">权限组 :</label>
								<div class="controls">
									<form:input path="name" readonly="${readonly }" cssClass="span11" placeholder="权限组" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">说明 :</label>
								<div class="controls">
									<input type="text" class="span11" placeholder="权限组说明 " name="password" value="" />
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
							<h5>用户列表</h5>
						</div>
						<div class="widget-content no-padding">
							<table class="table table-bordered groups-table">
								<thead>
									<tr>
										<th></th>
										<th>用户列表</th>
										<th>邮箱</th>
										<th>状态</th>
									</tr>
								</thead>
								<c:forEach items="${users}" var="user">
									<tbody>
										<tr>
											<c:set var="isUserChecked" value="" />
											<c:forEach items="${group.users }" var="gUser">
												<c:if test="${gUser.id == user.id}">
													<c:set var="isUserChecked" value="checked=checked" />
												</c:if>
											</c:forEach>
											<td><input type="checkbox" name="user.id" value="<c:out value="${user.id }" />" ${isUserChecked} /></td>
											<td><c:out value="${user.username }" /></td>
											<td><c:out value="${user.email }" /></td>
											<td><c:out value="${user.enabled }" /></td>
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
											<c:forEach items="${group.authorities }" var="gAuthority">
												<c:if test="${gAuthority.id == authority.id}">
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
								<!-- TODO 重复代码 -->
								<form:errors path="*" cssClass="alert alert-error alert-block" element="div" />
								<c:if test="${success != null}">
									<div class="alert alert-success alert-block">${success }</div>
								</c:if>
								<c:if test="${error != null}">
									<div class="alert alert-error alert-block">${error }</div>
								</c:if>
								<button type="submit" class="btn btn-success">全部更新</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<script type="text/javascript">
			window.onload = function() {
				$('.groups-table .authoritie-table').dataTable({
					'bJQueryUI' : true,
					'sPaginationType' : "full_numbers",
					'sDom' : '<""l>t<"F"fp>',
					'bSort' : true,
					'bRetrieve' : true,
					'aaSorting' : [ [ 2, "desc" ] ],
				});

				$('select').select2();

			};
		</script>
	</div>
</body>
<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</html>