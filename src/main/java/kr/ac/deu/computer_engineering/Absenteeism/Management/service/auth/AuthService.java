package kr.ac.deu.computer_engineering.Absenteeism.Management.service.auth;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Role.RoleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
}
