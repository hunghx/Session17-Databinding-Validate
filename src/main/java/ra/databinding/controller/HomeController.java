package ra.databinding.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.databinding.dao.StudentDao;
import ra.databinding.model.entity.FileUpload;
import ra.databinding.model.entity.Student;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home", "student", new Student());
        return modelAndView;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView doSubmit(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("home", "student", student);
            return modelAndView;
        }
        System.out.println("student ....." + student);
        studentDao.add(student);
        return new ModelAndView("success");
    }


}
