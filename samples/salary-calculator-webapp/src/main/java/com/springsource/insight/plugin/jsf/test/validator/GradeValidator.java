package com.springsource.insight.plugin.jsf.test.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="GradeValidator")
public class GradeValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage("Grade must be entered"));
        }

        int grade = 0; 
        if (value instanceof String) {
            String input = (String) value;
            if (!input.matches("^[0-9]*$")) {
                throw new ValidatorException(new FacesMessage("Grade must be numeric"));
            }
            grade = Integer.parseInt(input);
        } else if (value instanceof Integer) {
            grade = ((Integer) value).intValue();
        } else {
            throw new ValidatorException(new FacesMessage("Grade must be numeric"));
        }
        
        if (grade < 1 || grade > 10) {
            throw new ValidatorException(new FacesMessage("Grade must be between 1 and 10"));
        }
    }
}
