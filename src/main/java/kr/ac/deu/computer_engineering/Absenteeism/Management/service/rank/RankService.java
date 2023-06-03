package kr.ac.deu.computer_engineering.Absenteeism.Management.service.rank;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;

    // 직급 정보 목록 조회
    @Transactional(readOnly = true)
    public List<Rank> getList(HttpSession session) {
        List<Long> idList = new ArrayList<Long>();
        if (RoleValidate.isRoleCeo(session)) {
            // 대표이사 직급 ID가 1이기 때문에 대표이사 권한으로 접근 시 직급 중 대표이사 직급을 제외한 모든 직급 정보를 조회하도록 한다.
            idList.add(1L);
            return rankRepository.findAllByIdNotIn(idList);
        } else if (RoleValidate.isRoleManager(session)) {
            // 대표이사 직급 ID가 1이고, 부서장 직급 ID가 2이기 때문에 부서장 권한으로 접근 시 직급 중 대표이사와 부서장 직급을 제외한 모든 직급 정보를 조회하도록 한다.
            idList.add(1L);
            idList.add(2L);
            return rankRepository.findAllByIdNotIn(idList);
        }
        return null;
    }

    // 직급 정보 상세 조회
    @Transactional(readOnly = true)
    public Rank getRankById(Long rankId) {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급입니다.");
        return rank.get();
    }

    // 직급 정보 등록
    @Transactional
    public void createRank(RankDto dto) {
        Rank rank = dto.toEntity();
        Long checkSum = rankRepository.countByRankName(dto.getRankName());
        if (checkSum > 0) throw new CustomIllegalStateExceptionHandler("이미 존재하는 직급명입니다.");
        rankRepository.save(rank);
    }

    // 직급 정보 수정
    @Transactional
    public void updateRank(Long rankId, RankDto dto) {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급입니다.");
        rank.ifPresent(t -> {
            t.setRankName(dto.getRankName());
            rankRepository.save(t);
        });
    }

    // 직급 정보 삭제
    @Transactional
    public void deleteRank(Long rankId) {
        Optional<Rank> rank = rankRepository.findById(rankId);
        if (rank.isEmpty()) throw new CustomIllegalStateExceptionHandler("존재하지 않는 직급입니다.");
        rank.ifPresent(rankRepository::delete);
    }
}
