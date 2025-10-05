package NatkaBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String renderIndex() {
        return "pages/home/index";
    }

    @GetMapping("/about")
    public String renderAbout() {
        return "pages/home/about";
    }

    @GetMapping("/services")
    public String renderServices() {
        return "pages/home/services";
    }


}
