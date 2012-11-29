package com.springsource.insight.plugin.jsf.test.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class CalculateSalaryActionListener implements ActionListener {

    
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance(); 
        PersonBean person = context.getApplication().evaluateExpressionGet(context, "#{personBean}", PersonBean.class);
        person.calculateSalary();
    }
}
