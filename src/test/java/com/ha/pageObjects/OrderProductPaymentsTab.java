package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class OrderProductPaymentsTab {
	WebDriver driver;
	GenericFunctions generic;

	public OrderProductPaymentsTab(WebDriver driver, GenericFunctions generic){

		this.driver=driver;
		this.generic=generic;
	}
	
	private String payViaWire_link="//a[@class='bankwire']";
	private String orderConfirmation_btn="//*[@id='cart_navigation']/button";
	private String productCompletionPageHeading="//h1[@class='page-heading']";
	private String shippingTabHeading="//li[@class='step_done step_done_last four']";
	private String paymentTabHeading="//li[@id='step_end' and @class='step_current last']";
	private String orderCompletionMessage="//*[@class='cheque-indent']/strong";

	public void click_payViaWire_link() {
		generic.click(payViaWire_link);
	}
	
	public void click_orderConfirmation_btn() {
		generic.click(orderConfirmation_btn);
	}
	
	public String getText_productCompletionPageHeading() {
		return generic.getElementText(productCompletionPageHeading);
	}
	
	public boolean isVisible_shippingTabHeading() {
		return generic.isVisible(shippingTabHeading);
	}
	
	public boolean isVisible_paymentTabHeading() {
		return generic.isVisible(paymentTabHeading);
	}
	
	public boolean isVisible_orderCompletionMessage() {
		return generic.isVisible(orderCompletionMessage);
	}
	
}
