<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>文件上传</title>
<%@ include file="/WEB-INF/views/includes/head_scripts_links.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<form action="upload?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal" enctype="multipart/form-data">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>文件上传</h5>
						</div>
						<div class="widget-content no-padding">
							<div class="control-group">
								<label class="control-label">文件名 :</label>
								<div class="controls">
									${originalFileName }
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">文件大小 :</label>
								<div class="controls">
									${fileSize }
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Content-Type :</label>
								<div class="controls">
									${contentType }
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">选择文件 :</label>
								<div class="controls">
									<input name="file" type="file" value="" placeholder="选择文件"/>
								</div>
							</div>
							<div class="form-actions">
								<form:errors path="*" cssClass="alert alert-error alert-block" element="div"/>
								<c:if test="${success != null}">
									<div class="alert alert-success alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a> <span>${success }</span>
									</div>
								</c:if>
								<c:if test="${error != null}">
									<div class="alert alert-error alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a> <span>${error }</span>
									</div>
								</c:if>
								<button type="submit" class="btn btn-success">开始上传</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<%@ include file="/WEB-INF/views/includes/foot_scripts_links.jspf"%>
</html>