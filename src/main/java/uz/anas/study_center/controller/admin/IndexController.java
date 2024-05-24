package uz.anas.study_center.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_MENTOR')")
public class IndexController {

    //default page of admin
    @GetMapping
    public String index(){
        return "admin/admin.navbar";
    }

}
