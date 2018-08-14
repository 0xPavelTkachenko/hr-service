package www.service.hrservice.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import www.service.hrservice.interceptor.exception.ForbiddenException;
import www.service.hrservice.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ForbiddenException {
        if (authService.isAdmin(request.getParameter("key")) || authService.isClient(request.getParameter("login"), request.getParameter("password"))) {
            return true;
        } else {
            throw new ForbiddenException();
        }
    }

}
