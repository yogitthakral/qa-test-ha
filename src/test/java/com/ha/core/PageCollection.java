package com.ha.core;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;
import com.ha.pageObjects.CategoryPage;
import com.ha.pageObjects.CreateAccountPage;
import com.ha.pageObjects.Homepage;
import com.ha.pageObjects.LoginPage;
import com.ha.pageObjects.MyAccountPage;
import com.ha.pageObjects.NavBar;
import com.ha.pageObjects.OrderProductAddressTab;
import com.ha.pageObjects.OrderProductPaymentsTab;
import com.ha.pageObjects.OrderProductShippingTab;
import com.ha.pageObjects.ProductPage;
import com.ha.pageObjects.ShoppingCartSummaryPage;

public class PageCollection {
	private WebDriver driver;
	private GenericFunctions generic;
	
	
	//Collection of page objects which creates page objects only when needed.
	public PageCollection(WebDriver driver,GenericFunctions generic) {

		this.driver = driver;
		this.generic=generic;

	}	
	
	 Homepage homePage;
	 LoginPage loginPage;
	 CreateAccountPage createAccountPage;
	 MyAccountPage myAccountPage;
	 CategoryPage categoryPage;
	 ProductPage productPage;
	 ShoppingCartSummaryPage shoppingCartSummaryPage;
	 OrderProductAddressTab orderProductAddressTab;
	 OrderProductShippingTab orderProductShippingTab;
	 OrderProductPaymentsTab orderProductPaymentsTab;
	 NavBar navBar;



	public Homepage get_Homepage(){
		if(homePage==null) {
			homePage=new Homepage(driver,generic);
		}

		return homePage;
	}
	
	public LoginPage get_LoginPage(){
		if(loginPage==null) {
			loginPage=new LoginPage(driver,generic);
		}

		return loginPage;
	}

	public CreateAccountPage get_CreateAccountPage(){
		if(createAccountPage==null) {
			createAccountPage=new CreateAccountPage(driver,generic);
		}

		return createAccountPage;
	}
	
	public MyAccountPage get_MyAccountPage(){
		if(myAccountPage==null) {
			myAccountPage=new MyAccountPage(driver,generic);
		}

		return myAccountPage;
	}
	
	public CategoryPage get_CategoryPage(){
		if(categoryPage==null) {
			categoryPage=new CategoryPage(driver,generic);
		}

		return categoryPage;
	}
	

	public ProductPage get_ProductPage(){
		if(productPage==null) {
			productPage=new ProductPage(driver,generic);
		}

		return productPage;
	}
	
	public ShoppingCartSummaryPage get_ShoppingCartSummaryPage(){
		if(shoppingCartSummaryPage==null) {
			shoppingCartSummaryPage=new ShoppingCartSummaryPage(driver,generic);
		}

		return shoppingCartSummaryPage;
	}
	
	public OrderProductAddressTab get_OrderProductAddressTab(){
		if(orderProductAddressTab==null) {
			orderProductAddressTab=new OrderProductAddressTab(driver,generic);
		}

		return orderProductAddressTab;
	}
	
	public OrderProductShippingTab get_OrderProductShippingTab(){
		if(orderProductShippingTab==null) {
			orderProductShippingTab=new OrderProductShippingTab(driver,generic);
		}

		return orderProductShippingTab;
	}
	
	public OrderProductPaymentsTab get_OrderProductPaymentsTab(){
		if(orderProductPaymentsTab==null) {
			orderProductPaymentsTab=new OrderProductPaymentsTab(driver,generic);
		}

		return orderProductPaymentsTab;
	}
	
	public NavBar get_NavBar(){
		if(navBar==null) {
			navBar=new NavBar(driver,generic);
		}

		return navBar;
	}
	
	
	
	
}


