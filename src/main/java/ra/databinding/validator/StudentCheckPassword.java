package ra.databinding.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.databinding.model.entity.Student;

@Component
public class StudentCheckPassword implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        if (student.getPass()==null || !student.getPass().equals(student.getConfirmPass())){
            errors.rejectValue("confirmPass","err.notmatch", "khong khop mat khau");
        }
    }
}
