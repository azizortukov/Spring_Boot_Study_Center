package uz.anas.study_center.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        String errorMessage;
        if(exception.getMessage().equalsIgnoreCase("Bad credentials")) {
            errorMessage = "Incorrect password.";
            response.sendRedirect("/login?error=" + errorMessage);
        } else {
            errorMessage = "Number is not registered";
            response.sendRedirect("/login?notFound=" + errorMessage);
        }
    }
}
