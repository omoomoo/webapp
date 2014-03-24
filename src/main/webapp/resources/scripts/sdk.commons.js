// 统计Ajax错误处理
$(document).ajaxError(function(event, jqxhr, settings, exception) {
	var request = "Request : " + settings.type + " " + settings.url;
	var message = "Response : " + exception;
	var status = "Status : " + jqxhr.status;
	var newErrorMessage = '';
	newErrorMessage = errorMessage.replace(/{{request}}/, request);
	newErrorMessage = newErrorMessage.replace(/{{message}}/, message);
	newErrorMessage = newErrorMessage.replace(/{{status}}/, status);

	if ($('#fixed-message-box').length == 0) {
		var $fixedMessageBox = $(fixedMessageBox);
		$fixedMessageBox.html(newErrorMessage);
		$('#content').append($fixedMessageBox);
	} else {
		$('#fixed-message-box').append(newErrorMessage);
	}

});

var fixedMessageBox = '<div id="fixed-message-box" style="position:fixed;top:50px;right:20px; width:300px;"></div>';
var errorMessage = '<div class="alert alert-error alert-block" style="margin-bottom:5px; word-break：break-all; word-wrap:break-word;"><a class="close" data-dismiss="alert" href="#">×</a>{{request}}<br>{{status}}<br>{{message}}</div>';