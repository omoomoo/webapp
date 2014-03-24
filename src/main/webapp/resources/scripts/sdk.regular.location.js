/** 绘制谷歌世界地图 **/
google.load('visualization', '1', {'packages': ['geochart']});
function drawGoogleWorldMap(data,type) {
	var chart = new google.visualization.GeoChart(document.getElementById('geo_map'));
	//设置属性
	var options = {
	    	colorAxis: {minValue: 0}
	    };
	var mapData = [['国家', type]].concat(data);
    var dataForGoogle = google.visualization.arrayToDataTable(mapData);
    
    //触发事件
    google.visualization.events.addListener(chart, 'regionClick', function(eventData) {
    	window.parent.type = 'detail';
    	window.parent.countryCode = eventData.region;
    	$(window.parent.document).find('.detail').show();
    	$(window.parent.document).find('.detail .active').click();
    	$(window.parent.document).find('.back-control').show();
    }); 
    
    //画出世界地图
    chart.draw(dataForGoogle, options);
}

/** 绘制谷歌省份或城市地图 **/
google.load('visualization', '1', {'packages': ['geochart']});
function drawGoogleAreaMap(title,data,countryCode){	
	var chart = new google.visualization.GeoChart(document.getElementById('geo_map'));
	
	//设置属性
	var detailOptions = {
			region: countryCode,
			displayMode: 'markers',
			colorAxis: {colors: ['green', 'blue']}
	};	
	if(title == '省份'){
		detailOptions.resolution = 'provinces';
	}
	var resultData = [[title,'访问量']].concat(data);
	detailDataForGoogle = google.visualization.arrayToDataTable(resultData);
	chart.draw(detailDataForGoogle, detailOptions);	
}


/**绘制饼状图**/
function drawPieCharts(target,title, dataList){
	$(target).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '访问量比例',
            data: dataList
        }]
    });
}

/**
 * 将国家或城市的访问数据以index为下标分割两部分
 * 第一部分为原始数据保持不变
 * 第二部分归类为Others
 * 
 **/
function splitArray(source, index){
	var beforeIndexArray = source.slice(0, index);
	var afterIndexArray  =  source.slice(index, source.length);
	
	// 将afterIndexArray整合为Others
	var sum = 0;
	for(var i=0; i< afterIndexArray.length;i ++) {
		sum += afterIndexArray[i][1];
	}
	beforeIndexArray.push(['Others', sum]);
	
	return [beforeIndexArray, afterIndexArray];
}