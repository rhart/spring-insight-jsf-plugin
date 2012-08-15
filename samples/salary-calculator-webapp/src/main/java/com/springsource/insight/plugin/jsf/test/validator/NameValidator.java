package com.springsource.insight.plugin.jsf.test.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="NameValidator")
public class NameValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage("Name must be entered"));
        }

        if (!(value instanceof String)) {
            throw new ValidatorException(new FacesMessage("Name must be a String"));
        }

        String input = (String) value;
        if (input.length() < 2) {
            throw new ValidatorException(new FacesMessage("Name must be at least 2 characters"));
        }
    }
}
