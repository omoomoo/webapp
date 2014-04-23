package com.demo.webapp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping(value = "/security")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Value(value = "fileupload.basepath")
	private static String basepath;
	@Value(value = "fileupload.baseurl")
	private static String pathurl;

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String getFileUploadPage() {
		return "/fileupload/upload";
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
		logger.info("{}", file.getContentType());
		logger.info("{}", file.getOriginalFilename());
		logger.info("{}", file.getSize());

		redirectAttributes.addFlashAttribute("contentType", file.getContentType());
		redirectAttributes.addFlashAttribute("originalFileName", file.getOriginalFilename());
		redirectAttributes.addFlashAttribute("fileSize", file.getSize());

		try {
			// TODO 任意文件上传漏洞
			FileUtils.writeByteArrayToFile(new File(basepath + file.getOriginalFilename()), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/security/file";
	}

	/**
	 * @param bytes
	 * @return
	 */
	public String getMD5Hash(byte[] bytes) {
		// Base64.encodeBase64String(bytes[]);
		return Base64.encodeBase64String(DigestUtils.getMd5Digest().digest(bytes));
	}

	// @RequestMapping(value = "/date/test")
	public String dateTest(@DateTimeFormat(iso = ISO.DATE) Date date) {
		return null;
	}

	/**
	 * 修复Java路径遍历漏洞BUG
	 * 
	 * @see http://hakipedia.com/index.php/Poison_Null_Byte
	 * @param pathname
	 * @return
	 */
	public boolean isPathValid(String pathname) {
		return pathname.indexOf('\u0000') < 0 ? true : false;
	}
}
