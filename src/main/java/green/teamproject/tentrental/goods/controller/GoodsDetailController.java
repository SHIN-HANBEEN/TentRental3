package green.teamproject.tentrental.goods.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/goods")
public class GoodsDetailController {
    @GetMapping("/tentSetAFor2People")
    @PreAuthorize("permitAll()")
    public void tentSetAFor2PeopleGet() {
        log.info("tentSetAFor2PeopleGet initiated");
    }
}
