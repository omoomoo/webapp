/**
 * @author tyjuncai
 * @date  2014-03-03
 * @description 根据趋势统计图所需要统计项目的个数，制作的分页插件  
 * @param pageType: 1表示数据在前端分页， 2表示数据在后台分页。  
 *        当pageType为1时需要传入data，为2时需要传入pageCount
 *        当pageType为2时需要传入url, pageCount和requestData
 * 
 * */

(function ($){
  $.fn.dataPage = function (option){
	  
	  //初始化
	  //解除绑定事件
	  $('.last-enable').die('click');
	  $('.first-enable').die('click');
	  $('.next-enable').die('click');
	  $('.previous-enable').die('click');
	  $('.chart-page span a').die('click');

	  //初始化DOM结点
	  $('.chart-page').html('');
	  $('.chart-page').append($('<a/>').addClass('first').addClass('ui-state-disabled').text('First'));
	  $('.chart-page').append($('<a/>').addClass('previous').addClass('ui-state-disabled').text('Previous'));
	  $('.chart-page').append($('<span/>'));
	  $('.chart-page').append($('<a/>').addClass('next').addClass('ui-state-disabled').text('Next'));
	  $('.chart-page').append($('<a/>').addClass('last').addClass('ui-state-disabled').text('Last'));
	  
	  //开始生成分页
	  creatPageDom(option);      
  };
})(jQuery);


/**
 * 生成分页dom结点
 * 
 * 
 * */
function creatPageDom(option){
	var series = option.data;
	if(option.data.dateArr != undefined){
		series = option.data.series;
	}
	var pageCount = 0;
	if(option.pageType ==1){
		pageCount = parseInt(series.length / 10);
		  if(series.length % 10 != 0){
			  pageCount++;
		  }		  
	  }else{
		  pageCount = option.pageCount;
	  }
		  
	//数组长度小于10的时候
	if(pageCount == 1){
	   $('.chart-page span').append($('<a/>').addClass('ui-state-disabled').text('1'));
	 }
		  
	//数组长度大于10的时候
	if(pageCount > 1){
		for(var i=1; i <= pageCount; i++){
			if(i==1){
			    $('.chart-page span').append($('<a/>').addClass('ui-state-disabled').attr('data-no',i).text(1)); 
			}else if(i > 5){
			    break;
			}else{
				$('.chart-page span').append($('<a/>').attr('data-no',i).text(i));
				$('.chart-page .next').removeClass('ui-state-disabled').addClass('next-enable');
				$('.chart-page .last').removeClass('ui-state-disabled').addClass('last-enable');
			} 
		}	  			  
	}
		  
		//绑定页码的点击事件
		  $('.chart-page span a').click(function(){
			  var currentNo = $(this).attr('data-no');
			  movePageIndex(currentNo,pageCount);
			  option.pageNo = currentNo;
			  getData(option);
		  });
		  
		//绑定previous的点击事件	 
		  $('.previous-enable').live('click',function(){
			  var currentNo = parseInt($('.chart-page span .ui-state-disabled').attr('data-no'));
			  var previousNo = currentNo - 1;
			  movePageIndex(previousNo,pageCount);
			  option.pageNo = previousNo;
			  getData(option);
		  });	  
		  
		//绑定next的点击事件
		  $('.next-enable').live('click',function(){
			  var currentNo = parseInt($('.chart-page span .ui-state-disabled').attr('data-no'));
			  var nextNo = currentNo + 1;
			  movePageIndex(nextNo,pageCount);
			  option.pageNo = nextNo;
			  getData(option);
		  });
		  
		//绑定first的点击事件
		  $('.first-enable').live('click',function(){
			  $('.chart-page span').children().removeClass('ui-state-disabled');
				  for(var i=1; i <= pageCount; i++){	
					  if(i==1){
						  $('.chart-page span').children('a:nth-child(1)').addClass('ui-state-disabled').attr('data-no',1).text(1); 
					  }else if(i > 5){
						  break;
					  }else{
						  $('.chart-page span').children('a:nth-child('+i+')').attr('data-no',i).text(i);
						  $('.chart-page .next').removeClass('ui-state-disabled').addClass('next-enable');
						  $('.chart-page .last').removeClass('ui-state-disabled').addClass('last-enable');
						  $('.chart-page .first').removeClass('first-enable').addClass('ui-state-disabled');
						  $('.chart-page .previous').removeClass('previous-enable').addClass('ui-state-disabled');
					  }
					  
				  }	
				  
			option.pageNo = 1;	  
			getData(option);		 	  
		  });
 	  
		//绑定last的点击事件
		  $('.last-enable').live('click',function(){
			  $('.chart-page span').children().removeClass('ui-state-disabled');
			  if(pageCount<=5){
				  for(var i = 1;i <= pageCount; i++){
					  $('.chart-page span').children('a:nth-child('+i+')').attr('data-no',i).text(i);
				  }
				  $('.chart-page span').children('a:nth-child('+pageCount+')').addClass('ui-state-disabled');
			  }else{
				  var index = 1;
				  for(var i=pageCount-4; i <= pageCount; i++){	
				     $('.chart-page span').children('a:nth-child('+index+')').attr('data-no',i).text(i); 
				     index++;
				  }	
				  $('.chart-page span').children('a:nth-child(5)').addClass('ui-state-disabled');
			  }
			  $('.chart-page .first').removeClass('ui-state-disabled').addClass('first-enable');
			  $('.chart-page .previous').removeClass('ui-state-disabled').addClass('previous-enable');
			  $('.chart-page .next').removeClass('next-enable').addClass('ui-state-disabled');
			  $('.chart-page .last').removeClass('last-enable').addClass('ui-state-disabled');
			  
			  option.pageNo = pageCount;
			  getData(option);
		  });		  
	
}


/**
 * 分页下标左右移动
 * 
 * */
function movePageIndex(currentNo,pageCount){
	$('.chart-page span').children().removeClass('ui-state-disabled');
	  if(parseInt(currentNo) != 1){
		  $('.chart-page .first').removeClass('ui-state-disabled').addClass('first-enable');
		  $('.chart-page .previous').removeClass('ui-state-disabled').addClass('previous-enable');
	  }else{
		  $('.chart-page .first').removeClass('first-enable').addClass('ui-state-disabled');
		  $('.chart-page .previous').removeClass('previous-enable').addClass('ui-state-disabled');
	  }
	  
	  if(parseInt(currentNo) != pageCount){
		  $('.chart-page .next').removeClass('ui-state-disabled').addClass('next-enable');
		  $('.chart-page .last').removeClass('ui-state-disabled').addClass('last-enable');
	  }else{
		  $('.chart-page .next').removeClass('next-enable').addClass('ui-state-disabled');
		  $('.chart-page .last').removeClass('last-enable').addClass('ui-state-disabled');
	  }
	  
	  if(pageCount > 5){
		  //导航数字向左或向右移动
		  if(parseInt(currentNo)>=3 && (pageCount-parseInt(currentNo))>=2){
			  $('.chart-page span').children('a:nth-child(3)').addClass('ui-state-disabled');
			  var start_no = parseInt(currentNo) - 2;
			  var end_no = parseInt(currentNo) + 2;
			  var index = 1;		  
			  for(var j= start_no; j <= end_no; j++){
				  $('.chart-page span').children('a:nth-child('+index+')').attr('data-no',j).text(j);
				  index++;
			  }				  
		  }else if(parseInt(currentNo)==2){	  
			  $('.chart-page span').children('a:nth-child(2)').addClass('ui-state-disabled');
			  for(var j= 1; j <= 5; j++){
				  $('.chart-page span').children('a:nth-child('+j+')').attr('data-no',j).text(j);
			  }	
			  
		  }else if((pageCount-parseInt(currentNo))==1){
			  $('.chart-page span').children('a:nth-child(4)').addClass('ui-state-disabled');
			  var index = 1;
			  for(var j= parseInt(currentNo) - 3; j <= pageCount; j++){
				  $('.chart-page span').children('a:nth-child('+index+')').attr('data-no',j).text(j);
				  index++;
			  }	
			  
		  }else if((pageCount-parseInt(currentNo))==0){			
			  $('.chart-page span').children('a:nth-child(5)').addClass('ui-state-disabled');
		  }else {
			  $('.chart-page span').children('a:nth-child(1)').addClass('ui-state-disabled');
		  }
		    		  		  
	  }else{
		  $('.chart-page span').children('a:nth-child('+parseInt(currentNo)+')').addClass('ui-state-disabled');
	  }
}

/**
 * 获得趋势图所需要的数据
 * 
 * */
function getData(option){
	
	//pageType为1时的数据获取方法
	if(option.pageType == 1){
		var series = option.data;
		option.dataType = 'daily';
		if(option.data.dateArr != undefined){
			series = option.data.series;
			option.dataType = 'weekly';
		}
		var first = (option.pageNo - 1) * 10;
		var last = option.pageNo * 10 - 1;
		if (last > series.length) {
			last = series.length-1;
		}
		var list = new Array();
		for ( var i = first; i <= last; i++) {
			list.push(series[i]);
		}
		drawHighCharts(list, option);
	}
	
	//pageType为2时的数据获取方法
	//TODO 暂时没用用到，如果用到需要对其重写
	if(option.pageType == 2){
		option.requestData['pageNo'] = option.pageNo;
		option.requestData['pageSize'] = 10;
		$.get(option.url, option.requestData, function(data){
			//drawHighCharts(data, option.chartTitle, option.domClass);
		});
	}
}

/**
 * 生成HighCharts图表
 * 
 * */
function drawHighCharts(data, option){
	var xAxis = {
			type : 'datetime',
			dateTimeLabelFormats : { // don't display the dummy year
				hour : " ",
				day : '%m月%d日',
				month : '%m %b',
				year : '%Y'
			}
		};
	
	if(option.dataType == 'daily') {
		tooltip = {
				formatter : function() {
					return '<b>'+option.chartTitle+'：' + this.series.name + '</b><br/>' + Highcharts.dateFormat('%e. %b', this.x)
								+ ': ' + this.y + '次';
				}
			};		
	} else {
		xAxis = {
				categories : option.data.dateArr	
			};
			tooltip = {
					valueSuffix: '次'
				};
	}
	
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	
	$('.' + option.domClass).highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: option.chartTitle+'访问趋势统计'
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
        
        series: data
    });
}
