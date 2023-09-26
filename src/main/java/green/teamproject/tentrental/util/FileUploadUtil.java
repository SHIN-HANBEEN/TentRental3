package green.teamproject.tentrental.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Component
public class FileUploadUtil {
	
	String filePath = "C:\\Users\\user\\Documents\\uploadfile";
	
	public String fileUpload(MultipartFile multipartFile) {
		
		Path copyOfLocation = Paths.get(filePath + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		if(multipartFile.isEmpty()) {
			return null;
		}
		
		try {
			
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return multipartFile.getOriginalFilename();
	}

}
