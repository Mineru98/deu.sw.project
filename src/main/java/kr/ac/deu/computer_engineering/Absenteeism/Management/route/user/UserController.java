package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.user.UserService;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<?> getItemList(
            HttpServletRequest request,
            @RequestParam(required = false) String q) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            List<UserMapping> userList = userService.getList(q, session);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 상세 조회",
            description = "직원 상세 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getItemById(
            HttpServletRequest request,
            @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if (Objects.equals(RoleValidate.getUserId(session), userId) || RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            UserMapping user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 정보 생성",
            description = "직원 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(
            HttpServletRequest request,
            @Valid @RequestBody UserDto dto,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            userService.createUser(dto, session);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 직원 정보 수정",
            description = "직원 정보 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateItemById(
            HttpServletRequest request,
            @Valid @RequestBody UserDto dto,
            @PathVariable Long userId,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        Long sUserId = RoleValidate.getUserId(session);
        if (Objects.equals(sUserId, userId) || RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            userService.updateUser(userId, dto, session);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "직원")
    @Operation(
            summary = "직원 Id로 직원 정보 삭제",
            description = "직원 정보 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteItemById(
            HttpServletRequest request,
            @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            userService.deleteUser(userId, session);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
