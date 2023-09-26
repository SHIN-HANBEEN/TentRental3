package green.teamproject.tentrental.user.dto;

import green.teamproject.tentrental.user.entityenum.Gender;
import green.teamproject.tentrental.user.entityenum.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

// 푸시테스트
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 자유로운 읽고 쓰기를 위해 @Data 사용
@Data
public class UserDTO {
    //private String userId; //유저아이디는 유저 이메일로 대체
    private String userEmail;
    private String userPw;
    private String password_confirm;
    private String userAddressNumber;
    private String userAddressMain;
    private String userAddressDetail;
    private String userAddressReference;
    private String userPhoneNumber;
    private String userName;
    private Boolean fromSocial;
    private Gender gender;
    private Set<Role> role;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void addMemberRole(Role role) { //역할 추가 메서드
        this.role.add(role);
    } //권한 추가를 해주는 메서드
}
