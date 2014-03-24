/**
 * 趋势线性图
 * 
 */
function drawTendencyLineChart(target, series) {
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(target).highcharts(
			{
				chart : {
					type : 'spline'
				},
				title : {
					text : ''
				},
				subtitle : {
					text : 'Data From: www.allwinnertech.com'
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : {
						hour : " ",
						day : '%m月%d日',
						month : '%m %b',
						year : '%Y'
					}
				},
				yAxis : {
					title : {
						text : '访问量(次)'
					},
					min : 0
				},
				tooltip : {
					formatter : function() {
						return '<b>类型：' + this.series.name + '</b><br/>' + Highcharts.dateFormat('%e. %b', this.x)
								+ ': ' + this.y + '次';
					}
				},

				series : series
			});
}