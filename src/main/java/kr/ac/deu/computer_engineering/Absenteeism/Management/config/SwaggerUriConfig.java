package kr.ac.deu.computer_engineering.Absenteeism.Management.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/docs")
public class SwaggerUriConfig {

    @GetMapping("")
    public String api() {
        return "redirect:/swagger-ui/index.html";
    }
}
