package in.nareshit.raghu.controller;

import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.IUserService;
import in.nareshit.raghu.util.MyMailUtil;
import in.nareshit.raghu.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private MyMailUtil mailUtil;

    @GetMapping("/login")
    public String showLogin() {
        return "UserLogin";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "UserProfile";
    }

    @GetMapping("/setup")
    public String setup(HttpSession session, Principal p) {

        //read current username
        String username = p.getName();

        //load user object
        User user = userService.findByUsername(username).get();

        //store in http session
        session.setAttribute("userOb", user);

        //setting timeout
        session.setMaxInactiveInterval(10 * 60);

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

    @GetMapping("/showForgot")
    public String showForgot(){
        return "UserNewPwdGenerator";
    }

    @PostMapping("genNewPwd")
    public String userNewPwdGen(
            @RequestParam String email,
            Model model) {
        Optional<User> opt = userService.findByUsername(email);
        if (opt.isPresent()) {

            //read  user object
            User user = opt.get();

            //Generate New Password
            String pwd = userUtil.genPwd();

            //encode and update in DB
            userService.updateUserPwd(pwd, user.getId());

            //send message to ui
            model.addAttribute("message", "Password updated successfully! Check your email...");

            //Send email to user
            if (user.getId() != null)
                new Thread(() -> {
                    String text = "Your username is " + user.getUsername() + ", and new password is " + pwd;
                    mailUtil.send(user.getUsername(), "Password Updated!", text);
                }).start();

            //if user is not present in DB
        } else {
            model.addAttribute("message", "User Not Found!");
        }
        return "UserNewPwdGenerator";
    }
}
