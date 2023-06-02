package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    //
    <T> List<T> findAllByCompanyNameContaining(String companyName, Class<T> type);

    <T> Optional<T> findById(Long id, Class<T> type);
}