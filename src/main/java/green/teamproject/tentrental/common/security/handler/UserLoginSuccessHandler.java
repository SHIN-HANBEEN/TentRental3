package green.teamproject.tentrental.common.security.handler;

import green.teamproject.tentrental.common.security.dto.UserAuthDTO;
import green.teamproject.tentrental.common.security.token.UserTokenStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import static green.teamproject.tentrental.common.security.token.UserTokenGenerator.generateUniqueToken;

@Log4j2
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    PasswordEncoder passwordEncoder; //비밀번호 인코더
    private RedirectAttributes redirectAttributes;

//    @Autowired //UserTokenStore 를 자동 주입 받기 위해서 @Autowired 사용. UserTokenStore is null 오류를 방지할 수 있다.
//    public UserLoginSuccessHandler(PasswordEncoder passwordEncoder, UserTokenStore userTokenStore) {
//        this.passwordEncoder = passwordEncoder;
//        this.userTokenStore = userTokenStore;
//    }

    @Autowired //UserTokenStore 를 자동 주입 받기 위해서 @Autowired 사용. UserTokenStore is null 오류를 방지할 수 있다.
    public UserLoginSuccessHandler() {}

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication
    ) throws IOException, ServletException {

        log.info("----------------------");
        log.info("onAuthenticationSuccess ");

        UserAuthDTO authUser = (UserAuthDTO) authentication.getPrincipal(); // 인증 객체를 꺼내온다.

        String userEmail = authUser.getEmail();
        boolean fromSocial = authUser.isFromSocial();
        //String userEmailToken = generateUniqueToken(); //이메일을 redirect 할 때 URL 에 바로 띄우는 것은 보안상 이슈가 발생하므로 토큰을 생성하여 처리한다.
        //userTokenStore.storeTokenMapping(userEmailToken, userEmail);//생성한 토큰과 유저의 이메일을 매핑시킨다.


        log.info("Need Modify Member?" + fromSocial);

        boolean passwordResult = passwordEncoder.matches("1111", authUser.getPassword());

        if (fromSocial && passwordResult) {
            redirectStrategy.sendRedirect(request, response,
                    "/user/editForJustSignUpSocial"
                        ); //화면을 반환하는게 아니라 컨트롤러 메서드를 반환하는 것이므로 제일 앞에 / 슬래시를 뺴지 않는다.
        }
    }
}
