package kr.ac.deu.computer_engineering.Absenteeism.Management.service.allowance;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto.AllowanceOfRankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final RankRepository rankRepository;
    private final AllowanceOfRankRepository allowanceOfRankRepository;

    // 직급별 수당 정보 목록 조회
    @Transactional(readOnly = true)
    public List<AllowanceOfRank> getList(Long rankId) {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isPresent()) {
            return allowanceOfRankRepository.findAllByRank(rank.get(), AllowanceOfRank.class);
        } else {
            throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급별 수당입니다.");
        }
    }

    // 직급별 수당 정보 상세 조회
    @Transactional(readOnly = true)
    public AllowanceOfRank getAccountByRankIdAndId(Long rankId, Long accountId) {
        Optional<AllowanceOfRank> account = allowanceOfRankRepository.findByRankIdAndId(rankId, accountId, AllowanceOfRank.class);
        if (account.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급별 수당입니다.");
        return account.get();
    }

    // 직급별 수당 정보 등록
    @Transactional
    public void createAllowanceOfRank(AllowanceOfRankDto dto) {
        Optional<Rank> rank = rankRepository.findById(dto.getRankId());
        rank.ifPresent(t -> {
            AllowanceOfRank account = dto.toEntity(t);
            allowanceOfRankRepository.save(account);
        });
    }

    // 직급별 수당 정보 수정
    @Transactional
    public void updateAllowanceOfRank( Long accountId, AllowanceOfRankDto dto) {
        Optional<AllowanceOfRank> allowanceOfRank = allowanceOfRankRepository.findById(accountId, AllowanceOfRank.class);
        if (allowanceOfRank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급별 수당입니다.");
        allowanceOfRank.ifPresent(t -> {
            t.setAmount(dto.getAmount());
            t.setMaxInterval(dto.getMaxInterval());
            t.setMinInterval(dto.getMinInterval());
            if (dto.getRankId() != null) {
                Optional<Rank> rank = rankRepository.findById(dto.getRankId());
                rank.ifPresent(t::setRank);
            }
            allowanceOfRankRepository.save(t);
        });
    }

    // 직급별 수당 정보 삭제
    @Transactional
    public void deleteAllowanceOfRank(Long accountId) {
        Optional<AllowanceOfRank> allowanceOfRank = allowanceOfRankRepository.findById(accountId, AllowanceOfRank.class);
        if (allowanceOfRank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급별 수당입니다.");
        allowanceOfRank.ifPresent(allowanceOfRankRepository::delete);
    }
}
