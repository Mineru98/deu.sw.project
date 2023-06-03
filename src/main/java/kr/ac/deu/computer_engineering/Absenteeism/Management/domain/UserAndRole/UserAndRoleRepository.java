package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.UserAndRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAndRoleRepository extends JpaRepository<UserAndRole, Long> {
    List<UserAndRole> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
