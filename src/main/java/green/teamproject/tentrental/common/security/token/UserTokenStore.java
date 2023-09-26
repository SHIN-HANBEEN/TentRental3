package green.teamproject.tentrental.common.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component //Spring Bean 으로 등록을 시켜서 Handler 에서 쓸 수 있도록 설정한다. Singleton 으로 관리되므로 하나만 생성되게 된다.
public class UserTokenStore {
    static final Map<String, String> tokenToEmailMap = new HashMap<>(); //token 을 저장하는 Map 은 반드시 final 을 붙여야 한다. 안 그러면 맵이 여러개 생성되면서 어떤 키 값을 던져줘도 새로운 맵이 생성되버린다.

//    @Autowired
//    UserTokenStore userTokenStore; //Login Success Handler 에서 생성된 userTokenStore 를 주입받는다.

    public void storeTokenMapping(String token, String userEmail) { // Method to store the mapping
        tokenToEmailMap.put(token, userEmail); //토큰과 이메일 Map 객체에 저장
    }

    public String getEmailForToken(String token) { // Method to retrieve the email associated with a token
        return tokenToEmailMap.get(token);
    }
}
