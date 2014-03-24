/**
 * 
 */
function requestMemoryCharData(url, requstParam) {
	$.get(url, requstParam, function(data) {
		var series = [];
		var nameTodatemap = {};

		var dateArray = [];
		var keyMap = {};
		var keyArray = [];
		var dateKeyTovalueMap = {};

		for ( var i in data) {
			var entry = data[i];
			for ( var date in entry) {
				dateArray.push(date);
				var item = entry[date];
				for ( var key in item) {
					// 存key
					//var kStr = key.toString();
					if (!(keyMap[key])) {
						keyMap[key] = key;
						keyArray.push(key);
					}

					var value = item[key];
					var nKey = date + key;
					dateKeyTovalueMap[nKey] = value;
				}
			}
		}
		
		keyArray.sort();
		
		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
			var from = key * 256;
			var to = from + 256; 
			var name = from + "-" + to;
			nameTodatemap[name] = [];
		}

		for ( var index in dateArray) {
			var date = dateArray[index];
			for ( var kIndex in keyArray) {
				var key = keyArray[kIndex];
				var nKey = date + key;
				var value = 0;
				if (dateKeyTovalueMap[nKey]) {
					value = dateKeyTovalueMap[nKey];
				}
				var from = key * 256;
				var to = from + 256; 
				var name = from + "-" + to;
				var valueArray = nameTodatemap[name];
				valueArray.push(value);
			}
		}

		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
			var from = key * 256;
			var to = from + 256; 
			var name = from + "-" + to;
			var entry = {};
			entry['name'] = name;
			entry['data'] = nameTodatemap[name];
			series.push(entry);
		}

		drawChart(dateArray, series);

	});

}



function requestNandCharData(url, requstParam){
	function ascSort(a,b){
		return a-b;
	}  
	$.get(url, requstParam, function(data) {
		var series = [];
		var nameTodatemap = {};

		var dateArray = [];
		var keyMap = {};
		var keyArray = [];
		var dateKeyTovalueMap = {};

		for ( var i in data) {
			var entry = data[i];
			for ( var date in entry) {
				dateArray.push(date);
				var item = entry[date];
				for ( var key in item) {
					// 存key
					//var kStr = key.toString();
					if (!(keyMap[key])) {
						keyMap[key] = key;
						keyArray.push(key);
					}

					var value = item[key];
					var nKey = date + key;
					dateKeyTovalueMap[nKey] = value;
				}
			}
		}
		
		keyArray.sort(ascSort);
		
		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
		    console.log("key : "+key);
			var name = String(key);
			nameTodatemap[name] = [];
		}

		for ( var index in dateArray) {
			var date = dateArray[index];
			for ( var kIndex in keyArray) {
				var key = keyArray[kIndex];
				var nKey = date + key;
				var value = 0;
				if (dateKeyTovalueMap[nKey]) {
					value = dateKeyTovalueMap[nKey];
				}
				var name = String(key);
				var valueArray = nameTodatemap[name];
				valueArray.push(value);
			}
		}

		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
			var nameIndex = String(key);
			var name = null;
			var intKey = parseInt(key);
			if(intKey >= 0){
			   var start = Math.pow(2,intKey);
			   var end = Math.pow(2,(intKey+1));
			   name = start+"-"+end+"G";
			}else{
				name = '512MB-1G';
			}
			var entry = {};
			entry['name'] = name;
			entry['data'] = nameTodatemap[nameIndex];
			series.push(entry);
		}

		drawChart(dateArray, series);

	});
}



function requestAndroidVersionCharData(url, requstParam) {
	$.get(url, requstParam, function(data) {
		var series = [];
		var nameTodatemap = {};

		var dateArray = [];
		var keyMap = {};
		var keyArray = [];
		var dateKeyTovalueMap = {};

		for ( var i in data) {
			var entry = data[i];
			for ( var date in entry) {
				dateArray.push(date);
				var item = entry[date];
				for ( var key in item) {
					// 存key
					//var kStr = key.toString();
					if (!(keyMap[key])) {
						keyMap[key] = key;
						keyArray.push(key);
					}

					var value = item[key];
					var nKey = date + key;
					dateKeyTovalueMap[nKey] = value;
				}
			}
		}
		
		keyArray.sort();
		
		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
			var name = String(key);
			nameTodatemap[name] = [];
		}

		for ( var index in dateArray) {
			var date = dateArray[index];
			for ( var kIndex in keyArray) {
				var key = keyArray[kIndex];
				var nKey = date + key;
				var value = 0;
				if (dateKeyTovalueMap[nKey]) {
					value = dateKeyTovalueMap[nKey];
				}
				
				var name = String(key);
				var valueArray = nameTodatemap[name];
				valueArray.push(value);
			}
		}

		for ( var kIndex in keyArray) {
			var key = keyArray[kIndex];
			var name = String(key);
			var entry = {};
			entry['name'] = name;
			entry['data'] = nameTodatemap[name];
			series.push(entry);
		}

		drawChart(dateArray, series);

	});

}












function drawChart(categoryArr, serieArr) {
	$(function() {
		$('.memory-tendency-chart').highcharts(
				{
					chart : {
						type : 'line'
					},
					title : {
						text : ''
					},
					subtitle : {
						text : 'Source: allwinnertech.com'
					},
					xAxis : {
						categories : categoryArr
					// ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug',
					// 'Sep', 'Oct', 'Nov', 'Dec']
					},
					yAxis : {
						title : {
							text : ''
						}
					},
					tooltip : {
						enabled : false,
						formatter : function() {
							return '<b>' + this.series.name + '</b><br/>'
									+ this.x + ': ' + this.y ;
						}
					},
					plotOptions : {
						line : {
							dataLabels : {
								enabled : true
							},
							enableMouseTracking : false
						}
					},
					series : serieArr
				/*
				 * [{ name: 'Tokyo', data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5,
				 * 25.2, 26.5, 23.3, 18.3, 13.9, 9.6] }, { name: 'London', data:
				 * [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6,
				 * 4.8] }]
				 */
				});
	});
}