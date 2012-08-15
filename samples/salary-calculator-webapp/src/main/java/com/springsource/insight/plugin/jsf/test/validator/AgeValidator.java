package com.springsource.insight.plugin.jsf.test.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="AgeValidator")
public class AgeValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage("Age must be entered"));
        }

        int age = 0; 
        if (value instanceof String) {
            String input = (String) value;
            if (!input.matches("^[0-9]*$")) {
                throw new ValidatorException(new FacesMessage("Age must be numeric"));
            }
            age = Integer.parseInt(input);
        } else if (value instanceof Integer) {
            age = ((Integer) value).intValue();
        } else {
            throw new ValidatorException(new FacesMessage("Age must be numeric"));
        }
        
        if (age < 18) {
            throw new ValidatorException(new FacesMessage("You must be over 18"));
        }
        if (age > 65) {
            throw new ValidatorException(new FacesMessage("You must be under 65"));
        }
    }
}
