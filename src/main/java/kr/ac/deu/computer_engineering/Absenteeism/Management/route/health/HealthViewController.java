package kr.ac.deu.computer_engineering.Absenteeism.Management.route.health;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/health")
public class HealthViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "health/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "health/item.html";
    }
}
