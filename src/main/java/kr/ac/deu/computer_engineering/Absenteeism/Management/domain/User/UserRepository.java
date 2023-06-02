package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // UC-A10 : 이름으로 직원 목록 조회
    <T> List<T> findAllByNameContaining(String Name, Class<T> type);

    Long countByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    // UC-A11 : 직원 정보 상세 조회
    <T> Optional<T> findById(Long id, Class<T> type);

    List<User> findAllByDateOfLeaveIsNull();
    // UC-A14 : 직원 정보 등록

    // UC-A15 : 직원 정보 수정

    // UC-A16 : 직원 정보 삭제
    void deleteById(Long id);
}
