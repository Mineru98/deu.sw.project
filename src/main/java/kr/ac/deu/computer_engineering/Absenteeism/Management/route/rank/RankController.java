package kr.ac.deu.computer_engineering.Absenteeism.Management.route.rank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.rank.RankService;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rank")
public class RankController {
    final private RankService rankService;

    @Tag(name = "직급")
    @Operation(summary = "직급 목록 조회", description = "직급 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(@RequestParam(required = false) String q) {
        List<Rank> result = rankService.getList(q);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 상세 조회", description = "직급 상세 조회")
    @GetMapping("/{rankId}")
    public ResponseEntity<?> getItemById(@PathVariable Long rankId) {
        Rank rank = rankService.getRankById(rankId);
        return new ResponseEntity<>(rank, HttpStatus.OK);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 정보 생성", description = "직급 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(@RequestBody RankDto dto) {
        rankService.createRank(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 직급 정보 수정", description = "직급 정보 수정")
    @PutMapping("/{rankId}")
    public ResponseEntity<?> updateItemById(
            @PathVariable Long rankId,
            @RequestBody RankDto dto) {
        rankService.updateRank(rankId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 직급 정보 삭제", description = "직급 정보 삭제")
    @DeleteMapping("/{rankId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long rankId) {
        rankService.deleteRank(rankId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
