package green.teamproject.tentrental.user.validator;

import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDTO userDTO = (UserDTO) obj;
        Optional<User> userOptional = userRepository.findByEmail( ((UserDTO) obj).getUserEmail(), false);
        if(!((UserDTO) obj).getUserPw().equals(((UserDTO) obj).getPassword_confirm())){
            //비밀번호와 비밀번호 확인이 다르다면
            errors.rejectValue("password", "key","비밀번호가 일치하지 않습니다.");
        } else if(userRepository.findByEmail(((UserDTO) obj).getUserEmail(),false).isPresent()){
            // 이름이 존재하면
            errors.rejectValue("username", "key","이미 사용자 이름이 존재합니다.");
        }
    }// 비밀번호 검사할때 쓰면 될듯
}
