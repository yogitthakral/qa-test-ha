package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class ShoppingCartSummaryPage {
	WebDriver driver;
	GenericFunctions generic;

	public ShoppingCartSummaryPage(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String proceedToCheckout_btn="//p[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']";

	public void click_proceedToCheckout_btn() {
		generic.click(proceedToCheckout_btn);
	}
}
