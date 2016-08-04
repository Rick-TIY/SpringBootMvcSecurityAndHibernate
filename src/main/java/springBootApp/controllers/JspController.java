package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springBootApp.entities.UserDao;

@Controller
public class JspController {

    @RequestMapping(value="/jspTest")
    public String jspTest() {
        return "test/test";
    }
}
