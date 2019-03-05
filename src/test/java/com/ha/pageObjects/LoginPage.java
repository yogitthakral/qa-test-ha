package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;

public class LoginPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public LoginPage(WebDriver driver, GenericFunctions generic){
		
		this.driver=driver;
		this.generic=generic;
		
	}
	
	
	private String signup_email_InputField="//input[@id='email_create']";
	private String signup_submit_btn="//button[@id='SubmitCreate']";
	private String login_email_InputField="//input[@id='email']";
	private String login_passwd_InputField="//input[@id='passwd']";
	private String login_submit_btn="//button[@id='SubmitLogin']";

	public void fill_login_email_InputField(String email) {
		generic.fill(login_email_InputField, email);
	}
	public void fill_login_passwd_InputField(String password) {
		generic.fill(login_passwd_InputField, password);
	}
	
	public void click_login_submit_btn() {
		generic.click(login_submit_btn);
	}
	
	
	public void fill_random_signup_email_InputField() {
		generic.fill(signup_email_InputField, generic.returnRandomEmail());
	}
	
	public void click_signup_submit_btn() {
		generic.click(signup_submit_btn);
	}
	
	
	
	
	
	
	

}
