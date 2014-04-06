package com.demo.webapp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/fileupload")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getFileUploadPage() {
		return "/fileupload/upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
		logger.info("{}", file.getContentType());
		logger.info("{}", file.getOriginalFilename());
		logger.info("{}", file.getSize());

		redirectAttributes.addFlashAttribute("contentType", file.getContentType());
		redirectAttributes.addFlashAttribute("originalFileName", file.getOriginalFilename());
		redirectAttributes.addFlashAttribute("fileSize", file.getSize());

		try {
			FileUtils.writeByteArrayToFile(new File("C:\\ab"), file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/fileupload/upload";
	}

	// @RequestMapping(value = "/date/test")
	public String dateTest(@DateTimeFormat(iso = ISO.DATE) Date date) {
		return null;
	}

}
