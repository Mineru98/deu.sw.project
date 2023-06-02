package kr.ac.deu.computer_engineering.Absenteeism.Management.service.company;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    // 회사 정보 목록 조회
    @Transactional(readOnly = true)
    public List<Company> getList(String name) {
        return companyRepository.findAllByCompanyNameContaining(name);
    }

    // 회사 정보 상세 조회
    @Transactional(readOnly = true)
    public Company getCompanyById(Long companyId) {
        Optional<Company> comp = companyRepository.findById(companyId);
        if (comp.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 회사입니다.");
        return comp.get();
    }

    // 회사 정보 등록
    @Transactional
    public void createCompany(CompanyDto dto) {
        Company company = dto.toEntity();
        companyRepository.save(company);
    }

    // 회사 정보 수정
    @Transactional
    public void updateCompany(Long companyId, CompanyDto dto) {
        Optional<Company> comp = companyRepository.findById(companyId);
        if (comp.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 회사입니다.");
        comp.ifPresent(t -> {
            t.setCompanyName(dto.getCompanyName());
            t.setLat(dto.getLat());
            t.setLng(dto.getLng());
            t.setIsMain(dto.getIsMain());
            t.setSettlementDate(dto.getSettlementDate());
            t.setAddress(dto.getAddress());
            companyRepository.save(t);
        });
    }

    // 회사 정보 삭제
    @Transactional
    public void deleteCompany(Long companyId) {
        Optional<Company> comp = companyRepository.findById(companyId);
        if (comp.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 회사입니다.");
        comp.ifPresent(companyRepository::delete);
    }
}
