package green.teamproject.tentrental.common.controller;

import java.security.Principal;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.service.GoodsService;
import green.teamproject.tentrental.user.service.UserService;
import green.teamproject.tentrental.user.validator.AccountValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
//@RequestMapping("/home")
// 아무 경로로 들어와도 /home/main 으로 보내주기 위해 중간 경로를 없애고
// GetMapping "/" 로 설정해준다.
public class HomeController {

    @Autowired
    private GoodsService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/") // 메인화면 보내줌
    public String main(PageRequestDTO pageRequestDTO, Model model) {
        log.info("8080/ Init........" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
        return "home/main"; //loalhost:8080 접속을 하면 home/main 이 반환이 된다.
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/home/main")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("/home/main Get........" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
