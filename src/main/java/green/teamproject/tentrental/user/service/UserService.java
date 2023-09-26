package green.teamproject.tentrental.user.service;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.dto.PageResultDTO;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.User;
import org.springframework.ui.Model;

public interface UserService {
    String register(UserDTO dto);
    PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO);
    UserDTO read(String userEmail);
    void remove(String userEmail);
    void modify(UserDTO dto);
    Boolean existsByEmail(String email);

    default User dtoToEntity(UserDTO dto) {
        User entity = User.builder()
                .userEmail(dto.getUserEmail())
                .userPw(dto.getUserPw())
                .userAddressNumber(dto.getUserAddressNumber())
                .userAddressMain(dto.getUserAddressMain())
                .userAddressDetail(dto.getUserAddressDetail())
                .userAddressReference(dto.getUserAddressReference())
                .fromSocial(dto.getFromSocial())
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userName(dto.getUserName())
                .gender(dto.getGender())
                .role(dto.getRole())
                .build();
        return entity;
    }

    default UserDTO entityToDto(User entity) {
        UserDTO dto = UserDTO.builder()
                .userEmail(entity.getUserEmail())
                .userPw(entity.getUserPw())
                .userAddressNumber(entity.getUserAddressNumber())
                .userAddressMain(entity.getUserAddressMain())
                .userAddressDetail(entity.getUserAddressDetail())
                .userAddressReference(entity.getUserAddressReference())
                .fromSocial(entity.getFromSocial())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .userPhoneNumber(entity.getUserPhoneNumber())
                .userName(entity.getUserName())
                .role(entity.getRole())
                .build();
        return dto;
    }
}
