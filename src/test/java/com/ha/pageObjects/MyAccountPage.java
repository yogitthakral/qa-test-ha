package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class MyAccountPage {

	WebDriver driver;
	GenericFunctions generic;

	public MyAccountPage(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;

	}

	private String myAccount_pageHeading="//h1[@class='page-heading']";
	private String myAccount_name="//a[@class='account']";
	private String myAccount_welcome_message="//p[@class='info-account']";
	private String logout_Link="//a[@class='logout']";


	public String getText_myAccount_pageHeading() {
		return generic.getElementText(myAccount_pageHeading);
	}

	public String getText_myAccount_name() {
		return generic.getElementText(myAccount_name);
	}


	public String getText_myAccount_welcome_message() {
		return generic.getElementText(myAccount_welcome_message);
	}

	public boolean isVisible_logout_Link() {
		return generic.isVisible(logout_Link);
	}
	



}
