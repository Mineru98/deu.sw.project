package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.CreateUserDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UpdateUserDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @Tag(name = "직원")
    @Operation(
            summary = "직원 목록 조회",
            description = "직원 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(@RequestParam(required = false) String q) {
        List<User> userList = userService.getList(q);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 상세 조회",
            description = "직원 상세 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getItemById(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 정보 생성",
            description = "직원 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(@RequestBody CreateUserDto dto) throws Exception {
        userService.createUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 직원 정보 수정",
            description = "직원 정보 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateItemById(
            @RequestBody UpdateUserDto dto,
            @PathVariable Long userId) throws Exception {
        userService.updateUser(userId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 직원 정보 삭제",
            description = "직원 정보 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
