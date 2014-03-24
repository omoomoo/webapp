//  适用于android,cpu,内存,闪存的趋势统计
/**
 * @author tiger 趋势表格
 */
function drawTendencyTable(target, data) {
	// TODO 如果格式统计就不用那么蛋疼啦
	var $tbody = $('<tbody class="android-table-tbody">');
	var totalVisit = 0;
	if (data.dateArr != undefined) {
		for (var i = 0; i < data.series.length; i++) {
			totalVisit += (function(array) {
				var t = 0;
				for (var j = 0; j < array.data.length; j++) {
					t += array.data[j];
				}
				return t;
			})(data.series[i]);
		}

		data.series.forEach(function(item, index, array) {
			var total = 0;
			for (var k = 0; k < item.data.length; k++) {
				total += item.data[k];
			}

			var $tr = $('<tr/>');
			$tr.append(($('<td/>').text(item.name)));
			$tr.append(($('<td/>').text(total)));
			$tr.append(($('<td/>').text(Math.floor(total * 1000000 / totalVisit) / 10000 + '%')));
			$tr.append(($('<td/>').text(Math.floor(total / item.data.length))));

			$tbody.append($tr);
		});
	} else {

		var maxTimeLag = 0; // 最大时间间隔
		for (var i = 0; i < data.length; i++) {
			for (var j = 0; j < data[i].data.length; j++) {
				totalVisit += data[i].data[j][1];
			}
			var currentTimeLag = data[i].data[data[i].data.length - 1][0] - data[i].data[0][0];
			if (currentTimeLag > maxTimeLag) {
				maxTimeLag = currentTimeLag;
			}
		}

		var daysLag = maxTimeLag / (24 * 3600 * 1000) + 1;

		data.forEach(function(item, index, array) {
			var total = 0;
			for (var k = 0; k < item.data.length; k++) {
				total += item.data[k][1];
			}

			var $tr = $('<tr/>');
			$tr.append(($('<td/>').text(item.name)));
			$tr.append(($('<td/>').text(total)));
			$tr.append(($('<td/>').text(Math.floor(total * 1000000 / totalVisit) / 10000 + '%')));
			$tr.append(($('<td/>').text(Math.floor(total / daysLag))));

			$tbody.append($tr);
		});

	}

	var $table = $('<table class="table table-bordered android-table">');
	$table.append($('<thead><tr><th>类型</th><th>总访问</th><th>总占百分比</th><th class="center">平均</th></tr></thead>'));
	$table.append($tbody);

	$(target).html($table);

	$('.android-table').dataTable({
		'bJQueryUI' : true,
		'sPaginationType' : "full_numbers",
		'sDom' : '<""l>t<"F"fp>',
		'bSort' : true,
		'bRetrieve' : true,
		'aaSorting' : [ [ 1, "desc" ] ],
	});
	$('select').select2();
}

/**
 * 趋势线性图
 * 
 */
function drawTendencyLineChart(target, data, beginIndex, size) {
	// TODO 非常不优雅的实现！！判断格式类型, 日与周的数据格式是不一样滴
	var series = data;
	var xAxis = {
		type : 'datetime',
		dateTimeLabelFormats : { // don't display the dummy year
			hour : " ",
			day : '%m月%d日',
			month : '%m %b',
			year : '%Y'
		}
	};
	console.log(beginIndex);
	console.log(size);
	// 如果是【周】格式
	if (data.dateArr != undefined) {
		xAxis = {
			categories : data.dateArr
		};
		//  判断是否需要分页
		if(size != undefined) {
			series = data.series.slice(beginIndex, size);	
		} else {
			series = data.series;
			
		}
	} else {
		//  判断是否需要分页
		if(size != undefined) {
			series = data.slice(beginIndex, size);	
		}
	}
	
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
					text : '趋势统计'
				},
				subtitle : {
					text : 'Data From: www.allwinnertech.com'
				},

				xAxis : xAxis,
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