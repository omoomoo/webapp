<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SDK Analytics2.0</title>
<%@ include file="/WEB-INF/views/includes/headScriptsAndLinks.jspf"%>
<link rel="stylesheet" href="<c:url value="/resources2/styles/sdk.print.css"/>" media="print" />
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/header.jspf"%>
	<%@ include file="/WEB-INF/views/includes/sub_nav.jspf"%>

	<div id="content">
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

	</div>

	<!-- 脚本  -->
	<%@ include file="/WEB-INF/views/includes/footer.jspf"%>
	<%@ include file="/WEB-INF/views/includes/footScriptsAndLinks.jspf"%>
	<script type="text/javascript" src="<c:url value="/resources2/scripts/sdk.overview.js"/>"></script>
	<script type="text/javascript">
		var url = '<c:url value="/main2/overview/generalStatistics.json"/>';
		$.get(url, {}, function(data) {
			// 昨天 
			$('.device-count').text(data.device_count);
			$('.yesterday-statistics td:eq(1)').text(data.ystd_adv);
			$('.yesterday-statistics td:eq(2)').text(
					Math.floor((data.ystd_adv / data.device_count * 10000)) / 100 + '%');
			$('.yesterday-statistics td:eq(3)').text(data.ystd_new);
			$('.yesterday-statistics td:eq(4)').text(
					Math.floor((data.yesd_new / data.device_count * 10000)) / 100 + '%');
			// 上周
			$('.last-week-statistics td:eq(1)').text(data.last_week_adv);
			$('.last-week-statistics td:eq(2)').text(
					Math.floor((data.last_week_adv / data.device_count * 10000)) / 100 + '%');
			$('.last-week-statistics td:eq(3)').text(data.last_week_new);
			$('.last-week-statistics td:eq(4)').text(
					Math.floor((data.last_week_new / data.device_count * 10000)) / 100 + '%');
			// 上月
			$('.last-month-statistics td:eq(1)').text(data.last_month_adv);
			$('.last-month-statistics td:eq(2)').text(
					Math.floor((data.month_adv / data.device_count * 10000)) / 100 + '%');
			$('.last-month-statistics td:eq(3)').text(data.last_month_new);
			$('.last-month-statistics td:eq(4)').text(
					Math.floor((data.last_month_new / data.device_count * 10000)) / 100 + '%');
		});

		var url = '<c:url value="/main2/overview/dailyTendency.json?startDate=${startDate}&endDate=${endDate}"/>';
		$.get(url, {}, function(data) {
			console.log(data);
			drawTendencyLineChart('.visit-tendency-line-chart', data);
		});
	</script>
</body>
</html>