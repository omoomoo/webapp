package com.demo.webapp.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/fileupload")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getFileUploadPage() {
		return "/fileupload/upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		logger.info("{}", file.getContentType());
		logger.info("{}", file.getName());
		logger.info("{}", file.getOriginalFilename());
		logger.info("{}", file.getSize());

		return "/fileupload/upload";
	}

//	@RequestMapping(value = "/date/test")
	public String dateTest(@DateTimeFormat(iso = ISO.DATE) Date date) {
		return null;
	}

}
