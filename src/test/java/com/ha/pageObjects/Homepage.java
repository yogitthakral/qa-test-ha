package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;
import com.ha.genericFunctionsPack.GenericFunctions;

public class Homepage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public Homepage(WebDriver driver, GenericFunctions generic){
		
		this.driver=driver;
		this.generic=generic;
		
	}
	
	
	private String login_btn="//a[@class='login']";
	
	
	
	
	public void click_login_btn() {
		generic.click(login_btn);
	}
	
	
	
	
	
	
	

}
