package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.ResponseDTO;
import kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto.AuthDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto.LoginDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ) {
        AuthDto auth = authService.login(dto.getUsername(), dto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("userId", auth.getUserId());
        session.setAttribute("teamId", auth.getTeamId());
        session.setAttribute("roleList", auth.getRoleList());
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, auth), HttpStatus.OK);
    }

    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그아웃",
            description = "사용자 로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK), HttpStatus.OK);
    }
}
