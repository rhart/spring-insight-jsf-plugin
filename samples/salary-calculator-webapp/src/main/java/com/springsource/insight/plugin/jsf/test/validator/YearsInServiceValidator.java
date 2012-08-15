package com.springsource.insight.plugin.jsf.test.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="YearsInServiceValidator")
public class YearsInServiceValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            throw new ValidatorException(new FacesMessage("Years in Service must be entered"));
        }

        int years = 0;
        if (value instanceof String) {
            String input = (String) value;
            if (!input.matches("^[0-9]*$")) {
                throw new ValidatorException(new FacesMessage("Years in Service must be numeric"));
            }
            years = Integer.parseInt(input);
        } else if (value instanceof Integer) {
            years = ((Integer) value).intValue();
        } else {
            throw new ValidatorException(new FacesMessage("Years in Service must be numeric"));
        }
        
        if (years < 1 || years > 47) {
            throw new ValidatorException(new FacesMessage("Years in Service must be between 1 and 47"));
        }
    }
}
