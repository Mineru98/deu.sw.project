package kr.ac.deu.computer_engineering.Absenteeism.Management.service.rank;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    // 직급 정보 목록 조회
    @Transactional(readOnly = true)
    public List<Rank> getList(String name) {
        return rankRepository.findAllByRankNameContaining(name);
    }

    // 직급 정보 상세 조회
    @Transactional(readOnly = true)
    public Rank getRankById(Long rankId) throws Exception {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new Exception("존재하지 않는 직급입니다.");
        return rank.get();
    }

    // 직급 정보 등록
    @Transactional
    public void createRank(RankDto dto) throws Exception {
        Rank rank = dto.toEntity();
        rankRepository.save(rank);
    }

    // 직급 정보 수정
    @Transactional
    public void updateRank(Long rankId, RankDto dto) throws Exception {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new Exception("존재하지 않는 부서입니다.");
        rank.ifPresent(t -> {
            t.setRankName(dto.getRankName());
            rankRepository.save(t);
        });
    }

    // 직급 정보 삭제
    @Transactional
    public void deleteRank(Long rankId) throws Exception {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new Exception("존재하지 않는 직급입니다.");
        rank.ifPresent(rankRepository::delete);
    }
}
