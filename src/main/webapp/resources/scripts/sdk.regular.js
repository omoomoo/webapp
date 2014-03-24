//页面输出饼图及其表格：
function OutputPage(data, type) {
	var topSize=10;
	if(data[0].detail.length>topSize)
		topSize=20;
	AddTypeName(data, type);
	var series0 = {
		type : "pie",
		name : "访问量",
		data : PackChartsData(data, 0)
	};
	DrawPieChart("tv", series0, topSize);

	var series1 = {
		type : "pie",
		name : "活跃设备数",
		data : PackChartsData(data, 1)
	};
	DrawPieChart("adv", series1, topSize);

	var series2 = {
		type : "pie",
		name : "活跃IP数",
		data : PackChartsData(data, 2)
	};
	DrawPieChart("aiv", series2, topSize);

	$('.tvTable').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"sDom" : '<""l>t<"F"fp>',
		"bDestroy" : true,
		"aaData" : PackTableData(data, 0),
		"aaSorting" : [ [ 2, 'desc' ] ]
	});

	$('.advTable').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"sDom" : '<""l>t<"F"fp>',
		"bDestroy" : true,
		"aaData" : PackTableData(data, 1),
		"aaSorting" : [ [ 2, 'desc' ] ]
	});

	$('.aivTable').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"sDom" : '<""l>t<"F"fp>',
		"bDestroy" : true,
		"aaData" : PackTableData(data, 2),
		"aaSorting" : [ [ 2, 'desc' ] ]
	});

}

// 为图表添加日、周、月信息
function AddTypeName(data, type) {
	var name = "";
	if (type == "daily") {
		name = "日( " + $("#date").val() + " )";
	} else {
		name = "周( " + new Date(data[0].svd).format("yyyy.MM.dd") + "--"
				+ new Date(data[0].evd).format("yyyy.MM.dd") + " )";
	}
	$(".type_name").text(name);
}

// 封装饼图数据-----typeIndex:0,"tv"; 1,"adv"; 2,"aiv"
function PackChartsData(data, typeIndex) {
	var subData = data[0].detail;
	var list = new Array();
	for ( var i = 0; i < subData.length; i++) {
		list.push([ subData[i].name, subData[i].arr[typeIndex] ]);
	}
	var sortList = list.sort(function(x, y) {
		return parseInt(y[1]) - parseInt(x[1]);
	});
	return SplitArrayList(sortList, 20);
}

// 封装表格数据-----typeIndex:0,"tv"; 1,"adv"; 2,"aiv"
function PackTableData(data, typeIndex) {
	var subData = data[0].detail;
	var list = new Array();
	var total = 0;
	for ( var i = 0; i < subData.length; i++) {
		list.push([ subData[i].name, subData[i].arr[typeIndex] ]);
		total += subData[i].arr[typeIndex];
	}
	var sortList = list.sort(function(x, y) {
		return parseInt(y[1]) - parseInt(x[1]);
	});
	var resultList = new Array();
	for ( var j = 0; j < sortList.length; j++) {
		resultList.push([ (j + 1), sortList[j][0], sortList[j][1],
				(sortList[j][1] / total * 100).toFixed(3) + " %" ]);
	}
	return resultList;
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};