package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class OrderProductAddressTab {
	WebDriver driver;
	GenericFunctions generic;

	public OrderProductAddressTab(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String proceedToCheckout_btn="//button[@name='processAddress']";

	public void click_proceedToCheckout_btn() {
		generic.click(proceedToCheckout_btn);
	}
}
