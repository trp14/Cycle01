package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody String index() {
        return "Welcome to the home page!";
    }
    @RequestMapping("/signup")
    public @ResponseBody String signup() {
        return "Signup Page..!";
    }   
    @RequestMapping("/about")
    public @ResponseBody String about() {
        return "About Page..!";
    }        
    @RequestMapping("/resources")
    public @ResponseBody String resources() {
        return "RESOURCES..!";
    }           
}
