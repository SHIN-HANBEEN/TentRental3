package green.teamproject.tentrental.user.controller;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.security.token.UserTokenStore;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.entityenum.Role;
import green.teamproject.tentrental.user.service.UserService;
import green.teamproject.tentrental.user.validator.AccountValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Base64;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor // 자동 주입을 위한 Annotation
@Log4j2
public class UserController {
    private final UserService service; //빈에 등록된 service 사용
    private final AccountValidator accountValidator;

    @Autowired
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;


//    @PreAuthorize("permitAll()")
    @GetMapping("/customlogin")
    public void loginGet() {
        log.info("user/login GetMethod activated");
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void register(Model model) {
        model.addAttribute("userDTO", new UserDTO()); //빈 객체를 보내는 이유는 register.html 에서 올바른 형식의 정보를 입력했는지 에러 검사를 위해서이다.
        log.info("register get...");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public String register(
            UserDTO dto,
            @Validated UserDTO userDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        log.info("/user/register post method init for dto : " + dto);
        accountValidator.validate(userDTO, bindingResult);
        log.info("AccountValidator is activated. BindingResult has errors? : " + bindingResult.hasErrors());
        if(bindingResult.hasErrors()) {
            return "/user/register"; //등록 조건을 만족하지 않음
        } else {
            String inputedUserPw = dto.getUserPw();
            dto.setUserPw(passwordEncoder.encode(inputedUserPw));
            dto.setFromSocial(false); //기본적으로 소셜 회원 가입자가 아니다.
            service.register(dto); //회원 가입 조건을 만족하여 DB에 등록시킴
        }

        return "redirect:/user/customlogin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list............" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/read", "/modify"})
    public void adminReadModifyGet(@RequestParam String userEmail, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {  // 다시 목록 페이지로 돌아가는 데이터를 같이 저장하기 위해서 PageRequestDTO를 파라미터로 같이 사용한 것을 확인할 수 있습니다. 이때 @ModelAttribute 는 없어도 처리가 가능하지만 명시적으로 requestDTO라는 이름으로 처리해 두었습니다.
        log.info("userEmail: " + userEmail);
        UserDTO dto = service.read(userEmail);
        model.addAttribute("dto", dto);  // 페이지 정보 유지 시키기 위한 코드
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modify")
    public String adminModifyPost(UserDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        log.info("post modify.................");
        log.info("dto: " + dto);
        service.modify(dto);

        redirectAttributes.addAttribute("userEmail", dto.getUserEmail()); // redirect 할 때 정보를 유지시키기
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("r_type", requestDTO.getR_type());
        redirectAttributes.addAttribute("r_keyword", requestDTO.getR_keyword());

        return "redirect:/user/read"; // Use the correct URL mapping here
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/edit")
    public void edit(Principal principal, Model model) {
        log.info("========= /user/edit Get init =========");
        String userEmail = principal.getName(); // Get the logged-in user's email
        UserDTO loggedInUser = service.read(userEmail);
        log.info(loggedInUser);
        model.addAttribute("dto", loggedInUser); //View 단으로 정보 전송
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/edit")
    public String edit(@RequestParam("roleString") String roleString, UserDTO dto, RedirectAttributes redirectAttributes
    ) {
        log.info("========= /user/edit Post Init =========");
        log.info("dto: " + dto);

        Set<Role> roleSet = new HashSet<>(); //View 단에서 넘어오는 Role 정보는 String 으로 넘어오기 때문에 Set< > 자료형으로 바꾸어 주기 위한 코드들이다.
        if ( roleString.equals("[USER]") ) {
            roleSet.add(Role.USER);
        } else if ( roleString.equals("[ADMIN]") ) {
            roleSet.add(Role.ADMIN);
        }
        dto.setRole(roleSet);

        String modifiedUserPW = dto.getUserPw();
        dto.setUserPw(passwordEncoder.encode(modifiedUserPW)); //소셜 로그인 회원이 수정한 비밀번호를 encoding 을 진행한다.

        service.modify(dto);

        //redirectAttributes.addAttribute("userEmail", dto.getUserEmail()); // redirect 할 때 정보를 유지시키기, 혹시 수정하고 안내 팝업을 띄울 때 필요할 까봐 코드는 주석처리만 해두었다.

        return "redirect:/home/main"; // Use the correct URL mapping here
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/editForJustSignUpSocial")
    public void editForJustSignUpSocial(Principal principal, Model model) {
        log.info("========= /user/editForJustSignUpSocial Get init =========");
        String userEmail = principal.getName(); // Get the logged-in user's email
        UserDTO loggedInUser = service.read(userEmail);
        log.info(loggedInUser);
        model.addAttribute("dto", loggedInUser); //View 단으로 정보 전송
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/editForJustSignUpSocial")
    public String editForJustSignUpSocial(
            UserDTO userDTO,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult
    ) {
        log.info("========= /user/editForJustSignUpSocial Post Init =========");
        log.info("userDTO: " + userDTO);

        accountValidator.validate(userDTO, bindingResult);
        log.info("AccountValidator is activated. BindingResult has errors? : " + bindingResult.hasErrors());
        if(bindingResult.hasErrors()) {
            return "/user/register"; //등록 조건을 만족하지 않음
        } else {
            String inputedUserPw = userDTO.getUserPw();
            userDTO.setUserPw(passwordEncoder.encode(inputedUserPw));
            userDTO.setFromSocial(false); //기본적으로 소셜 회원 가입자가 아니다.
            service.modify(userDTO); //회원 가입 조건을 만족하여 DB에 등록시킴
        }

        return "redirect:/home/main"; // Use the correct URL mapping here
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/remove")
    public String remove(String userEmail, RedirectAttributes redirectAttributes) {
        log.info("userEmail: " + userEmail);
        service.remove(userEmail);
        redirectAttributes.addFlashAttribute("msg", userEmail);
        return "redirect:/user/list";
    }
}
