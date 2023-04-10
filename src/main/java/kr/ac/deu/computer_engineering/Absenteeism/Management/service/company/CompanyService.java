package kr.ac.deu.computer_engineering.Absenteeism.Management.service.company;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
}
