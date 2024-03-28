package ra.databinding.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherConstraint implements ConstraintValidator<PasswordMatcher,Object> {
    private String passField;
    private String confirmPassField;

    @Override
    public void initialize(PasswordMatcher passwordMatcher) {
        this.passField = passwordMatcher.pass();
        this.confirmPassField=passwordMatcher.confirmPass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        Object passValue = new BeanWrapperImpl(o).getPropertyValue(passField);
        Object confirmPassvalue = new BeanWrapperImpl(o).getPropertyValue(confirmPassField);

        if (passValue==null|| !passValue.equals(confirmPassvalue)){
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(confirmPassField)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
