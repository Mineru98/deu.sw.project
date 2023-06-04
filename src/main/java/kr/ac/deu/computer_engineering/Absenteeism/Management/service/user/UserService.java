package kr.ac.deu.computer_engineering.Absenteeism.Management.service.user;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.AccountRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistoryRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Role.Role;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Role.RoleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.ScheduleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement.StatementRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.TeamRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRole;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole.UserAndRoleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Encrypt;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TeamRepository teamRepository;
    private final RankRepository rankRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
    private final AccountRepository accountRepository;
    private final ScheduleRepository scheduleRepository;
    private final StatementRepository statementRepository;
    private final HealthCheckHistoryRepository healthCheckHistoryRepository;

    // 직원 목록 조회
    @Transactional(readOnly = true)
    public List<UserMapping> getList(String name, HttpSession session) {
        if (RoleValidate.isRoleManager(session)) {
            return userRepository.findAllByNameContainingAndTeamId(name, RoleValidate.getTeamId(session), UserMapping.class);
        } else {
            return userRepository.findAllByNameContaining(name, UserMapping.class);
        }
    }

    // 직원 상세 조회
    @Transactional(readOnly = true)
    public UserMapping getUserById(Long userId) {
        Optional<UserMapping> user = userRepository.findById(userId, UserMapping.class);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        return user.get();
    }

    // 직원 등록
    @Transactional
    public void createUser(UserDto dto, HttpSession session) {
        // 회사 정보를 찾아본다.
        Optional<Company> company = companyRepository.findById(dto.getCompanyId());
        if (company.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 회사입니다.");
        // 아이디 중복 체크를 한다.
        Optional<User> user = userRepository.findByUsername(dto.getUsername(), User.class);
        if (user.isPresent()) throw new CustomIllegalStateExceptionHandler("이미 존재하는 계정입니다.");
        // 존재하는 부서를 확인한다.
        Optional<Team> team = teamRepository.findById(dto.getTeamId());
        if (team.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 부서입니다.");
        if (RoleValidate.isRoleManager(session)) {
            // 로그인한 사용자가 부서관리자일 경우에는 사용자의 부서 ID랑 입력한 부서 ID와 동일하지 않는 경우는 등록하지 못하도록 한다.
            if (dto.getTeamId() != RoleValidate.getTeamId(session)) {
                throw new CustomIllegalStateExceptionHandler("다른 부서의 직원을 등록할 수 없습니다.");
            }
        }
        // 직급이 존재하는지 확인한다.
        Optional<Rank> rank = rankRepository.findById(dto.getRankId());
        if (rank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급입니다.");
        if (dto.getRankId() == 2L) {
            // 등록하고자 하는 직급이 부서관리자라면, 시스템 권한이 부서관리자, 사원이어야 합니다.
            if (!checkContainsBoth(dto.getRoleIdList(), 2L, 3L)) throw new CustomIllegalStateExceptionHandler("직급이 부서관리자이면, 시스템권한은 사원과 부서장 모두 선택해야합니다.");
        } else if (dto.getRankId() == 3L) {
            // 등록하고자 하는 직급이 사원이라면, 시스템 권한이 사원이어야 합니다.
            if (dto.getRoleIdList().stream().filter(t -> !t.equals(3L)).collect(Collectors.toList()).size() > 0) throw new CustomIllegalStateExceptionHandler("직급이 사원이면, 시스템권한은 사원만 선택해야합니다.");
        }
        // 직급이 부서관리자인 경우에만 관리자 여부를 True로 한다.
        dto.setIsManager(dto.getRankId() == 2L);
        User result = dto.toEntity(team.get(), rank.get(), company.get());
        userRepository.save(result);
        List<UserAndRole> uar = new ArrayList<>();
        for(Long roleId: dto.getRoleIdList()) {
            UserAndRole item = new UserAndRole();
            item.setUser(result);
            // 대표이사인 경우에는 시스템 권한 변경이 불가능하도록 합니다.
            if (dto.getRankId() != 1L && roleId != 1L) {
                Optional<Role> role = roleRepository.findById(roleId);
                if (role.isPresent()) {
                    item.setRole(role.get());
                    uar.add(item);
                } else {
                    throw new CustomIllegalStateExceptionHandler("시스템 권한을 등록하는 과정에서 문제가 발생했습니다.");
                }
            } else {
                throw new CustomIllegalStateExceptionHandler("대표이사 시스템권한은 설정할 수 없습니다.");
            }
        }
        userAndRoleRepository.saveAll(uar);
    }


    public boolean checkContainsBoth(List<Long> numbers, Long target1, Long target2) {
        boolean containsTarget1 = false;
        boolean containsTarget2 = false;
        for (Long number : numbers) {
            if (number.equals(target1)) containsTarget1 = true;
            else if (number.equals(target2)) containsTarget2 = true;
            if (containsTarget1 && containsTarget2) return true; // 둘 다 존재하면 true 반환
        }
        return false; // 둘 중 하나라도 존재하지 않으면 false 반환
    }

    // 직원 수정
    @Transactional
    public void updateUser(Long userId, UserDto dto, HttpSession session) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        if (RoleValidate.isRoleManager(session)) {
            if (!Objects.equals(user.get().getTeam().getId(), RoleValidate.getTeamId(session))) {
                throw new CustomIllegalStateExceptionHandler("해당 직원을 수정할 수 있는 권한이 없습니다.");
            }
        }
        user.ifPresent(t -> {
            t.setUsername(dto.getUsername());
            t.setName(dto.getName());
            t.setPassword(Encrypt.encode(dto.getPassword()));
            t.setDateOfJoin(dto.getDateOfJoin());
            t.setDateOfLeave(dto.getDateOfLeave());
            t.setContactNumber(dto.getContactNumber());
            t.setNetworkMacAddress(dto.getNetworkMacAddress());
            t.setIsManager(dto.getRankId() == 2L);
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

            if (t.getRank().getId() != 1L) {
                userAndRoleRepository.deleteAllByUserId(t.getId());
                List<UserAndRole> uar = new ArrayList<>();
                for(Long roleId: dto.getRoleIdList()) {
                    UserAndRole item = new UserAndRole();
                    item.setUser(t);
                    if (t.getRank().getId() != 1L && roleId != 1L) {
                        Optional<Role> role = roleRepository.findById(roleId);
                        if (role.isPresent()) {
                            item.setRole(role.get());
                            uar.add(item);
                        } else {
                            throw new CustomIllegalStateExceptionHandler("시스템 권한을 등록하는 과정에서 문제가 발생했습니다.");
                        }
                    } else {
                        throw new CustomIllegalStateExceptionHandler("대표이사 시스템권한은 설정할 수 없습니다.");
                    }
                }
                userAndRoleRepository.saveAll(uar);
            }
        });
    }

    @Transactional
    public void deleteUser(Long userId, HttpSession session) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        if (RoleValidate.isRoleManager(session)) {
            if (!Objects.equals(user.get().getTeam().getId(), RoleValidate.getTeamId(session))) {
                throw new CustomIllegalStateExceptionHandler("해당 직원을 삭제할 수 있는 권한이 없습니다.");
            }
            if (Objects.equals(user.get().getId(), RoleValidate.getUserId(session))) {
                throw new CustomIllegalStateExceptionHandler("자기 자신은 삭제할 수 없습니다.");
            }
        }
        if (RoleValidate.isRoleCeo(session)) {
            if (Objects.equals(user.get().getId(), RoleValidate.getUserId(session))) {
                throw new CustomIllegalStateExceptionHandler("대표이사는 삭제할 수 없습니다.");
            }
        }
        if (user.isPresent()) {
            accountRepository.deleteAllByUserId(user.get().getId());
            scheduleRepository.deleteAllByUserId(user.get().getId());
            statementRepository.deleteAllByUserId(user.get().getId());
            healthCheckHistoryRepository.deleteAllByUserId(user.get().getId());
            userAndRoleRepository.deleteAllByUserId(user.get().getId());
            userRepository.delete(user.get());
        }
    }
}
