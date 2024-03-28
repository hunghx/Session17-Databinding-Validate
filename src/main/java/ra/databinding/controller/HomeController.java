package ra.databinding.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.databinding.model.entity.FileUpload;
import ra.databinding.model.entity.Student;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    private final String pathUpload = "C:\\Users\\AD\\IdeaProjects\\Session17-DataBiding-Form\\src\\main\\webapp\\uploads";


    @RequestMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home", "student", new Student());
        modelAndView.addObject("genders",new Boolean[]{true,false,null});
        modelAndView.addObject("fileUpload",new FileUpload());
        return modelAndView;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView doSubmit(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("home", "student", student);
            modelAndView.addObject("genders",new Boolean[]{true,false,null});
            return modelAndView;
        }
        System.out.println("student ....." + student);
        return new ModelAndView("success") ;
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(Model model, @ModelAttribute("file") List<MultipartFile> fileList) throws IOException {
        // logic upload
        List<String> urls = new ArrayList<>();
        if (!fileList.isEmpty()){
            File fileUpload = new File(pathUpload);
            if (!fileUpload.exists()){
                fileUpload.mkdir();
            }

            for (MultipartFile file : fileList){
                urls.add(file.getOriginalFilename());
                // upload
                FileCopyUtils.copy(file.getBytes(),new File(pathUpload+File.separator+file.getOriginalFilename()));

            }
            model.addAttribute("urls",urls);
        }

        return "image";
    }

}
