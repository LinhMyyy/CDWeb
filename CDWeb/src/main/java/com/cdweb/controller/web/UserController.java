package com.cdweb.controller.web;

import com.cdweb.converter.UserConverter;
import com.cdweb.dto.UserDTO;
import com.cdweb.repository.ConfirmationTokenRepository;
import com.cdweb.repository.UserRepository;
import com.cdweb.service.IUserService;
import com.cdweb.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;


    @PostMapping(value = "/dang-ki")
    public ModelAndView registerUser(@ModelAttribute("User") UserDTO user, HttpServletRequest request) {
        UserDTO userDTO = userService.sendMail(user, request);
        ModelAndView mav = new ModelAndView("web/dang-ki.html");
        if (userDTO != null) {
            mav.addObject("message", "Mời bạn xác nhận tài khoản qua email: " + userDTO.getEmail());
        } else {
            mav.addObject("message", "Email " + userDTO.getEmail() + " đã được sử dụng! ");

        }
        return mav;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmEmail(@RequestParam(name = "token") String confirmationToken, Model model) {
        UserDTO user = userService.confirmEmail(confirmationToken);
        return new ModelAndView("web/dang-nhap.html");
    }

    @GetMapping("/check-mail")
    public UserDTO checkMail(@RequestParam(name = "email") String email) {
        UserDTO user = userService.findByEmail(email);
        if (user != null) user.setPassword("");
        return user;
    }

    @GetMapping("/dang-nhap")
    public ModelAndView loginPage(@RequestParam(name = "error", required = false, defaultValue = "false") boolean error) {
        ModelAndView mav = new ModelAndView("web/dang-nhap.html");
        if (error) {
            mav.addObject("error", "Email hoặc mật khẩu không chính xác!");
            return mav;
        }

        return mav;
    }

    @GetMapping("/dang-ki")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("web/dang-ki.html");
        mav.addObject("message");
        return mav;
    }

    @GetMapping("/user")
    public UserDTO user(Principal principal) {
        if (principal != null) {
            UserDTO user = this.userService.findByEmail(principal.getName());
            user.setPassword("");
            return user;
        } else {
            return null;
        }
    }

    @GetMapping("/quen-mat-khau")
    public ModelAndView forgetPasswordPage() {
        ModelAndView mav = new ModelAndView("web/quen-mat-khau.html");
        mav.addObject("message", "Điền vào email của bạn để yêu cầu một mật khẩu mới. Một Email sẽ được gửi đến địa chỉ\n" +
                "                                này để xác minh địa chỉ Email của bạn.");
        return mav;
    }

    @GetMapping("/send-mail-forget-password")
    public ModelAndView newPassword(@RequestParam(name = "email", required = false, defaultValue = "false") String email, HttpServletRequest request) {
        System.out.println(email);
        UserDTO userDTO = userService.sendMailForgetPassword(email, request);
        ModelAndView mav = new ModelAndView("web/quen-mat-khau.html");
        if (userDTO == null) {
            mav.addObject("message", "Tài khoản không tồn tại!");
        } else {
            mav.addObject("message", "Vui lòng check email để hoàn thành quá trình lấy lại mật khẩu!");
        }
        return mav;
    }

    @RequestMapping(value = "/forget-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forgetPassword(@RequestParam(name = "token") String confirmationToken) {
        UserDTO user = userService.confirmEmailForgetPassword(confirmationToken);
        ModelAndView mav = new ModelAndView("web/doi-mat-khau.html");
        mav.addObject("forget", true);
        mav.addObject("email", user.getEmail());
        return mav;
    }

    @PostMapping("/change-information")
    public ModelAndView changePassword(@ModelAttribute("user") UserDTO user, Principal principal) {
        UserDTO userDTO = userService.changePassword(user);
        if (principal == null) {
            return new ModelAndView("web/dang-nhap.html");
        } else {
            return new ModelAndView("web/thong-tin-ca-nhan.html");
        }
    }

    @GetMapping("/doi-mat-khau")
    public ModelAndView changePasswordPage(Principal principal) {
        ModelAndView mav = new ModelAndView("web/doi-mat-khau.html");
        mav.addObject("forget", false);
        mav.addObject("email", principal.getName());
        return mav;
    }

    @GetMapping("/check-password")
    public boolean checkPassword(@RequestParam(name = "password") String password, Principal principal) {
        return this.userService.checkPass(password, principal.getName());
    }

}
