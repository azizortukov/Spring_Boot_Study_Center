package uz.anas.study_center.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.anas.study_center.entity.Group;
import uz.anas.study_center.service.GroupServiceImpl;

@Controller
@RequestMapping("/admin/group")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class GroupController {

    private final GroupServiceImpl groupService;

    @GetMapping
    public String group(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            Model model){
        model.addAttribute("groups", groupService.getAll(pageNumber - 1, pageSize, search));
        return "admin/group";
    }

    @GetMapping("/action")
    public String groupAction() {
        return "admin/action.group";
    }

    @PostMapping("/add")
    public String addGroup(@ModelAttribute Group group){
        groupService.save(group);
        return "redirect:/admin/group";
    }

    //TODO create a group info
    @GetMapping("/info")
    public String groupInfo(Model model){
        return "admin/group-info";
    }

}
