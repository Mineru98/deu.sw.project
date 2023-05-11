package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    //
    List<Company> findAllByCompanyNameContaining(String companyName);

    Optional<Company> findById(Long id);
}