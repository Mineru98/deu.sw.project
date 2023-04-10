package kr.ac.deu.computer_engineering.Absenteeism.Management.route.team;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/team")
public class TeamViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "team/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "team/item.html";
    }
}
