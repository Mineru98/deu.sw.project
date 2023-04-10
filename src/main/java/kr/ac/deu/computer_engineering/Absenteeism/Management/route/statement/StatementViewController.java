package kr.ac.deu.computer_engineering.Absenteeism.Management.route.statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/statement")
public class StatementViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "statement/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "statement/item.html";
    }
}
