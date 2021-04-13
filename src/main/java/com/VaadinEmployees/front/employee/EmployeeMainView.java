package com.VaadinEmployees.front.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.VaadinEmployees.controller.CompanyController;
import com.VaadinEmployees.controller.EmployeesController;
import com.VaadinEmployees.entity.Company;
import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.front.MainViewLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "employee",layout = MainViewLayout.class)
@StyleSheet("frontend://css/form-style.css")
@StyleSheet("frontend://css/style.css")
@PageTitle("Employee")
public class EmployeeMainView extends VerticalLayout{
	
	@Autowired
	EmployeesController employeesController;
	@Autowired
	CompanyController companyController;
	
	private final EmployeesForm forma;
	
	Grid<Employees>gridEmployees = new Grid<>(Employees.class);
	TextField filterText= new TextField("Filter");
	
	HorizontalLayout filterLayout = new HorizontalLayout();
	
	public EmployeeMainView(EmployeesController employeesController, CompanyController companyController) {
		
		this.employeesController=employeesController;
		this.companyController=companyController;
		
		forma = new EmployeesForm(employeesController,companyController);
		forma.setVisible(false);
		configurationGrid();
		
		filterLayout.addClassName("horizontal-filter");
		filterLayout.add(filterText,createRefreshButton(),createAddButton());
		filterLayout.setDefaultVerticalComponentAlignment(Alignment.END);
		add(filterLayout);
		Div content = new Div(gridEmployees,forma);
		addClassName("view-employees");
		content.setSizeFull();
		add(content);
		content.addClassName("divContent");// Uses CSS Flexbox to manage the layout
		
		configFilter();
		setSizeFull();
	}

	public void configurationGrid() {
		//grid.setRowsDraggable(true);
		
		gridEmployees.setSizeFull();
		gridEmployees.setColumns("id","firstName","lastName","birthDay","company","salary","position");
		gridEmployees.removeColumnByKey("company");
		gridEmployees.addColumn(employee->{
			Company company =employee.getCompany();
			return  company==null?"-":company.getCompanyName();
			
		}).setHeader("Company");
		
		gridEmployees.addComponentColumn(this::createRemoveButton).setHeader("Remove");
		//Width for Column 
		gridEmployees.getColumns().forEach(col->col.setAutoWidth(true));
		gridEmployees.addClassName("employees-grid");//2/3 space for grid
		updateList();
		
		gridEmployees.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
		gridEmployees.asSingleSelect().addValueChangeListener(event->{
			 showEmployeeForm(event.getValue(), event.getValue() != null);
		});
	}

	private void showEmployeeForm(Employees employees, boolean visible) {
		forma.setEmployee(employees);
		forma.setVisible(visible);
	}
	
	protected void updateList() {
		gridEmployees.setItems(employeesController.getAllEmployees());
	}
	
	public void configFilter() {
		filterText.setClearButtonVisible(true);
		filterText.setPlaceholder("Name or Last Name");
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		filterText.addValueChangeListener(f->filterUpdate());
		
	}
	public void filterUpdate() {
		gridEmployees.setItems(employeesController.filtriranje(filterText.getValue()));
	}
	
	private Button createRemoveButton(Employees employee) {
		return new Button(VaadinIcon.CLOSE_CIRCLE_O.create(),clickEvent->{
			ListDataProvider<Employees>dataProvider= (ListDataProvider<Employees>) gridEmployees.getDataProvider();
			dataProvider.getItems().remove(employee);
			dataProvider.refreshAll();
			employeesController.delateEmployee(employee.getId());
			  Notification.show("Employee deleted", 2000, Notification.Position.MIDDLE);
		});
	}
	
	private Button createRefreshButton() {
		return new Button("Refresh table",VaadinIcon.REFRESH.create(), clickEvent->{
			updateList();
			
		});
	}

	private Button createAddButton() {
		return new Button("Add Employee", event -> showEmployeeForm(null, true));
	}	
}
