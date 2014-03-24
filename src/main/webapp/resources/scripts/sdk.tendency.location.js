/**
 * 请求数据，生成国家趋势统计图表 
 * 
 * @urlForTable 生成国家统计表格的请求路径
 * @urlForChart 生成国家趋势图的请求路径
 * @urlForPage  生成分页后的绑定路径
 * @urlForDetail  跳转到省份和城市的路径
 * 
 **/
function createCountryTableAndChart(urlForTable, urlForChart, urlForDetail, requestData){
	$.get(urlForTable,requestData,function(data){
		drawCountryTendencyTable(data, urlForDetail);
		drawCountryTendencyChart(urlForChart, requestData);
	});
	
}

/**
 * 画国家趋势统计表 
 * 
 **/
function drawCountryTendencyTable(data, urlForDetail){

	var days = getDaysBetween($('#startDate').val(), $('#endDate').val());
	var totalCount = 0;
	var averageCount = 0;
	
	//求和
	for(var i in data){
		totalCount += data[i].result;
		var average = Math.round(data[i].result/days);
		data[i].average = average;
		averageCount += average;
	}
	
	//画表
	var table_dom = $('<table/>').addClass('table').addClass('table-bordered').addClass('data-table');
	
	var thead_dom = $('<thead/>');
	var th_dom = $('<tr/>');
	th_dom.append($('<th/>').text('国家'));
	th_dom.append($('<th/>').text('平均量'));
	th_dom.append($('<th/>').text('平均量所占比重'));
	th_dom.append($('<th/>').text('总计'));
	th_dom.append($('<th/>').text('总计所占比重'));
	th_dom.append($('<th/>').text('操作'));
	thead_dom.append(th_dom);
	table_dom.append(thead_dom);
	
	var tbody_dom = $('<tbody/>');
	for(var j in data){
		var tr_dom = $('<tr/>').addClass('gradeA');
		tr_dom.append($('<td/>').text(data[j]['cr_n']));
		tr_dom.append($('<td/>').text(data[j].average));
		tr_dom.append($('<td/>').text(Math.round(data[j].average/averageCount * 10000)/100.00+'%'));
		tr_dom.append($('<td/>').text(data[j].result));
		tr_dom.append($('<td/>').text(Math.round(data[j].result/totalCount * 10000)/100.00+'%'));
		tr_dom.append($('<td/>').append($('<i/>').addClass('icon-link').attr('style','margin-left:10px;')).append($('<a/>').attr('href', urlForDetail+'&countryName='+data[j]['cr_n']).attr('target','_blank').attr('style','margin-left:3px;').text('查看省份和城市')));
		
		tbody_dom.append(tr_dom);
	}
	table_dom.append(tbody_dom);
	$('.country-tendency-table').html(table_dom);
	$('.data-table').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""l>t<"F"fp>',
		"aaSorting": [[3,'desc']]
	});
	$('select').select2();
}

/**
 * 画国家趋势统计图
 * 
 * */
function drawCountryTendencyChart(url, requestData){
	$.get(url,requestData,function(data){
		getHighChart(data,'国家','country-tendency-chart');
		//调用分页插件
		$.fn.dataPage({
			pageType: 1,
			data: data,
			chartTitle: '国家',
			domClass: 'country-tendency-chart'
		  }		
		);
	});
}

/**
 * 画城市或省份趋势统计图
 * 
 * */
function drawDetailsTendencyChart(url, title, requestData){
	
	$.get(url, requestData, function(data){
		getHighChart(data, title, 'country-detail-tendency-chart');
		//调用分页插件
		$.fn.dataPage({
			pageType: 1,
			data: data,
			chartTitle: title,
			domClass: 'country-detail-tendency-chart'
		  }		
		);
		
	});
}

/**
 * 计算两个日期相差的天数,日期格式为yyyy-MM-dd
 * 
 */
function getDaysBetween(startDate, endDate) {
    var milliseconds = Date.parse(endDate) - Date.parse(startDate);
    var days = milliseconds / 86400000;
    return days + 1;
}


/**
 * 使用HighCharts图表 
 * 
 **/
function getHighChart(data, title, domClass){
	var series = [];
	var xAxis = {
			type : 'datetime',
			dateTimeLabelFormats : { // don't display the dummy year
				hour : " ",
				day : '%m月%d日',
				month : '%m %b',
				year : '%Y'
			}
		};
	
	if(data.dateArr!=undefined) {
		xAxis = {
			categories : data.dateArr	
		};
		series = data.series.slice(0, 10);
		tooltip = {
				valueSuffix: '次'
			};
	} else {
		series = data.slice(0, 10);
		tooltip = {
				formatter : function() {
					return '<b>'+title+'：' + this.series.name + '</b><br/>' + Highcharts.dateFormat('%e. %b', this.x)
								+ ': ' + this.y + '次';
				}
			};
	}
	
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	
	$('.'+domClass).highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: title+'访问趋势统计'
        },
        subtitle: {
            text: '来源：www.softwinners.com'
        },
        xAxis: xAxis,
        yAxis: {
            title: {
                text: '访问量 (次)'
            },
            min: 0
        },
        tooltip: tooltip,
        
        series: series
    });
}
