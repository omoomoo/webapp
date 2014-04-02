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
<link rel="stylesheet" href="<c:url value="/resources2/styles/sdk.print.css"/>" media="print" />
</head>
<body>
	<!-- 书签导航 -->
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="概况统计" class="tip-bottom"><i class="icon-home"></i> 概览</a>
		</div>
	</div>

	<!-- what kind of table?  -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>
							总设备数 ：<span style="color: red; font-weight: 700; font-size: 200%;" class="device-count"></span>（台）
						</h5>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th></th>
									<th>活跃设备</th>
									<th>活跃率</th>
									<th>新增设备</th>
									<th>增长率</th>
								</tr>
							</thead>
							<tbody>
								<tr class="odd gradeX yesterday-statistics">
									<td class="center">昨天</td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
								</tr>
								<tr class="even gradeC last-week-statistics">
									<td class="center">上周</td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
								</tr>
								<tr class="odd gradeA last-month-statistics">
									<td class="center">上月</td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
									<td class="center"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 访问趋势  -->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>访问趋势</h5>
					</div>
					<div class="widget-content" style="min-height: 300px;">
						<div class="visit-tendency-line-chart"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		window.onload = function() {
			var url = '<c:url value="/main/overview/generalStatistics.json"/>';
			$.get(url, {}, function(data) {
			});
		};
	</script>
</body>
<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</html>