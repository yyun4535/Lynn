package com.human.main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class Upload {

	// public boolean fileUpload(MultipartHttpServletRequest mRequest){
	// boolean uploadFile = false;

	public String fileUpload(MultipartHttpServletRequest mRequest) {
		String uploadFile = null;

		String uploadPath = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/wtpwebapps/uploadfile/";

		Iterator<String> iterator = mRequest.getFileNames();

		while (iterator.hasNext()) {
			String uploadFileName = iterator.next();

			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String originFileName = mFile.getOriginalFilename();
			String saveFileName = originFileName;

			if (saveFileName != null && !saveFileName.equals("")) {
				if (new File(uploadPath + saveFileName).exists()) {
					saveFileName = System.currentTimeMillis() + "_" + saveFileName;
				} // if()

				try {
					mFile.transferTo(new File(uploadPath + saveFileName));
					uploadFile = saveFileName;
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} // if()
		} // while()

		return uploadFile;
	}// fileUpload
}
