package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class NavBar {
	
	WebDriver driver;
	GenericFunctions generic;

	public NavBar(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;

	}
	private String Women_Link_NavBar="//a[text()='Women']";
	
	
	public void click_Women_Link_NavBar() {
		generic.click(Women_Link_NavBar);
	}
	

}
