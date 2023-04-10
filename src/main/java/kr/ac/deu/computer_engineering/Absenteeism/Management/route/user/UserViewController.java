package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user;

import kr.ac.deu.computer_engineering.Absenteeism.Management.route.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        model.addAttribute("title", "사용자 목록 페이지");
        return "user/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        model.addAttribute("title", "사용자 상세 페이지");
        UserDto user = new UserDto();
        user.setName("Test");
        model.addAttribute("user", user);
        return "user/item.html";
    }

    @RequestMapping("/myPage")
    public String myPageView(Model model) {
        model.addAttribute("title", "마이페이지");
        return "user/myPage.html";
    }
}
