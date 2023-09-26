package green.teamproject.tentrental.user.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.dto.PageResultDTO;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.QUser;
import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.entityenum.Role;
import green.teamproject.tentrental.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;;

import java.security.Principal;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Boolean existsByEmail(String email) {
        Boolean isExists;
        Optional<User> userOptional = repository.findByEmail(email, false);
        isExists = userOptional.isPresent();
        return isExists;
    }

    @Override
    public String register(UserDTO dto) {
        log.info("DTO................");
        log.info(dto);

        User entity = dtoToEntity(dto);

        log.info("Entity................");
        log.info(entity);

        repository.save(entity);

        return entity.getUserEmail();
    }
    @Override
    public PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색 조건 처리
        Page<User> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<User, UserDTO> fn = (entity -> entityToDto(entity));
        // entity to dto 의 결과로 dtoList 를 반환하게 된다.
        return new PageResultDTO<>(result, fn);
    }
    @Override
    public UserDTO read(String userEmail) {
        Optional<User> result = repository.findById(userEmail);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }
    @Override
    public void remove(String userEmail) {
        repository.deleteById(userEmail);
    }
    @Override
    public void modify(UserDTO dto) {
        Optional<User> result = repository.findById(dto.getUserEmail());
        if(result.isPresent()) {
            User entity = result.get();
            entity.changeUserAddress(dto.getUserAddressNumber(), dto.getUserAddressMain(), dto.getUserAddressDetail(), dto.getUserAddressReference());
            entity.changeUserPhoneNumber(dto.getUserPhoneNumber());
            entity.changeUserPw(dto.getUserPw());
            repository.save(entity);
        }
    }
    private BooleanBuilder getSearch(PageRequestDTO requestDTO) { //Querydsl 처리
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        String r_type = requestDTO.getR_type();
        String r_keyword = requestDTO.getR_keyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QUser qUser = QUser.user;

        BooleanExpression expression = qUser.userEmail.isNotEmpty(); // userEmail isNotEmpty( )조건 생성
        booleanBuilder.and(expression); // 조건 탑재

        if(type == null || type.trim().isEmpty() && r_type == null && r_type.trim().isEmpty()) { // 검색 조건이 없는 경우에는
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();
//        if(type.contains("u")) { // type = u 일 때는 아이디 검색
//            conditionBuilder.or(qUser.userId.contains(keyword));
//        }
        if(type.contains("e")) { // type = e 일 때는 이메일 검색
            conditionBuilder.or(qUser.userEmail.contains(keyword));
        }
        if(type.contains("n")) { // type = n 일 때는 이름 검색
            conditionBuilder.or(qUser.userName.contains(keyword));
        }
        if(r_type.contains("r")) { // r_type = r 일 때는 권한 검색 // r_keyword 는 ROLE_USER 와 같이 입력해야함
            conditionBuilder.and(qUser.role.contains(Role.valueOf(r_keyword))); // 2개의 별도의 검색창을 만들어서 and 조건 검색을 해주었다.
        }


        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
