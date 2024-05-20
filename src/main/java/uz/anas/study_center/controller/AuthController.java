package uz.anas.study_center.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.model.request.UserRequestDto;
import uz.anas.study_center.service.UserServiceImpl;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final HttpSession session;
    private final UserServiceImpl userService;

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,
                        @RequestParam(required = false) String phoneNumber,
                        @RequestParam(required = false) String notFound,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("notFound", notFound);
        return "auth/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping("/sign-up")
    public String register(@ModelAttribute UserRequestDto userRequestDto, Model model) {
        model.addAttribute("user", userRequestDto);
        if (userService.getByPhoneNumber(userRequestDto.getPhoneNumber()) != null) {
            model.addAttribute("phoneInUse", true);
            return "auth/sign-up";
        }
        if (!userRequestDto.getPhoneNumber().matches("\\d{9}")) {
            model.addAttribute("phoneNumberWrong", true);
            return "auth/sign-up";
        }
        if (!userService.confirmPassword(userRequestDto)) {
            model.addAttribute("confirmPasswordWrong", true);
            return "auth/sign-up";
        }
        userService.saveUserRequestDto(userRequestDto);
        return "auth/login";
    }

}
