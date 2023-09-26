package green.teamproject.tentrental.repository;

import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.entityenum.Role;
import green.teamproject.tentrental.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertUserDummies() {
        IntStream.rangeClosed(1, 300).forEach(i ->
                {
                    User user = User.builder()
                            .userEmail("userEmail..." + i)
                            .userPw("userPw..." + i)
                            .userPhoneNumber("userPhoneNumber..." + i)
                            .userName("userName..." + i)
                            .build();
                    user.addMemberRole(Role.USER);
                    userRepository.save(user);
                });
    }
    @Test
    public void insertAdminDummies() {
        IntStream.rangeClosed(301, 600).forEach(i ->
        {
            User user = User.builder()
                    .userEmail("userEmail..." + i)
                    .userPw("userPw..." + i)
                    .userPhoneNumber("userPhoneNumber..." + i)
                    .userName("userName..." + i)
                    .build();
            user.addMemberRole(Role.USER);
            userRepository.save(user);
        });
    }
    @Test
    public void ReadList() {
        userRepository.findAll().forEach(entity -> System.out.println(entity));
    }

    @Test
    public void Update() {
        User user1 = User.builder()
                .userEmail("userEmail...1")
                .userPw("Dummy Changed")
                .userPhoneNumber("Dummy Changed")
                .userName("Dummy Changed")
                .build();
        user1.addMemberRole(Role.USER);
        userRepository.save(user1);
    }

    @Test
    public void Delete() {
        userRepository.deleteById("userId...1");
    }

    @Test
    public void insertDummies() {
        //1 - 80까지는 USER만 지정
        //81 - 100까지는 USER, ADMIN 지정

        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = User.builder()
                    .userEmail("userEmail" + i)
                    .userName("사용자" + i)
                    .fromSocial(false)
                    .userPw(passwordEncoder.encode("1111"))
                    .build();
            user.addMemberRole(Role.USER); //전체 사용자는 USER 등급 포함
            if(i > 5) {
                user.addMemberRole(Role.ADMIN); //26~30번 사용자는 MANAGER 등급 추가
            }
            userRepository.save(user);
        });

        IntStream.rangeClosed(11, 20).forEach(i -> {
            User user = User.builder()
                    .userEmail("userEmail" + i)
                    .userName("사용자" + i)
                    .fromSocial(true)
                    .userPw(passwordEncoder.encode("1111"))
                    .build();
            user.addMemberRole(Role.USER); //전체 사용자는 USER 등급 포함
            if(i > 15) {
                user.addMemberRole(Role.ADMIN); //26~30번 사용자는 MANAGER 등급 추가
            }
            userRepository.save(user);
        });
    }
}
