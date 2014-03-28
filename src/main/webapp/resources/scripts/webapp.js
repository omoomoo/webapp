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


// 弹出窗口
window.openWindow = function (url, name, iWidth, iHeight) {
	// 默认宽高
	iWidth = iWidth || 1048;
	iHeight = iHeight || 700;
    //获得窗口的垂直水平位置  
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2;  
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
    
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=1,titlebar=no');  
};