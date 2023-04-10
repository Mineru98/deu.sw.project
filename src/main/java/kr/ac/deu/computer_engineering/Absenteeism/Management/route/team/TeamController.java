package kr.ac.deu.computer_engineering.Absenteeism.Management.route.team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/team")
public class TeamController {
    @Tag(name = "부서")
    @Operation(
            summary = "부서 목록 조회",
            description = "부서 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 상세 조회",
            description = "부서 목록 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 정보 생성",
            description = "부서 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 부서 정보 수정",
            description = "부서 목록 조회")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "부서")
    @Operation(
            summary = "부서 Id로 부서 정보 삭제",
            description = "부서 목록 조회")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
