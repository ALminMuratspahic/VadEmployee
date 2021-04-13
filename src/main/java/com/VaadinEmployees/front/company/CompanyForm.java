package com.VaadinEmployees.front.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties.Gridfs;

import com.VaadinEmployees.controller.CompanyController;
import com.VaadinEmployees.entity.Company;
import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.front.MainViewLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "company",layout = MainViewLayout.class)
@StyleSheet("frontend://css/companyStyle.css")
@PageTitle("Company")
public class CompanyForm extends VerticalLayout {
	
	@Autowired
	CompanyController companyController;
	
	Button	buttonInsert = new Button("Add company");
	TextField companyName= new TextField("Company name");
	HorizontalLayout layout1 = new HorizontalLayout();
	HorizontalLayout layout2 = new HorizontalLayout();
	Grid<Company>gridCompany= new Grid<>(Company.class);
	
	public CompanyForm(CompanyController companyController) {
		
		this.companyController=companyController;
		configGrid();
		layout1.add(companyName,buttonInsert);
		
		companyName.focus();
		
		layout1.setWidthFull();
		layout2.setWidthFull();
		layout1.setAlignItems(Alignment.END);
		layout2.setAlignItems(Alignment.END);
		
		add(layout1);
		add(gridCompany);
		addClassName("main-view");
		setSizeFull();
		configButton();

	}
	
	public void configButton() {	
		
		buttonInsert.addClickListener(c->{
			companyController.saveCompany(new Company(companyName.getValue()));
			companyName.clear();
			Notification.show("Company added",1500,Position.MIDDLE);
			updateCompany();
		});
	}
	
	private Button createRemoveButton(Company company) {
		return new Button(VaadinIcon.CLOSE_CIRCLE_O.create(), click->{
			ListDataProvider<Company>dataProvider=getDataprovider();
			dataProvider.getItems().remove(company);
			dataProvider.refreshAll();
			companyController.deleteCompany(company.getId());
			Notification.show("Company deleted", 1500, Position.MIDDLE);
		});
	}
	
	private void configGrid() {
		gridCompany.setColumns("id","companyName");
		gridCompany.addComponentColumn(this::createRemoveButton).setHeader("Remove");
		gridCompany.getColumns().forEach(col->col.setAutoWidth(true));
		gridCompany.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS);
		
		updateCompany();
	}
	private void updateCompany() {
		gridCompany.setItems(companyController.getAllCompany());
	}
	private ListDataProvider<Company>getDataprovider(){
		return (ListDataProvider<Company>) gridCompany.getDataProvider();
	}
	
	
}
