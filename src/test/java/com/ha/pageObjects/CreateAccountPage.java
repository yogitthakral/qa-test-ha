package com.ha.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ha.genericFunctionsPack.GenericFunctions;
import com.ha.genericFunctionsPack.XlsReader;

public class CreateAccountPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public CreateAccountPage(WebDriver driver, GenericFunctions generic){
		
		this.driver=driver;
		this.generic=generic;
		
	}
	
	private String gender_Mr_radioBtn="//input[@id='id_gender1']";
	private String gender_Mrs_radioBtn="//input[@id='id_gender2']";
	private String customerFirstName_InputField="//input[@id='customer_firstname']";
	private String customerLastName_InputField="//input[@id='customer_lastname']";
	private String password_InputField="//input[@id='passwd']";
	private String dob_days_dropdown="//select[@id='days']";
	private String dob_months_dropdown="//select[@id='months']";
	private String dob_years_dropdown="//select[@id='years']";
	private String company_inputField="//input[@id='company']";
	private String address1_inputField="//input[@id='address1']";
	private String address2_inputField="//input[@id='address2']";
	private String city_inputField="//input[@id='city']";
	private String state_drop_down="//select[@id='id_state']";
	private String postcode_inputField="//input[@id='postcode']";
	private String other_inputField="//textarea[@id='other']";
	private String phone_inputField="//input[@id='phone']";
	private String phone_mobile_inputField="//input[@id='phone_mobile']";
	private String alias_inputField="//input[@id='alias']";
	private String submitAccount_btn="//button[@id='submitAccount']";
	
	
	
	
	
	
	public void click_gender_radioBtn(String gender) {
		if(gender.equalsIgnoreCase("male")) {
			generic.click(gender_Mr_radioBtn);
		}else {
		generic.click(gender_Mrs_radioBtn);
	}}
	
	public void fill_customerFirstName_InputField(String value_To_Fill) {
		generic.fill(customerFirstName_InputField, value_To_Fill);
	}
	
	public void fill_customerLastName_InputField(String value_To_Fill) {
		generic.fill(customerLastName_InputField, value_To_Fill);
	}
	
	public void fill_password_InputField(String value_To_Fill) {
		generic.fill(password_InputField, value_To_Fill);
	}
	
	public void fill_company_inputField(String value_To_Fill) {
		generic.fill(company_inputField, value_To_Fill);
	}
	
	public void fill_address1_inputField(String value_To_Fill) {
		generic.fill(address1_inputField, value_To_Fill);
	}
	
	public void fill_address2_inputField(String value_To_Fill) {
		generic.fill(address2_inputField, value_To_Fill);
	}
	
	public void fill_city_inputField(String value_To_Fill) {
		generic.fill(city_inputField, value_To_Fill);
	}
	
	public void fill_postcode_inputField(String value_To_Fill) {
		generic.fill(postcode_inputField, value_To_Fill);
	}
	
	public void fill_other_inputField(String value_To_Fill) {
		generic.fill(other_inputField, value_To_Fill);
	}
	
	public void fill_phone_inputField(String value_To_Fill) {
		generic.fill(phone_inputField, value_To_Fill);
	}
	
	public void fill_phone_mobile_inputField(String value_To_Fill) {
		generic.fill(phone_mobile_inputField, value_To_Fill);
	}
	
	public void fill_alias_inputField(String value_To_Fill) {
		generic.fill(alias_inputField, value_To_Fill);
	}
	
	public void select_state_drop_down(String value_To_Select) {
		generic.selectFromDropdownByVisibleText(state_drop_down, value_To_Select);
	}
	
	
	public void click_submitAccount_btn() {
		generic.click(submitAccount_btn);
	}
	
	
	
public void select_dob_from_dropdown(String dob_with_hyphen_separator) {
	String[] dobArray=dob_with_hyphen_separator.split("\\-");
	if(dobArray.length<3) {
		throw new RuntimeException("DOB is incorrect "+dob_with_hyphen_separator + " it should have been given in dd-mm-yyyy format");
	}
	generic.selectFromDropdown(dob_days_dropdown, dobArray[0]);
	generic.selectFromDropdown(dob_months_dropdown, dobArray[1]);
	generic.selectFromDropdown(dob_years_dropdown, dobArray[2]);
}


public void fill_registerForm(XlsReader xlsReaderObj,String sheetName, int rowNumber) {
	click_gender_radioBtn(xlsReaderObj.getCellData(sheetName, "Gender", rowNumber));
	fill_customerFirstName_InputField(xlsReaderObj.getCellData(sheetName, "FirstName", rowNumber));
	fill_customerLastName_InputField(xlsReaderObj.getCellData(sheetName, "LastName", rowNumber));
	fill_password_InputField(xlsReaderObj.getCellData(sheetName, "Password", rowNumber));
	select_dob_from_dropdown(xlsReaderObj.getCellData(sheetName, "DOB", rowNumber));
	fill_company_inputField(xlsReaderObj.getCellData(sheetName, "Company", rowNumber));
	fill_address1_inputField(xlsReaderObj.getCellData(sheetName, "Address1", rowNumber));
	fill_address2_inputField(xlsReaderObj.getCellData(sheetName, "AddressrowNumber", rowNumber));
	fill_city_inputField(xlsReaderObj.getCellData(sheetName, "City", rowNumber));
	select_state_drop_down(xlsReaderObj.getCellData(sheetName, "State", rowNumber));
	fill_postcode_inputField(xlsReaderObj.getCellData(sheetName, "Postcode", rowNumber));
	fill_other_inputField(xlsReaderObj.getCellData(sheetName, "Other", rowNumber));
	fill_phone_inputField(xlsReaderObj.getCellData(sheetName, "Phone", rowNumber));
	fill_phone_mobile_inputField(xlsReaderObj.getCellData(sheetName, "Mobile", rowNumber));
	fill_alias_inputField(xlsReaderObj.getCellData(sheetName, "Alias", rowNumber));
	click_submitAccount_btn();
	
}
	
	
	
	
	
	
	
	

}
