package com.ha.core;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;



public class UrlProvider extends ConfigDetails {


	WebDriver driver;
	GenericFunctions generic;

	public UrlProvider(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
	}

	
	//provides Website Under test url to the test case , in case the environment is test , url will be replaced by the url supplied in the command line before execution.
	public void Load_TestWebSite_Url() {
		if(env.equalsIgnoreCase("test")){
			driver.get(urlstr);
		}
		else if(env.equalsIgnoreCase("live")){
			driver.get("http://automationpractice.com/index.php");
		}
	}
}

