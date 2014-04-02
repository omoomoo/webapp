<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="decorator" content="${param._decorator}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SDK Analytics2.0</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="权限组列表" class="tip-bottom"><i class="icon-home"></i>权限组列表</a>
		</div>
	</div>

	<!-- what kind of table?  -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>权限组列表</h5>
						<span class="btn icon-plus-sign add-group-trigger" style="float: right; margin-top: 7px; margin-right: 8px;">添加权限组</span>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered groups-table">
							<thead>
								<tr>
									<th>ID</th>
									<th>权限组</th>
									<th>说明</th>
									<th>修改信息</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groups }" var="group">
									<tr>
										<td><c:out value="${group.id }" /></td>
										<td><c:out value="${group.name }" /></td>
										<td><c:out value="${group.name }" />-说明</td>
										<td><span class="query-group-trigger" data-user-url="<c:url value="/security/group/${group.id }" />">修改信息</span> &nbsp;&nbsp; <span
											class="delete-group-trigger" data-user-url="<c:url value="/security/group/${group.id }" />">删除</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		window.onload = function() {

			// 重复代码
			$('.groups-table').dataTable({
				'bJQueryUI' : true,
				'sPaginationType' : "full_numbers",
				'sDom' : '<""l>t<"F"fp>',
				'bSort' : true,
				'bRetrieve' : true,
				'aaSorting' : [ [ 0, "asc" ] ],
			});
			$('select').select2();

			$('.query-group-trigger').click(function() {
				var url = $(this).attr('data-user-url') + '?_decorator=none';
				window.openWindow(url, '权限组信息');
			});

			$('.add-group-trigger').click(function() {
				window.openWindow('<c:url value="/security/group"/>_decorator=none', '权限组信息');
			});

			//TODO 重复代码
			$('.delete-group-trigger').click(function() {
				var url = $(this).attr('data-user-url') + '_decorator=none';

				if (!window.confirm('你去要删除该用户吗 ?')) {
					return;
				}

				$.post(url, {
					'_format' : 'json',
					'_method' : 'DELETE',
					'${_csrf.parameterName}' : '${_csrf.token}',
				}, function(data) {
					window.alert(data.success || data.error);
					window.location.reload();
				});

			});
		};
	</script>
</body>
<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</html>