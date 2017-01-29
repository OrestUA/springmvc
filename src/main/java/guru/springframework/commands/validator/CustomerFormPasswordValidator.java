package guru.springframework.commands.validator;

import guru.springframework.commands.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Fudjitsu on 29.01.2017.
 */
@Component
public class CustomerFormPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CustomerForm customerForm = (CustomerForm) object;
        String pass = customerForm.getPasswordText();
        String confPass = customerForm.getPasswordTextConf();

        if (!pass.equals(confPass)) {
            errors.rejectValue("passwordText", "customerForm.passwordText.missMatch");
            errors.rejectValue("passwordTextConf", "customerForm.passwordTextConf.missMatch");
        }
    }

}
