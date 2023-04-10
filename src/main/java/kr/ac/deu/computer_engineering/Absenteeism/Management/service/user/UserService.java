package kr.ac.deu.computer_engineering.Absenteeism.Management.service.user;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
