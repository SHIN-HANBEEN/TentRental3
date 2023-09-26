package green.teamproject.tentrental.common.controller;

import green.teamproject.tentrental.common.security.dto.UserAuthDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll.....");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public void exUser(@AuthenticationPrincipal UserAuthDTO userAuthDTO) {
        log.info("exUser.....");
        log.info("-------------------");
        log.info(userAuthDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin.....");
    }

    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"shb080101@gmail.com\"")
    @GetMapping("/exOnly")
    public String exMemberOnly(@AuthenticationPrincipal UserAuthDTO userAuthDTO) {
        log.info("exMemberOnly.....");
        log.info(userAuthDTO);

        return "sample/admin";
    }
}
