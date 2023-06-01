package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.aop.AuthAdvice;
import kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto.AuthDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto.LoginDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    final private AuthService authService;

    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그인",
            description = "사용자 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            HttpServletRequest request,
            @RequestBody(required = true) LoginDto dto
    ) throws Exception {
        AuthDto auth = authService.login(dto.getUsername(), dto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("userId", auth.getUserId());
        session.setAttribute("roleList", auth.getRoleList());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그아웃",
            description = "사용자 로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
