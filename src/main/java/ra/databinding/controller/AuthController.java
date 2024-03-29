package ra.databinding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ra.databinding.model.dto.request.FormLogin;
import ra.databinding.model.dto.request.FormRegister;
import ra.databinding.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @RequestMapping("/register")
    public ModelAndView formRegister(){
        return new ModelAndView("register","user",new FormRegister());
    }
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String doRegister(@ModelAttribute FormRegister user){
        authService.register(user);
        return "redirect:/auth/login";
    }
    @RequestMapping("/login")
    public ModelAndView formLogin(){
        return new ModelAndView("login","formLogin",new FormLogin());
    }
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String doLogin(@ModelAttribute FormLogin formLogin, Model model){
        if (authService.login(formLogin)){
            return "redirect:/";
        }else {
            model.addAttribute("err","username or pass incorrect");
            model.addAttribute("formLogin",formLogin);
            return "login";
        }
    }
}
