package green.teamproject.tentrental.purchase.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import green.teamproject.tentrental.purchase.dto.PurchaseDto;
import green.teamproject.tentrental.purchase.service.PurchaseService;

@Controller
@RequestMapping("/purchase")

public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<PurchaseDto> list = purchaseService.getPage(page);
		model.addAttribute("list", list);
	}

	@GetMapping("/add")
	public void purchaseAdd(int goodsNo, Model model) {
		model.addAttribute("goodsNo", goodsNo);
	}

	@PostMapping("/add")
	public String addPost(PurchaseDto dto) {
		purchaseService.add(dto);
		return "redirect:/purchase/list";
	}

	@PostMapping("/remove")
	public String removePost(int pcsNo) {
		purchaseService.remove(pcsNo);
		return "redirect:/purchase/list";
	}

	@GetMapping("/read")
	public void read(int goodsNo, int pcsNo, String explanation, LocalDate pcsStartDate, LocalDate pcsEndDate, Model model) {
		model.addAttribute("goodsNo", goodsNo);
		model.addAttribute("pcsStartDate", pcsStartDate);
		model.addAttribute("pcsEndDate", pcsEndDate); 
		model.addAttribute("pcsNo", pcsNo);
		model.addAttribute("explanation", explanation);
	}

}
