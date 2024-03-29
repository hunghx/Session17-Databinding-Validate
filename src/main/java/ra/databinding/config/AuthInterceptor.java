package ra.databinding.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ra.databinding.model.entity.RoleName;
import ra.databinding.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // lay session
        HttpSession session = request.getSession();
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin==null){
            response.sendRedirect("/auth/login");
            return false;
        }else{
            if (userLogin.getRoleSet().stream().anyMatch(role->role.getRoleName().equals(RoleName.ROLE_ADMIN))){
                return true;
            }else {
                response.sendRedirect("/403");
                return false;
            }
        }
    }
}
