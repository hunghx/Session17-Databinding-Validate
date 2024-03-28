package ra.databinding.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.databinding.dao.StudentDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class StudentNameConstraint implements ConstraintValidator<StudentNameUnique,String>
{
    @Autowired
    private StudentDao studentDao;
    @Override
    public void initialize(StudentNameUnique studentNameUnique) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !studentDao.existByName(value);
    }
}
