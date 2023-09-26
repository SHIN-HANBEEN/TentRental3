package green.teamproject.tentrental.service;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.dto.PageResultDTO;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.entityenum.Role;
import green.teamproject.tentrental.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService service;

    @Test
    public void testRegister() {
        UserDTO userDTO = UserDTO.builder()
                .userEmail("userEmail_1")
                .userPw("userPw_1")
                .userPhoneNumber("01012345678")
                .userName("userName_1")
                .build();
        userDTO.addMemberRole(Role.USER);
        System.out.println(service.register(userDTO));
    }
// slack test 4
    @Test
    public void testList() {
        // PageRequestDTO 생성
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResultDTO<UserDTO, User> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("=======================================");
        for (UserDTO userDTO : resultDTO.getDtoList()) {
            System.out.println(userDTO);
        }

        System.out.println("======================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("n") // 검색 조건 u, e, n, r, ur, ure ...
                .keyword("2") // 검색 키워드
                .build();
        PageResultDTO<UserDTO, User> resultDto = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDto.isPrev());
        System.out.println("NEXT: " + resultDto.isNext());
        System.out.println("TOTAL: " + resultDto.getTotalPage());

        System.out.println("-----------------------------------");
        for (UserDTO userDTO : resultDto.getDtoList()) {
            System.out.println(userDTO);
        }

        System.out.println("-----------------------------------");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }
}
