package uz.anas.study_center.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.anas.study_center.entity.enums.PayType;
import uz.anas.study_center.model.request.PaymentRequestDto;
import uz.anas.study_center.service.PaymentServiceImpl;
import uz.anas.study_center.service.UserServiceImpl;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/payment")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final UserServiceImpl userService;

    @GetMapping
    public String group(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        model.addAttribute("payments", paymentService.getAll(pageNumber - 1, pageSize, fromDate, toDate));
        return "admin/payment";
    }

    @GetMapping("/action")
    public String action(Model model) {
        model.addAttribute("paymentTypes", PayType.values());
        model.addAttribute("students", userService.getAllStudents());
        return "admin/action.payment";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute PaymentRequestDto paymentRequestDto) {
        paymentService.savePaymentRequestDto(paymentRequestDto);
        return "redirect:/admin/payment";
    }

}
