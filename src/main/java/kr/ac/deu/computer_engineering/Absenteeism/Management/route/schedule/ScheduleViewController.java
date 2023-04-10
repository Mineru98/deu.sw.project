package kr.ac.deu.computer_engineering.Absenteeism.Management.route.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "schedule/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "schedule/item.html";
    }
}
