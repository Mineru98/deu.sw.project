package kr.ac.deu.computer_engineering.Absenteeism.Management.route.team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.dto.TeamDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.dto.TeamDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/team")
public class TeamController {
    final private TeamService teamService;

    @Tag(name = "부서")
    @Operation(
            summary = "부서 목록 조회",
            description = "부서 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(@RequestParam(required = false) String q) {
        List<Team> result = teamService.getList(q);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 상세 조회",
            description = "부서 상세 조회")
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getItemById(@PathVariable Long teamId) throws Exception {
        Team team = teamService.getTeamById(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 정보 생성",
            description = "부서 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(
            @RequestBody TeamDto dto
    ) throws Exception {
        teamService.createTeam(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 부서 정보 수정",
            description = "부서 정보 수정")
    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateItemById(
            @PathVariable Long teamId,
            @RequestBody TeamDto dto) throws Exception {
        teamService.updateTeam(teamId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 부서 정보 삭제",
            description = "부서 정보 삭제")
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long teamId) throws Exception {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
