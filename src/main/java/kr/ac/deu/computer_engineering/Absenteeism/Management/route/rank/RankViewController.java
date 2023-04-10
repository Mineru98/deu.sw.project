package kr.ac.deu.computer_engineering.Absenteeism.Management.route.rank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rank")
public class RankViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "rank/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "rank/item.html";
    }
}
