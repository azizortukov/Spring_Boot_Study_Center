package uz.anas.study_center.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.study_center.service.UserServiceImpl;

@Controller
@RequestMapping("/admin/student")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class StudentAdminController {

    private final UserServiceImpl userService;

    @GetMapping
    public String student(@RequestParam(required = false) String search,
                          @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                          Model model){
        model.addAttribute("students", userService.getAllStudentsContaining(pageNumber - 1, pageSize, search));
        return "admin/student";
    }

}
