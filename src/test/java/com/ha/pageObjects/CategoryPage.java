package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class CategoryPage {
	WebDriver driver;
	GenericFunctions generic;

	public CategoryPage(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String faded_thsirt_link="//h5/a[@title='Faded Short Sleeve T-shirts']";

	public void click_faded_thsirt_link() {
		generic.click(faded_thsirt_link);
	}
}
