package uz.anas.study_center.controller.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class IndexController {

    @GetMapping
    public String index(){
        return "admin/admin.navbar";
    }

}
