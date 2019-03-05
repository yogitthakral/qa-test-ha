package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class ProductPage {
	WebDriver driver;
	GenericFunctions generic;

	public ProductPage(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String addToCart_btn="//button[@name='Submit']";
	private String proceedToCheckout_btn="//div[@id='layer_cart']//a[@title='Proceed to checkout']";

	public void click_addToCart_btn() {
		generic.click(addToCart_btn);
		
	}
	public void click_proceedToCheckout_btn() {
		generic.click(proceedToCheckout_btn);
	}
}
