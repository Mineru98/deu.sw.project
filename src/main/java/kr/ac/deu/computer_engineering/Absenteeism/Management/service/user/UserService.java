package kr.ac.deu.computer_engineering.Absenteeism.Management.service.user;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.TeamRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Encrypt;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Format;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TeamRepository teamRepository;
    private final RankRepository rankRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    // 직원 목록 조회
    @Transactional(readOnly = true)
    public List<UserMapping> getList(String name) {
        return userRepository.findAllByNameContaining(name, UserMapping.class);
    }

    // 직원 상세 조회
    @Transactional(readOnly = true)
    public UserMapping getUserById(Long userId) {
        Optional<UserMapping> user = userRepository.findById(userId, UserMapping.class);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        String contactNumber = Formatter.convertPhoneNumber(user.get().getContactNumber());
        user.get().setContactNumber(contactNumber);
        return user.get();
    }

    // 직원 등록
    @Transactional
    public void createUser(UserDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getRankId());
        if (company.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 회사입니다.");
        Optional<Team> team = teamRepository.findById(dto.getRankId());
        if (team.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 부서입니다.");
        Optional<Rank> rank = rankRepository.findById(dto.getRankId());
        if (rank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급입니다.");
        User user = dto.toEntity(team.get(), rank.get(), company.get());
        userRepository.save(user);
    }

    // 직원 수정
    @Transactional
    public void updateUser(Long userId, UserDto dto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        user.ifPresent(t -> {
            t.setUsername(dto.getUsername());
            t.setName(dto.getName());
            t.setPassword(Encrypt.encode(dto.getPassword()));
            t.setDateOfJoin(dto.getDateOfJoin());
            t.setDateOfLeave(dto.getDateOfLeave());
            t.setContactNumber(dto.getContactNumber());
            t.setNetworkMacAddress(dto.getNetworkMacAddress());
            t.setIsManager(dto.getIsManager());
            t.setIsOfficer(dto.getIsOfficer());
            if (dto.getCompanyId() != null) {
                Optional<Company> company = companyRepository.findById(dto.getCompanyId());
                company.ifPresent(t::setCompany);
            }

            if (dto.getTeamId() != null) {
                Optional<Team> team = teamRepository.findById(dto.getTeamId());
                team.ifPresent(t::setTeam);
            }

            if (dto.getRankId() != null) {
                Optional<Rank> rank = rankRepository.findById(dto.getRankId());
                rank.ifPresent(t::setRank);
            }
            userRepository.save(t);
        });
    }

    @Transactional
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        user.ifPresent(userRepository::delete);
    }
}
