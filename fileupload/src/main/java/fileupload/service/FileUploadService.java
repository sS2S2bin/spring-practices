package fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH="/Users/soobin/Desktop/poscodx2024/eclipse-workspace/fileupload-files";
	private static String URL_PATH="/images";
	

	public String restore(MultipartFile file) {
		String url = null;
		File uploadDirectory = new File(SAVE_PATH);
		
		try {
			// 존재 여부 확인 
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			
			if(file.isEmpty()) {
				return url;
			}
			
			String originFilename = file.getOriginalFilename();
			String exName = originFilename.substring(originFilename.lastIndexOf(".")+1); // abc.jpeg -> jpeg
			String saveFileName = generateSaveFilename(exName);
			Long fileSize = file.getSize();
			
			System.out.println("origin file name"+originFilename+":"+fileSize+"save file name"+saveFileName);
			
			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
			os.write(data);
			os.close();
			
			url = URL_PATH+"/"+saveFileName;
			
			
		} catch (IOException e) {
			throw new RuntimeException(); // 의미 있게 File upload exception을 만들어야해
		}
		return url;
	}


	private String generateSaveFilename(String exName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("."+exName);
		
		
		return filename;
	}

}
