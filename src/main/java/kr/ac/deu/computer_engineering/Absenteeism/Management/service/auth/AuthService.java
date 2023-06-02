package kr.ac.deu.computer_engineering.Absenteeism.Management.service.auth;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRole;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRoleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto.AuthDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kr.ac.deu.computer_engineering.Absenteeism.Management.enums.AuthorityRole.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserAndRoleRepository userAndRoleRepository;

    @Transactional(readOnly = true)
    public AuthDto login(String username, String password) {
        AuthDto authDto = new AuthDto();
        Long checkUsername = userRepository.countByUsername(username);
        if (checkUsername > 0) {
            String encodingPassword = Encrypt.encode(password);
            Optional<User> checkUsernameAndPassword = userRepository.findByUsernameAndPassword(username, encodingPassword);
            if (checkUsernameAndPassword.isPresent()) {
                User user = checkUsernameAndPassword.get();
                authDto.setTeamId(user.getTeam().getId());
                authDto.setUserId(user.getId());
                List<UserAndRole> roleList = userAndRoleRepository.findAllByUserId(user.getId());
                if (roleList.size() > 0) {
                    List<String> roleArr = new ArrayList<>();
                    for (UserAndRole t : roleList) {
                        if (t.getRole().getRoleName().equals(ROLE_CEO)) {
                            roleArr.add(ROLE_CEO.value());
                        } else if (t.getRole().getRoleName().equals(ROLE_MANAGER)) {
                            roleArr.add(ROLE_MANAGER.value());
                        } else if (t.getRole().getRoleName().equals(ROLE_STAFF)) {
                            roleArr.add(ROLE_STAFF.value());
                        }
                    }
                    authDto.setRoleList(roleArr);
                } else {
                    // 권한이 존재하지 않음.
                    throw new CustomIllegalStateExceptionHandler("해당 사용자의 권한이 존재하지 않습니다.");
                }
            } else {
                // 로그인 계정은 존재하지만, 비밀번호가 틀린 경우
                throw new CustomIllegalStateExceptionHandler("계정과 비밀번호를 다시 확인해주세요.");
            }
        } else {
            // 로그인 계정이 존재하지 않는 경우
            throw new CustomIllegalStateExceptionHandler("계정과 비밀번호를 다시 확인해주세요.");
        }
        return authDto;
    }
}
