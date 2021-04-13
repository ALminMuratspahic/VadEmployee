package com.VaadinEmployees.front.employee;

import com.VaadinEmployees.common.CustomNumberField;
import com.VaadinEmployees.common.Utils;
import com.VaadinEmployees.controller.CompanyController;
import com.VaadinEmployees.controller.EmployeesController;
import com.VaadinEmployees.entity.Company;
import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.front.MainViewLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@StyleSheet("frontend://css/form-style.css")
@Route(layout = MainViewLayout.class)
public class EmployeesForm extends FormLayout {

    private final EmployeesController employeesController;
    private final CompanyController companyController;
    private final List<Component> components;

    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField position = new TextField("Position");
    private final CustomNumberField salary = new CustomNumberField("Salary");
    private final DatePicker birthDay = new DatePicker("Birthday date");
    private final ComboBox<Company> company = new ComboBox<>("Company");

    private final Binder<Employees> binder = new Binder<>(Employees.class);
    private final Button saveButton = Utils.createButton(this::saveClick, "Save", ButtonVariant.LUMO_PRIMARY, Key.ENTER);
    private final Button cancelButton = Utils.createButton(this::cancelClick, "Cancel", ButtonVariant.LUMO_ERROR);

    public EmployeesForm(EmployeesController employeesController, CompanyController companyController) {
        this.employeesController = employeesController;
        this.companyController = companyController;
        binder.bindInstanceFields(this);
        components = Arrays.asList(firstName, lastName, birthDay, position, salary, company);

        addClassName("employees-form");// 1/3 space for form
        configComponents();
        add(new HorizontalLayout(saveButton, cancelButton));
    }

    private void configComponents() {
        components.forEach(this::add);
        components.forEach(e -> e.getElement().setProperty("clearButtonVisible", true));
        birthDay.setPlaceholder("Select a date");
        company.setPlaceholder("Select company");
        company.setItems(companyController.getAllCompany());
        company.setItemLabelGenerator(Company::getCompanyName);
    }

    private void saveClick() {
        boolean isNew = binder.getBean() == null;
        Employees employee = isNew ? new Employees() : binder.getBean();
        binder.writeBeanIfValid(employee);
        employeesController.saveEmployee(employee);
        Notification.show(isNew ? "Add success" : "Edit success", 1500, Position.MIDDLE);
        clearAllField();
    }

    private void cancelClick() {
        setVisible(false);
        clearAllField();
    }

    private void clearAllField() {
        components.stream().map(e -> (HasValue) e).forEach(e -> e.clear());
        
    }

    public void setEmployee(Employees employee) {
        binder.setBean(employee);
    }
}
