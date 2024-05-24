package uz.anas.study_center.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import uz.anas.study_center.entity.enums.RoleName;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(RoleName.ROLE_ADMIN.name())) {
                response.sendRedirect("/admin");
                return;
            } else if (authority.getAuthority().equals(RoleName.ROLE_STUDENT.name())) {
                response.sendRedirect("/student");
                return;
            } else if (authority.getAuthority().equals(RoleName.ROLE_MENTOR.name())) {
                response.sendRedirect("/admin/timetable");
                return;
            }
        }
        response.sendRedirect("/");
    }
}
