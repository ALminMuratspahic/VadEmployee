package com.VaadinEmployees.front;

import com.VaadinEmployees.front.company.CompanyForm;
import com.VaadinEmployees.front.employee.EmployeeMainView;
import com.VaadinEmployees.front.employee.EmployeesForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
@Route("")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")

public class MainViewLayout extends AppLayout{
	
	Tabs tabs = new Tabs();
	
	public MainViewLayout() {
		Image img =
				new Image("https://www.flaticon.com/svg/vstatic/svg/197/197524.svg?"
						+ "token=exp=1617300187~hmac=7875c9ece3b1f0a4acc061913d78869d", "Flag");
		img.setHeight("40px");
		HorizontalLayout headerLayout = new HorizontalLayout(new DrawerToggle(), img);
		headerLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
	
		addToNavbar(headerLayout);
		addMenuTab("Employee", EmployeeMainView.class);
		addMenuTab("Company", CompanyForm.class);
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		addToDrawer(tabs);
		
	}
	
	private void addMenuTab(String label, Class<? extends Component> targetClass) {
		Tab tab =new Tab(new RouterLink(label, targetClass));
		tabs.add(tab);	
	}

}
