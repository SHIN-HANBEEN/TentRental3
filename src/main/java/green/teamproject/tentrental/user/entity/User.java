package green.teamproject.tentrental.user.entity;

// slack test 8

import com.querydsl.core.BooleanBuilder;
import green.teamproject.tentrental.common.entity.BaseEntity;
import green.teamproject.tentrental.user.entityenum.Gender;
import green.teamproject.tentrental.user.entityenum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class User extends BaseEntity {
    @Id
    @Column(length = 40)
    private final String userEmail;
    
    @Column(length = 200, nullable = false) //PW 는 암호화 되면 길어지므로 length 길게 설정
    private String userPw;

    @Column(length = 10, nullable = true)
    private String userAddressNumber;

    @Column(length = 50, nullable = true)
    private String userAddressMain;

    @Column(length = 50, nullable = true)
    private String userAddressDetail;

    @Column(length = 50, nullable = true)
    private String userAddressReference;
    
    @Column(nullable = true)
    private String userPhoneNumber;
    
    @Column(nullable = true)
    private final String userName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private final Gender gender;

    @Column(length = 100, nullable = true)
    private String userDirectory;

    @Column(nullable = false) //소셜 회원 여부
    private Boolean fromSocial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.LAZY) //지금 이 객체의 일부로만 사용되기 때문에 JPA의 @ElementCollection 을 이용합니다.
    @Builder.Default
    private Set<Role> role = new HashSet<>(); //Role 타입값을 처리하기 위해서 Set<> 타입으로 지정하였습니다.

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.userPw = passwordEncoder.encode(this.userPw);
    }

    public void addMemberRole(Role role) { //역할 추가 메서드
        this.role.add(role);
    } //권한 추가를 해주는 메서드

    public void changeUserPw(String userPw) {
        this.userPw = userPw;
    }

//    public void changeUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
    public void changeUserAddress(String userAddressNumber, String userAddressMain, String userAddressDetail, String userAddressReference) {
        this.userAddressNumber = userAddressNumber;
        this.userAddressMain = userAddressMain;
        this.userAddressDetail = userAddressDetail;
        this.userAddressReference = userAddressReference;
    }
    public void changeUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
