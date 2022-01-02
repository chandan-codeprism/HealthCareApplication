package in.nareshit.raghu.controller;

import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "UserLogin";
    }

    @GetMapping("/setup")
    public String setup(HttpSession session, Principal p) {

        //read current username
        String username = p.getName();

        //load user object
        User user =userService.findByUsername(username).get();

        //store in http session
        session.setAttribute("userOb",user);
        return "UserHome";
    }
}
