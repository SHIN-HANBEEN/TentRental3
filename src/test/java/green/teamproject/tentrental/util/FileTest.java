package green.teamproject.tentrental.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class FileTest {
	//파일 생성하기
	//폴더 생성하기
	//특정 폴더 밑에 있는 파일 목록 확인하기
	//특정파일이 파일인지 폴더인지 확인하기
	//파일이 존재하는지 확인하기
	
	public static final String TEST_DIRECTORY = "test";
	

	@Test
	public void 파일생성() {
		/*
		 * String path = System.getProperty("user.dir") +File.separator +TEST_DIRECTORY;
		 * System.out.println("절대경로: " + path);
		 */
		
		File f = new File("C:\\Users\\user\\Documents\\김상현", "연습");
		System.out.println();
		
		if(!f.exists()) {
			if(f.mkdir())
				System.out.println("폴더생성성공");
			else
				System.out.println("폴더생성실패");
		} else {
			System.out.println("폴더존재");
		}
		System.out.println();
		
		File f2 = new File(f, "test.txt");
		System.out.println(f2.getAbsolutePath());
		
		if(!f2.exists()) {
			try {
				if(f2.createNewFile())
					System.out.println("파일생성성공");
				else
					System.out.println("파일생성실패");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("파일이존재");
		}
		
		System.out.println();
		
		if(f.isDirectory()) {
			System.out.println("폴더입니다");
		} else {
			System.out.println("놉");
		}
		
		System.out.println();
		
		if(f.exists() && f.isDirectory()) {
			String[] fList = f.list();
			for(int i = 0; i<fList.length; i++) {
				System.out.println(fList[i]);
			} 
			
			} else {
				System.out.println("해당 경로는 폴더가 아님");
		}
		
	}
	
	
	
	

}
