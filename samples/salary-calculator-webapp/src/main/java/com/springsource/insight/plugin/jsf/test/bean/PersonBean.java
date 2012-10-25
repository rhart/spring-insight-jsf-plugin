package com.springsource.insight.plugin.jsf.test.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean
public class PersonBean implements ActionListener {

	private String name;
	private int age;
	private int yearsOfService;
	private int grade;
	private double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String calculateSalary() {
		setSalary(((age * 0.15) + (grade * 0.25) + (yearsOfService * 0.25)) * 10000);
		return "showSalary";
	}
	
	public void calculateSalaryListener(ActionEvent event) {
	    calculateSalary();
	}

    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        calculateSalary();
    }
    
    public void preRender() {
        System.out.println("XXX PRE RENDER");
    }
}
