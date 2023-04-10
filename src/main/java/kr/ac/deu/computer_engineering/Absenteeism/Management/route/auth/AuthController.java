package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그인",
            description = "사용자 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그아웃",
            description = "사용자 로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
