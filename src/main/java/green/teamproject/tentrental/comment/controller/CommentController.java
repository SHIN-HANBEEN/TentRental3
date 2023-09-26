package green.teamproject.tentrental.comment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import green.teamproject.tentrental.comment.dto.CommentDTO;
import green.teamproject.tentrental.comment.service.CommentService;

@RestController
@RequestMapping("/comment")

public class CommentController {
	
	@Autowired
	private CommentService service;
	
	/*
	 * @GetMapping("/") public String home() { return "/home/main"; }
	 */

	@GetMapping("/read/{goodsNo}")
	public ResponseEntity<List<CommentDTO>> getListBygoodsEntity(@PathVariable("goodsNo") int goodsNo){
		List<CommentDTO> list = service.getList(goodsNo);
		System.out.println(list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> register(CommentDTO dto, Principal principal){
		
		//String userid = principal.getName(); //시큐리티 사용해서 현재 로그인한 사람의 아이디를 꺼내오기
		
		//todo: 로그인 기능이 완성이되면 아이디를 DTO에 저장한다
		
		dto.setCmtWriter("aaa"); //강제로 저장
		
		int cmtNo = service.register(dto);
		
		return new ResponseEntity<>(cmtNo, HttpStatus.OK);
	}
	
	@GetMapping("/remove/{cmtNo}")
	public ResponseEntity<String> remove(@PathVariable("cmtNo") int cmtNo){
		service.remove(cmtNo);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@PostMapping("/modify")
	public ResponseEntity<String> modify(CommentDTO dto, Principal principal){
		
		dto.setCmtWriter("코딩좋아하는상현");//강제로 저장
		
		service.modify(dto);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
}
