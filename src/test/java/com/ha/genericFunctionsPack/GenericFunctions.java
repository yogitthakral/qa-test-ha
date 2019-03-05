package com.ha.genericFunctionsPack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GenericFunctions {

	WebDriver driver;
	WebDriverWait wait;
	String winHandle;


	//Generic Function Class that has some defined generic functions that are being used repeatedly in the test case statements.
	public GenericFunctions(WebDriver driver) {
		this.driver=driver;
	}

	public GenericFunctions() {
	}

	public WebDriver getDriver() {
		return driver;
	}



	public void SetImplicitWaitInMilliSeconds(int timeOut){
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
	}


	public WebDriver StartRemoteDriver(String browserType)
	{
		ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<RemoteWebDriver>();

		if(browserType.equalsIgnoreCase("Chrome")){
			System.out.println("Running on installed Chrome");
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			try {
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				SetImplicitWaitInMilliSeconds(30000);
				threadDriver.set((RemoteWebDriver) driver);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize(); 
		}else if(browserType.equalsIgnoreCase("FF")){ 
			System.out.println("Running on installed firefox");
			System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe"); 
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();  
			try {
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				SetImplicitWaitInMilliSeconds(30000);
				threadDriver.set((RemoteWebDriver) driver);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}   
			driver.manage().window().maximize(); 
		}


		wait = new WebDriverWait(this.driver, 30);
		SetImplicitWaitInMilliSeconds(30000);
		return threadDriver.get();    

	}



	//A common method to start the standalone and remote driver depending on an environment variable "gridMachine"
	public WebDriver StartDriver(String browserType)
	{
		String seleniumExecution = System.getenv("gridMachine");
		System.out.println("selenium type of execution is:"+seleniumExecution);

		if (seleniumExecution == null) {

		} else if (seleniumExecution.equalsIgnoreCase("true")) {
			driver = StartRemoteDriver(browserType);
			this.SetImplicitWaitInMilliSeconds(15000);
			return driver;

		} else {
		}
		if(browserType.trim().equalsIgnoreCase("")){
			throw new RuntimeException("Kindly set the 'browserType' variable before calling this function");
		}

		if(browserType.equalsIgnoreCase("FF")){
			System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe"); 
			driver = new FirefoxDriver();
			driver.manage().window().maximize(); 
		}



		else if(browserType.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize(); 
		}

		wait = new WebDriverWait(this.driver, 30);
		return driver;

	}


	public void StopDriver(){
		driver.quit();
	}






	public void CloseNewWindow(){
		driver.close();
	}


	public void SwitchtoOriginalWindow(){
		driver.switchTo().window(winHandle);
	}


	public Boolean isPresent(String elementXpath){
		SetImplicitWaitInMilliSeconds(500);
		if(driver.findElements(By.xpath(elementXpath)).size()!=0){
			return true;
		}
		else{
			return false;
		}
	}


	public Boolean isVisible(String elementXpath){
		if(driver.findElements(By.xpath(elementXpath)).size()!=0 && 
				driver.findElement(By.xpath(elementXpath)).isDisplayed()){
			return true;
		}
		else{
			return false;
		}


	}

	public void GoToSleep(int TimeInMillis)
	{
		try{Thread.sleep(TimeInMillis);} catch(Exception e){}
	}



	public String getNameAsCurrentTimeStamp(){
		String TimeStamp = Calendar.getInstance().getTime().toString();
		TimeStamp = TimeStamp.replace(":", "").replace(" ", "");
		return TimeStamp;
	}

	public String returnRandomEmail(){
		String timestamp = String.valueOf(new Date().getTime());
		String Email = "hA_challenge_" +timestamp + "@yopmail.com"; 
		System.out.println(Email);
		return Email;
	}



	public void click(String xpath){
		WebElement ele = findElementVisibility(xpath);
		try{
			ele.click();
		}
		catch(Exception e){
			try{
				System.out.println("First try in catch");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+ele.getLocation().y+")");
				ele.click();
			}
			catch(Exception e2){
				System.out.println("Second try in catch");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
			}
		}
	}



	public void clear(String xpath){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).clear();
	}

	public void fill(String xpath, String value_To_Fill){

		if(value_To_Fill.equals("")){
			findElementPresence(xpath).click();
			findElementPresence(xpath).clear();
			return;
		}
		try{
			findElementPresence(xpath).click();
			findElementPresence(xpath).clear();
			findElementPresence(xpath).sendKeys(value_To_Fill);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	public String getElementText(String xpath)
	{
		String value = findElementPresence((xpath)).getText();
		System.out.println(value);
		return value;
	}

	public boolean isStringContainedInUrl(String stringToBeContained) {
		return driver.getCurrentUrl().toString().contains(stringToBeContained);
	}

	public void selectFromDropdown (String xpath, String value_To_Select){
		if(value_To_Select.equals(""))
			return;
		System.out.println(value_To_Select);
		WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Select select = new Select(dropdown);
		select.selectByValue(value_To_Select);
	}

	public void selectFromDropdownByVisibleText (String xpath, String value_To_Select){
		if(value_To_Select.equals(""))
			return;
		System.out.println(value_To_Select);
		WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value_To_Select);
	}



	public WebElement findElementPresence(String xPath){
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
	}


	public WebElement findElementVisibility(String xPath){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	}








}
