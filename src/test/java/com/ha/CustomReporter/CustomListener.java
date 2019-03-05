package com.ha.CustomReporter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ha.genericFunctionsPack.TakeScreenshot;
import com.ha.testcases.WebTest;



public class CustomListener implements ITestListener {
	//intialize extent objects
	public static ExtentReports extent = ExtentManager.getExtent();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();



	@Override
	public synchronized void onStart(ITestContext context) {
	}



	@Override
	public synchronized void onFinish(ITestContext testContext) {
		extent.flush();
	}


	@Override
	public synchronized void onTestStart(ITestResult result) {

		
		//Getting test method full name , splitting and extracting only method name and setting it as extent report test case name
		ExtentTest parent;	
		String testName=result.getMethod().getMethodName();

		
		
		parent = extent.createTest(testName);
		String[] category=result.getMethod().getTestClass().getName().split("\\.");
		parent.assignCategory(category[category.length-1]);
		test.set(parent);



	}



	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		//sending log to Extent and the logger file.
		test.get().pass(result.getMethod().getMethodName()+" passed");

		try {
			WebTest.log4j.debug(result.getMethod().getMethodName()+"_PASSED"+"_TimeStamp:"+getDateTimeIP());
		} catch (Exception e) {
			e.printStackTrace();
		}	



	}


	@Override
	public synchronized void onTestFailure(ITestResult result) {
		//if test case if failed due to non assertion failures , its counted under skipped test cases with all the remaining things such as exception and screenshot .
		//This is done to filter out obvious failures quickly so that Bugs could be found quickly when analysing a test report.
		ReportingDriverProvider driverProvider = new ReportingDriverProvider();	


		StringWriter sw = new StringWriter();
		result.getThrowable().printStackTrace(new PrintWriter(sw));

		if(sw.toString().equalsIgnoreCase("null")) {
			sw.write("No Exception was thrown by the test case");
		}

		try{
				if(result.getThrowable()!=null && result.getThrowable().toString().contains("java.lang.Throwable: Multiple failures")){
					test.get().skip(result.getMethod().getMethodName()+" Skipped because of multiple failures.");	

				}else{

					if(driverProvider.getDriver(result)!=null){
						String path=TakeScreenshot.returnScreenShotName(driverProvider.getDriver(result),result.getMethod().getMethodName());
						if(result.getThrowable().toString().contains("java.lang.AssertionError")){


							SessionId session = ((RemoteWebDriver) driverProvider.getDriver(result)).getSessionId();
							if(session!=null){
								test.get().log(Status.FAIL,"Failure URL :- " +((WebDriver) driverProvider.getDriver(result)).getCurrentUrl().toString());
							}

							test.get().log(Status.FAIL, "<pre>"+sw.toString()+"</pre>");
							test.get().log(Status.FAIL,"Screenshot :- "+"<img data-featherlight=\""+path+"\"  width=\"30%\" src=\""+path+"\" data-src=\""+path+"\""+path+"\">");
						}
						else
						{
							SessionId session = ((RemoteWebDriver) driverProvider.getDriver(result)).getSessionId();
							if(session!=null){
								test.get().log(Status.SKIP,"Test Case Skipped on URL :- " +((WebDriver) driverProvider.getDriver(result)).getCurrentUrl().toString());
							}
							test.get().log(Status.SKIP, "<pre>"+sw.toString()+"</pre>");
							test.get().log(Status.SKIP,"Screenshot :- "+"<img data-featherlight=\""+path+"\"  width=\"30%\" src=\""+path+"\" data-src=\""+path+"\""+path+"\">");

						}
					}
					else{
						if(result.getThrowable().toString().contains("java.lang.AssertionError")){

							test.get().log(Status.FAIL, "<pre>"+sw.toString()+"</pre>");
						}
						else
						{
							test.get().log(Status.SKIP, "<pre>"+sw.toString()+"</pre>");

						}

					}

				}
				//sending log to the logger file.
				WebTest.log4j.debug(result.getMethod().getMethodName()+"_FAILED"+"_TimeStamp:"+getDateTimeIP());
		}
		catch(Exception e){

			test.get().log(Status.SKIP, "<pre>"+"Test Case skipped because of an exception in the custom repoter method, Please find the exception here :- \n"+e+"</pre>");

		}

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		//Code control comes here when test configuration is failed , 
		//and we log the exception thats been causing it to debug it and 
		//also provided the page url of the test case faster
		ReportingDriverProvider driverProvider = new ReportingDriverProvider();	
		ExtentTest parent;	
		String testName=result.getMethod().getMethodName();



		try{
				if(test.get()==null ||((!test.get().getModel().getName().contains(result.getMethod().getMethodName()))&& result.getParameters().length==0)){
					parent = extent.createTest(testName);
					String[] category=result.getMethod().getTestClass().getName().split("\\.");
					parent.assignCategory(category[category.length-1]);
					test.set(parent);

				}



			if(result.getThrowable()!=null && result.getThrowable().toString().contains("java.lang.Throwable: Multiple failures")){
				test.get().skip(result.getMethod().getMethodName()+" Skipped because of multiple failures.");	
			}else{
				if(driverProvider.getDriver(result)!=null){
					String path=TakeScreenshot.returnScreenShotName(driverProvider.getDriver(result),result.getMethod().getMethodName());

					SessionId session = ((RemoteWebDriver) driverProvider.getDriver(result)).getSessionId();
					if(session!=null){
						test.get().log(Status.SKIP,"Test Case Skipped On URL :- " +((WebDriver) driverProvider.getDriver(result)).getCurrentUrl().toString());
					}

					if(result.getThrowable()==null){
						test.get().log(Status.SKIP, "<pre>"+"No Exception is thrown in test case. This might be caused due to unknown errors or Configuration Failures."+"</pre>");
					}else{
						test.get().log(Status.SKIP, "<pre>"+ result.getThrowable()+"</pre>");

					}


					if(path!=null)
					{
						test.get().log(Status.SKIP,"Screenshot :- "+"<img data-featherlight=\""+path+"\"  width=\"30%\" src=\""+path+"\" data-src=\""+path+"\""+path+"\">");
					}
					test.get().skip("Test Skipped"+result.getMethod().getMethodName() );
				}
				else{
					test.get().log(Status.SKIP, "<pre>"+result.getThrowable()+"</pre>");
					test.get().skip("Test Skipped "+result.getMethod().getMethodName() );
				}
			}



			WebTest.log4j.debug(result.getMethod().getMethodName()+"_SKIPPED"+"_TimeStamp:"+getDateTimeIP());

		}catch(Exception e) {
			test.get().log(Status.SKIP, "<pre>"+"Test Case skipped because of an exception in the custom repoter method, Please find the exception here :- \n"+e+"</pre>");


		}


	}






	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public String getDateTimeIP() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaa_dd_MMM_yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}



}


