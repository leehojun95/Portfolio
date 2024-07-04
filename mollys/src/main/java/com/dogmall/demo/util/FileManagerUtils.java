package com.dogmall.demo.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class FileManagerUtils {

	public static String getDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	public static String uploadFile(String uploadFolder, String dateFolder, MultipartFile uploadFile) {
		
		String realUploadFileName = "";
		
		File file = new File(uploadFolder, dateFolder);
		
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		String clientFileName = uploadFile.getOriginalFilename();
		UUID uudi = UUID.randomUUID();
		
		realUploadFileName = uudi.toString() + "_" + clientFileName;
		
		try {
			File saveFile = new File(file, realUploadFileName);
			uploadFile.transferTo(saveFile);
			
			if(checkImageType(saveFile)) {
				File thumnailFile = new File(file, "s_" + realUploadFileName);
				
				BufferedImage bo_img = ImageIO.read(saveFile);
				double ratio = 3;
				int width = (int) (bo_img.getWidth() / ratio);
				int height = (int) (bo_img.getHeight() / ratio); 
				
				Thumbnails.of(saveFile)
						  .size(width, height)
						  .toFile(thumnailFile);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return realUploadFileName;
	}
	
	public static boolean checkImageType(File saveFile) {
		
		boolean isImageType = false;
		
		try {
			
			String contentType = Files.probeContentType(saveFile.toPath());
			
			isImageType = contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return isImageType;
	}
	
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		File file = new File(uploadPath, fileName);
		
		if(!file.exists()) {
			return entity;
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		
		entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		
		return entity;
	}
	
	public static void delete(String uploadPath, String dateFolderName, String fileName, String type) {
		
		File file2 = new File((uploadPath + "\\" + dateFolderName + "\\" + fileName.substring(2)).replace('\\', File.separatorChar));
		if(file2.exists()) file2.delete();
		
		if(type.equals("image")) {
			
			File file1 = new File((uploadPath + "\\" + dateFolderName + "\\" + fileName).replace('\\', File.separatorChar));
			if(file1.exists()) file1.delete();
		}
	}
	
	
}
