package com.ha.CustomReporter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.ha.core.BaseTestClass;



public class ReportingDriverProvider {
	
	
	
	/**
	 * Method to return driver that is being used
	 * @param tr
	 * @return driver
	 */

	public <T> T getDriver(ITestResult tr) {
		try {
			Object currentClass = tr.getInstance();
			WebDriver driver = ((BaseTestClass) currentClass).getDriver();
			
			return (T) driver;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
