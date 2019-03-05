package com.ha.testcases;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.ha.core.ConfigDetails;
import com.ha.core.PageCollection;
import com.ha.core.UrlProvider;
import com.ha.genericFunctionsPack.GenericFunctions;
import com.ha.genericFunctionsPack.XlsReader;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class WebTest extends ConfigDetails {
	WebDriverWait wait;
	GenericFunctions generic;
	PageCollection pageCollection;
	XlsReader xlsReader;

	String existingUserEmail = "hA_challenge_1551553374999@yopmail.com";
	//base 64 encoded password , decode function is used in the test case to decode it on demand
	String existingUserPassword = "UVdFUlRZ";

	
	
	
String log4jConfPath =System.getProperty("user.dir")+"/log4j.properties";
	
	public static Logger log4j ;
	
	@BeforeSuite
	public void initialize_ConfigVariables(){
		System.out.println("the path for log4j is"+log4jConfPath);
		//Configure Log4j.properties
		PropertyConfigurator.configure(log4jConfPath);
		
		
		//Intialize Log4j Object 
		log4j = Logger.getLogger("devpinoyLogger");
		
		//Reading the attributes from Config.properties and mapping them to the 
		try {
			FileInputStream input = new FileInputStream("Config.properties");
			Properties	prop = new Properties();
			prop.load(input);

			Driver_Type = prop.getProperty("Driver_Type");
			log4j.debug(Driver_Type);
			
			env = prop.getProperty("Environment");
			log4j.debug(env);

			urlstr = prop.getProperty("urlstr");
			log4j.debug(urlstr);

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		generic= new GenericFunctions(driver);
		driver=generic.StartDriver(Driver_Type);
		pageCollection= new PageCollection(driver, generic);
		UrlProvider url= new UrlProvider(driver, generic);
		xlsReader= new XlsReader(System.getProperty("user.dir")+"/src/test/java/com/ha/testData/testData.xls");
		url.Load_TestWebSite_Url();
	}

	@Test
	public void TC_001_signUpTest() {
		String name = xlsReader.getCellData("signInTest", "FirstName", 2);
		String surname = xlsReader.getCellData("signInTest", "LastName", 2);
		pageCollection.get_Homepage().click_login_btn();
		pageCollection.get_LoginPage().fill_random_signup_email_InputField();
		pageCollection.get_LoginPage().click_signup_submit_btn();
		pageCollection.get_CreateAccountPage().fill_registerForm(xlsReader,"signInTest", 2);
		assertEquals(pageCollection.get_MyAccountPage().getText_myAccount_pageHeading(),"MY ACCOUNT");
		assertEquals(pageCollection.get_MyAccountPage().getText_myAccount_name(),name + " " + surname);
		assertTrue(pageCollection.get_MyAccountPage().getText_myAccount_welcome_message().contains("Welcome to your account."));
		assertTrue(pageCollection.get_MyAccountPage().isVisible_logout_Link());	
		assertTrue(generic.isStringContainedInUrl("controller=my-account"));
	}
	
	

	@Test
	public void TC_002_logInTest() {
		pageCollection.get_Homepage().click_login_btn();
		pageCollection.get_LoginPage().fill_login_email_InputField(existingUserEmail);
		pageCollection.get_LoginPage().fill_login_passwd_InputField(new String( Base64.decodeBase64(existingUserPassword)));
		pageCollection.get_LoginPage().click_login_submit_btn();
		assertEquals(pageCollection.get_MyAccountPage().getText_myAccount_pageHeading(),"MY ACCOUNT");
		assertEquals(pageCollection.get_MyAccountPage().getText_myAccount_name(),"Firstname Lastname");
		assertTrue(pageCollection.get_MyAccountPage().getText_myAccount_welcome_message().contains("Welcome to your account."));
		assertTrue(pageCollection.get_MyAccountPage().isVisible_logout_Link());	
		assertTrue(generic.isStringContainedInUrl("controller=my-account"));
	}

	@Test
	public void TC_003_checkoutTest() {
		pageCollection.get_Homepage().click_login_btn();
		pageCollection.get_LoginPage().fill_login_email_InputField(existingUserEmail);
		pageCollection.get_LoginPage().fill_login_passwd_InputField(new String( Base64.decodeBase64(existingUserPassword)));
		pageCollection.get_LoginPage().click_login_submit_btn();
		pageCollection.get_NavBar().click_Women_Link_NavBar();
		pageCollection.get_CategoryPage().click_faded_thsirt_link();
		pageCollection.get_ProductPage().click_addToCart_btn();
		pageCollection.get_ProductPage().click_proceedToCheckout_btn();
		pageCollection.get_ShoppingCartSummaryPage().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductAddressTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductShippingTab().click_tnc_checkbox();
		pageCollection.get_OrderProductShippingTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductPaymentsTab().click_payViaWire_link();
		pageCollection.get_OrderProductPaymentsTab().click_orderConfirmation_btn();
		assertEquals(pageCollection.get_OrderProductPaymentsTab().getText_productCompletionPageHeading(),"ORDER CONFIRMATION");
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_shippingTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_paymentTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_orderCompletionMessage());
		assertTrue(generic.isStringContainedInUrl("controller=order-confirmation"));
	}
	
	@Test
	public void TC_004_checkoutThenloginTest() {
		
		pageCollection.get_NavBar().click_Women_Link_NavBar();
		pageCollection.get_CategoryPage().click_faded_thsirt_link();
		pageCollection.get_ProductPage().click_addToCart_btn();
		pageCollection.get_ProductPage().click_proceedToCheckout_btn();
		pageCollection.get_ShoppingCartSummaryPage().click_proceedToCheckout_btn();
		pageCollection.get_LoginPage().fill_login_email_InputField(existingUserEmail);
		pageCollection.get_LoginPage().fill_login_passwd_InputField(new String( Base64.decodeBase64(existingUserPassword)));
		pageCollection.get_LoginPage().click_login_submit_btn();
		pageCollection.get_OrderProductAddressTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductShippingTab().click_tnc_checkbox();
		pageCollection.get_OrderProductShippingTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductPaymentsTab().click_payViaWire_link();
		pageCollection.get_OrderProductPaymentsTab().click_orderConfirmation_btn();
		assertEquals(pageCollection.get_OrderProductPaymentsTab().getText_productCompletionPageHeading(),"ORDER CONFIRMATION");
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_shippingTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_paymentTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_orderCompletionMessage());
		assertTrue(generic.isStringContainedInUrl("controller=order-confirmation"));
	}
	
	@Test
	public void TC_005_checkoutThenRegisterTest() {
		pageCollection.get_NavBar().click_Women_Link_NavBar();
		pageCollection.get_CategoryPage().click_faded_thsirt_link();
		pageCollection.get_ProductPage().click_addToCart_btn();
		pageCollection.get_ProductPage().click_proceedToCheckout_btn();
		pageCollection.get_ShoppingCartSummaryPage().click_proceedToCheckout_btn();
		pageCollection.get_LoginPage().fill_random_signup_email_InputField();
		pageCollection.get_LoginPage().click_signup_submit_btn();
		pageCollection.get_CreateAccountPage().fill_registerForm(xlsReader,"signInTest", 2);
		pageCollection.get_OrderProductAddressTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductShippingTab().click_tnc_checkbox();
		pageCollection.get_OrderProductShippingTab().click_proceedToCheckout_btn();
		pageCollection.get_OrderProductPaymentsTab().click_payViaWire_link();
		pageCollection.get_OrderProductPaymentsTab().click_orderConfirmation_btn();
		assertEquals(pageCollection.get_OrderProductPaymentsTab().getText_productCompletionPageHeading(),"ORDER CONFIRMATION");
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_shippingTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_paymentTabHeading());
		assertTrue(pageCollection.get_OrderProductPaymentsTab().isVisible_orderCompletionMessage());
		assertTrue(generic.isStringContainedInUrl("controller=order-confirmation"));
	}
	
	
	

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
