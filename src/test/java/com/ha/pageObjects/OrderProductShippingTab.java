package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class OrderProductShippingTab {
	WebDriver driver;
	GenericFunctions generic;

	public OrderProductShippingTab(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String tnc_checkbox="//div[@id='uniform-cgv']";
	private String proceedToCheckout_btn="//button[@name='processCarrier']";

	public void click_tnc_checkbox() {
		generic.click(tnc_checkbox);
	}
	
	public void click_proceedToCheckout_btn() {
		generic.click(proceedToCheckout_btn);
	}
}
