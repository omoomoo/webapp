/**
 * 输出饼状图
 * @param targetId
 * @param series
 * @param topNum
 */
function DrawPieChart(targetId,series,topNum) {
    $('#'+targetId).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Top'+topNum
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.y}</b>'
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
        series: [series]
    });
}

/**
 * 输出横向二叠柱状图
 * @param targetId
 * @param data
 */
function DrawStackedBar(targetId, data){
	$('#'+targetId).highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Stacked bar chart'
        },
        xAxis: {
            categories: data.types
        },
        yAxis: {
            min: 0,
            title: {
                text: '设备数'
            }
        },
        legend: {
            backgroundColor: '#FFFFFF',
            reversed: true
        },
        plotOptions: {
            series: {
                stacking: 'normal'
            }
        },
            series: [{
            name: '不支持',
            data: data.falseList
        }, {
            name: '支持',
            data: data.trueList
        }]
    });
}

/**
 * 将数据以index为下标分割两部分 第一部分为原始数据保持不变 第二部分归类为Others
 * 二维数组格式：[[String,Number],[String,Number],...]
 * 
 */
function SplitArrayList(sortArrayList, index) {
	//可排序
//	var sortArrayList = arrayList.sort(function(x, y) {
//		return parseInt(y[1]) - parseInt(x[1]);
//	});
//
//	var array=new Array();
	if (index > sortArrayList.length) {
		index = sortArrayList.length;
		return sortArrayList;
	}
	var beforeIndexArray = sortArrayList.slice(0, index);
	var afterIndexArray = sortArrayList.slice(index, sortArrayList.length);

	// 将afterIndexArray整合为Others
	var sum = 0;
	for ( var i = 0; i < afterIndexArray.length; i++) {
		sum += afterIndexArray[i][1];
	}
	beforeIndexArray.push([ 'Others', sum ]);
//	array.push(beforeIndexArray);
//	array.push(afterIndexArray);
	return beforeIndexArray;
}