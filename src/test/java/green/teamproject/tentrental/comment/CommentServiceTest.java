package green.teamproject.tentrental.comment;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import green.teamproject.tentrental.comment.dto.CommentDTO;
import green.teamproject.tentrental.comment.service.CommentService;

@SpringBootTest

public class CommentServiceTest {
	
	@Autowired
	CommentService service;
	
	
	
	
	@Test
	public void 데이터전체조회() {
		List<CommentDTO> result = service.getList(2);
		for(int i = 0; i < result.size(); i++) {
			CommentDTO dto = result.get(i);
			System.out.println(dto);
		}
	}
	
	@Test
	public void 데이터수정() {
		CommentDTO dto = service.read(1);
		dto.setCmtContents("레알내용수정");
		service.modify(dto);
	}
	
	
	
	
	
	
	

	
}
