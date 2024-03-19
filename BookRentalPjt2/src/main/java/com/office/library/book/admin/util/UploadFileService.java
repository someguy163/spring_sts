package com.office.library.book.admin.util;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.book.BookVo;

@Service
public class UploadFileService {

	public String upload(MultipartFile file) {

		boolean result = false; 

		//파일저장
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		String uploadDir = "C:\\library\\upload\\";

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replace("-", "");

		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);

//		디렉토리가 없으면 mkdirs를 통해서 디렉토리를 만들어라
		
		if(!saveFile.exists())
			saveFile.mkdirs();

		try {
			file.transferTo(saveFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(result) {
			System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!");
			return uniqueName + fileExtension;

		} else {
			System.out.println("[UploadFileService] FILE UPLOAD FAIL!");

			return null;
		}

	}
	


}
