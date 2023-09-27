package green.teamproject.tentrental.goods.controller;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.service.GoodsService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
@Log4j2
public class GoodsController {
	
	@Autowired
	private GoodsService service;

	//상품리스트 화면
	@PreAuthorize("permitAll()")
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model){
		log.info("list........" + pageRequestDTO);
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
	
	//등록화면
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/register")
	public void register() {
		
	}
	
	//등록처리
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public String registerPost(GoodsDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
		redirectAttributes.addAttribute("msg", no);
		return "redirect:/home/main";
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
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/modify")
	public void modify(int no, Model model) {
		GoodsDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}
	
	//수정처리
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/modify")
	public String modifyPost(GoodsDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getGoodsNo());
		return "redirect:/board/read";
	}
	
	//삭제처리
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/remove")
	public String removePost(int goodsNo) {
		service.remove(goodsNo);
		return "redirect:/board/list";
	}
}
