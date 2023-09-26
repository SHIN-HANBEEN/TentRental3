package green.teamproject.tentrental.goods.controller;

import java.io.File;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.entity.GoodsEntity;
import green.teamproject.tentrental.goods.service.GoodsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	//메인화면
	@GetMapping("/home")
	public void home() {
		
	}
	
	//상품리스트 화면
	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<GoodsDTO> list = service.getList(page);
		model.addAttribute("list", list);
		System.out.println("전체 페이지 수" + list.getTotalPages());
		System.out.println("전체 게시물 수: " + list.getTotalElements());
		System.out.println("현재 페이지 번호: " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수: " + list.getNumberOfElements());
	}
	
	//등록화면
	@GetMapping("/register")
	public void register() {
		
	}
	
	//등록처리
	@PostMapping("/register")
	public String registerPost(GoodsDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
		redirectAttributes.addAttribute("msg", no);
		return "redirect:/board/list";
	}
	
	//상세조회
	@GetMapping("/read")
	public void read(int no, HttpSession session ,@RequestParam(defaultValue = "0") int page, Model model) {
		GoodsDTO dto = service.read(no);
		service.updateView(no, session);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}
	
	//수정화면
	@GetMapping("/modify")
	public void modify(int no, Model model) {
		GoodsDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}
	
	//수정처리
	@PostMapping("/modify")
	public String modifyPost(GoodsDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getGoodsNo());
		return "redirect:/board/read";
	}
	
	//삭제처리
	@PostMapping("/remove")
	public String removePost(int goodsNo) {
		service.remove(goodsNo);
		return "redirect:/board/list";
	}
	
	//검색처리
	/*
	 * @GetMapping("/search") public String search(@RequestParam(value="keyword")
	 * String keyword, Model model) { List<GoodsEntity> searchList =
	 * service.search(keyword); model.addAttribute("searchList", searchList); return
	 * "redirect:/board/list"; }
	 */
	
	

}
