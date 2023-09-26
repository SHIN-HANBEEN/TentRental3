package green.teamproject.tentrental.common.security.config;

import green.teamproject.tentrental.common.security.handler.UserLoginSuccessHandler;
import green.teamproject.tentrental.common.security.service.UserEntityDetailsService;
import green.teamproject.tentrental.common.security.token.UserTokenStore;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Configuration // 어노테이션으로 시큐리티 설정 클래스임을 명시한다.
@EnableWebSecurity // enablewebsecurity 어노테이션은 스프링 mvc 와 시큐리티를 결합한다.
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //@PreAuthorize 를 사용하기 위해 prePostEnabled 와 securedEnabled 속성 사용
public class SecurityConfig {
    @Autowired
    private UserEntityDetailsService userEntityDetailsService; //Remember Me 에서 사용

    private RedirectAttributes redirectAttributes;

    /* 사용자 인증 처리를 위한 메소드 추가
        메소드에 Bean 어노테이션을 붙이면 반환객체가 스프링 컨테이너의 빈으로 등록된다.
        이렇게 빈으로 등록된 객체를 인증이 필요할 때 사용하게 된다.
        아래 코드가 인증 필터 역할을 수행하는 메서드이다. */
    @Bean
    PasswordEncoder passwordEncoder() { //시큐리티 커스터마이징 첫 설정은 패스워드 암호화 입니다.
        return new BCryptPasswordEncoder(); //Security 커스터마이징의 첫 번째 처리는 비밀번호 암호화 방식의 설정인데, BCryptPasswordEncooder 를 최근에는 많이 사용하고 있는 추세이다.
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //url 주소마다 접근 설정 처리. SecurityFilterChain 을 반환하는 코드입니다.
        // 체인 형태로 기능별로 사용자 접근권한을 설정하는 코드이다.
        // 접근권한을 다 설정하면 ; 세미콜론으로 마무리 짓는다.
        log.info("----------filterchain--------------");

        http
                .authorizeRequests()
                    .and()
                .formLogin()
                    .loginPage("/user/customlogin") //커스텀 로그인 페이지를 지정하지만, 로그인을 위한 Post 처리를 하는 컨트롤러는 필요없고, view 단에서 /login 으로 처리하면 내부적으로 알아서 처리해준다.
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/home/main",true)
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessUrl("/home/main")
                    .and()
                .userDetailsService(userEntityDetailsService); //HttpSecurity 의 userDetailsService( ) 메서드를 통해서 UserEntityDetailsService 를 가져와야 /login 으로 들어오는 요청들을 커스텀 페이지로 로그인을 해도 정상적으로 처리할 수 있다.

        http
                .csrf()
                .disable();

        http
                .oauth2Login()
                .successHandler(userLoginSuccessHandler());

        http
                .rememberMe()
                .tokenValiditySeconds(60*60*24*7);

//        http.csrf().disable(); //CSRF 토큰 발행을 중단시킨다. 보안상 이슈가 있어서 그렇다.
//        http.oauth2Login().successHandler(userLoginSuccessHandler()); //google 로그인 처럼 OAuth 로그인을 위한 설정
//        http.rememberMe().tokenValiditySeconds(60*60*24*7) //Remember Me 활성화를 1주일 동안 유지되도록 설정
//                .userDetailsService(userEntityDetailsService);

        return http.build();
    }

    @Bean
    public UserLoginSuccessHandler userLoginSuccessHandler() {
        return new UserLoginSuccessHandler(); //소셜 로그인 후 modify 로 이동시킬 때 이메일정보를 가져갈 수 있도록 redirectAttribute 추가. UserTokenStore 추가
    }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() { //암호화 테스트를 위한 코드입니다. 암호화된 패스워드를 이용하기 위해서는 해당 암호를 사용하는 사용자가 필요한데요. 아래와 같은 코드로 사용자를 생성해줄 수 있습니다.
//        UserDetails user = User.builder() //시큐리티가 기본적으로 제공하는 User 구현체를 이용해서 테스트를 위한 임의의 사용자를 생성할 수 있다.
//                .username("user1")
//                .password(passwordEncoder().encode("1111"))
//                .roles("USER")
//                .build();
//
//        log.info("userDetailsService......");
//        log.info(user);
//
//        return new InMemoryUserDetailsManager(user); //생성한 임의의 유저를 InMemoryUserDetailsManager( ) 를 활용해서 메모리 상에 있는 데이터를 이용하는 인증 매니저(AuthenticationManager)를 생성합니다.
//    }
}