package green.teamproject.tentrental.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder; //BCryptPasswordEncoder 로 설정한 PasswordEncoder 를 가져온다.

    @Test
    public void testEncoder() {
        String password = "1111";
        String enPw = passwordEncoder.encode(password); //BCruptPasswordEncoder 로 암호화된 비밀번호는 복호화가 불가능하므로, 이런식으로 미리 변수에 담아서 암호화가 정상적으로 되었는지 확인할 수 있다.
        System.out.println("enPw: " + enPw);
        boolean matchResult = passwordEncoder.matches(password, enPw); //사용자가 입력한 비밀번호와 암호화되어 서버에 저장된 비밀번호가 같은지 확인하는 함수는 matches(password, enPw) 입니다.
        System.out.println("matchResult: " + matchResult);
    }
}
