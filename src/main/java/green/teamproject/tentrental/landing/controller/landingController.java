package green.teamproject.tentrental.landing.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/landing")
@Log4j2
public class landingController {
    @PreAuthorize("permitAll()")
    @GetMapping("/landing")
    public void landing() {
        log.info("landing controller initiated");
    }

}
