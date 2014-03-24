$(function() {

	/** 日期控件 * */
	$('#date-picker-button').click(function() {
		$('#date-picker').toggle();
		$('body').click(function() {
			$('#date-picker').hide();
		});
		return false;
	});
	$('#date-picker').click(function() {
		return false;
	});

	$('#date-from')
			.datepicker(
					{
						dateFormat : 'yy-mm-dd',
						monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月",
								"七月", "八月", "九月", "十月", "十一月", "十二月" ],
						dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
						numberOfMonths : 1,
						defaultDate : '-2w',
						onSelect : function(selectedDate) {
							$('#date-to').datepicker('option', 'minDate',
									selectedDate);
							$('#startDate').val(selectedDate);
						}
					});

	$('#date-to').datepicker(
			{
				dateFormat : 'yy-mm-dd',
				monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
						"九月", "十月", "十一月", "十二月" ],
				dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
				numberOfMonths : 1,
				onSelect : function(selectedDate) {
					$('#date-from').datepicker('option', 'maxDate',
							selectedDate);
					$('#endDate').val(selectedDate);
				}
			});

	$('#date-submit').click(function() {
		$('.date-button').removeClass('active');
		$('.widget-plain form').submit();
		$('#date-picker').hide();
	});

	/** 快速日期选择控件 * */
	$('.date-button').click(function() {
		$('.date-button').removeClass('active');
		$(this).addClass('active');
		var id = $(this).attr('id');
		switch (id) {
		case '7days':
			changeDateForTendency(7);
			break;
		case '30days':
			changeDateForTendency(30);
			break;
		case '60days':
			changeDateForTendency(60);
			break;
		case '90days':
			changeDateForTendency(90);
			break;
		case '1week':
			changeDateForRegular(7);
			break;
		case '1month':
			changeDateForRegular(30);
			break;
		case '2months':
			changeDateForRegular(60);
			break;
		case '3months':
			changeDateForRegular(90);
			break;
		}
		$('.widget-plain form').submit();
	});

	/** 类型控件 * */
	$('.statisticsType .type-button').click(function() {
		var type = $(this).attr('data-value');
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('#statisticsType').val(type);
		$('.widget-plain form').submit();
	});

	$('.timeType .type-button').click(function() {
		var period = $(this).attr('data-value');
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('#timeType').val(period);
		$('.widget-plain form').submit();
	});
	
	$('.detail .type-button').click(function() {
		var detail = $(this).attr('data-value');
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('#detail').val(detail);
		$('.widget-plain form').submit();
	});

	
	/** 修改趋势时间 * */
	function changeDateForTendency(days_num) {
		var end_date = new Date();
		var start_date = new Date(end_date.getTime() - days_num * 24 * 3600
				* 1000);
		$('#startDate').val(dateFormat(start_date));
		$('#endDate').val(dateFormat(end_date));
		$('#date-from').datepicker('option', 'maxDate',
				end_date);
		$('#date-from').datepicker('setDate', start_date);
		$('#date-to').datepicker('option', 'minDate',
				start_date);
		$('#date-to').datepicker('setDate', end_date);
		
	}
	
	/** 修改定期统计时间 **/
	function changeDateForRegular(days_num){
		var current_date = new Date();
		var result_date = new Date(current_date.getTime() - days_num * 24 * 3600
				* 1000);
		$('#endDate').val(dateFormat(result_date));
		$('#date-to').datepicker('setDate', result_date);
	}

	/** 转换日期格式 * */
	function dateFormat(date) {
		var year = date.getFullYear();
		var month = date.getMonth() < 10 ? '0' + (date.getMonth() + 1) : date
				.getMonth() + 1;
		var day = date.getDate();
		return year + '-' + month + '-' + day;
	}
});