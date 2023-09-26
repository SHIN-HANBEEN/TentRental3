package green.teamproject.tentrental.common.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class UserAuthDTO extends User implements OAuth2User { //UserDetails 인터페이스를 구현한 User 클래스를 상속받는다. 엔티티 클래스와 DTO 클래스를 별도로 구성했듯이 ClubAuthMemberDTO 가 바로 이런 역할을 하는 클래스입니다. ClubAuthMemeberDTO는 DTO 역할을 수행하는 클래스인 동시에 스프링 시큐리티에서 인가/인증 작업에 사용할 수 있게 됩니다. 또한 OAuth2User 를 상속받아 소셜 로그인 처리를 진행.
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String, Object> attr;

    public UserAuthDTO(
                                String username,
                                String password,
                                boolean fromSocial,
                                Collection<? extends GrantedAuthority> authorities,
                                Map<String, Object> attr) {

        this(username, password, fromSocial, authorities);
        this.attr=attr;
    }

    public UserAuthDTO(String username,
                             String password,
                             boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
