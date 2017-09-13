package cn.imexue.ec.web.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.StringUtil;

/**
 * 
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年2月17日
 * @author lijianfeng
 * @version 1.0
 */
public class FileServiceImpl {
	public File uploadFile(MultipartFile file, String path) throws Exception {
		Date today = DateUtil.getCurrentTimestamp();
		String fileName = DateUtil.formatDate(today, DateUtil.DATE_FORMAT_YMDHMS);
		fileName = fileName + StringUtil.getRandomStringIgnoreSensitive(4);
		String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		fileName = fileName + prefix;
		
		path = path + DateUtil.formatDate(today) + "/";
		File targetFile = new File(fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		file.transferTo(targetFile);
		return targetFile;
	}
	
	public void deleteFile(String url){
		try{
			File file = ResourceUtils.getFile(url);
			file.delete();
		}catch(FileNotFoundException e){
			return ;
		}
	}
}
