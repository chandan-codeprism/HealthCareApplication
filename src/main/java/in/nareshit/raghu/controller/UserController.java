package in.nareshit.raghu.controller;

import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        User user = userService.findByUsername(username).get();

        //store in http session
        session.setAttribute("userOb", user);

        return "UserHome";
    }

    @GetMapping("/showPwdUpdate")
    public String showPwdUpdate() {
        return "UserPwdUpdate";
    }

    @PostMapping("/pwdUpdate")
    public String updatePwd(
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        //Read current user from session
        User user = (User) session.getAttribute("userOb");
        //read user id
        Long userId = user.getId();
        //make service call
        userService.updateUserPwd(password, userId);
        //TODO mail sending to updated password
        model.addAttribute("message", "Password Updated!");
        return "UserPwdUpdate";
    }
}
