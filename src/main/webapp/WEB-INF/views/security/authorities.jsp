<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="decorator" content="${param._decorator}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>权限列表</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="权限列表" class="tip-bottom"><i class="icon-home"></i>权限列表</a>
		</div>
	</div>

	<!-- what kind of table?  -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>权限列表</h5>
						<span class="btn icon-plus-sign add-user-trigger" style="float: right; margin-top: 7px; margin-right: 8px;">添加权限</span>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered authorities-table">
							<thead>
								<tr>
									<th>ID</th>
									<th>权限</th>
									<th>说明</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${authorities }" var="authority">
									<tr>
										<td><c:out value="${authority.id }" /></td>
										<td><c:out value="${authority.name }" /></td>
										<td><c:out value="${authority.name }" />-说明</td>
										<td><span class="query-authority-trigger">修改信息</span>&nbsp;&nbsp;<span class="delete-authority-trigger">删除</span></td>
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
			$('.authorities-table').dataTable({
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
</body>
<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</html>