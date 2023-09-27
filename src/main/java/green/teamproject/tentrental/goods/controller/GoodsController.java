package green.teamproject.tentrental.goods.controller;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.service.GoodsService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
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
	@PreAuthorize("permitAll()")
	@GetMapping("/read")
	public void read(@RequestParam("goodsNo") String goodsNo, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, HttpSession session, Model model) {
		int intNo = Integer.parseInt(goodsNo);
		GoodsDTO dto = service.read(intNo);
		service.updateView(intNo, session);
		model.addAttribute("result", dto);
	}
	
	//수정화면
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/modify")
	public void modify(@RequestParam("goodsNo") String goodsNo, Model model) {
		int intNo = Integer.parseInt(goodsNo);
		GoodsDTO dto = service.read(intNo);
		model.addAttribute("dto", dto);
	}
	
	//수정처리
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/modify")
	public String modifyPost(GoodsDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("goodsNo", dto.getGoodsNo());
		return "redirect:/goods/read";
	}
	
	//삭제처리
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/remove")
	public String removePost(int goodsNo) {
		service.remove(goodsNo);
		return "redirect:/goods/list";
	}
}
