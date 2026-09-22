package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	public void configureReport() {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName="PetUserAutomationReport_"+timestamp+".html";
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+reportName);
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//Add System Info to Report
		reports.setSystemInfo("Machine","TestPC1");
		reports.setSystemInfo("OS ","Windows 10");
		reports.setSystemInfo("Username","Ayush");
		
		//Configure Report
		htmlReporter.config().setDocumentTitle("Pet User Automation Report");
		htmlReporter.config().setReportName("Pet User Automation");
		htmlReporter.config().setTheme(Theme.DARK);
	}
		@Override
		public void onStart(ITestContext Result) {
			configureReport();
			System.out.println("========== Test Execution Started ==========");
		}
	
		@Override
		public void onTestFailure(ITestResult Result) {
			System.out.println("Name of the test Method Failed:"+ Result.getName());
			test=reports.createTest(Result.getName());
			test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed TC is:"+Result.getName(),ExtentColor.RED));
			String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+Result.getName()+".png";
			File screenshotFile=new File(screenshotPath);
			if(screenshotFile.exists()) {
				test.fail("Captured Screenshot is below:").addScreenCaptureFromPath(screenshotPath);
			}
		}
		
		@Override
		public void onTestSuccess(ITestResult Result) {
			System.out.println("Name of the test Method Succesfully Extecuted:"+ Result.getName());
			test=reports.createTest(Result.getName());
			test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed TC is:"+Result.getName(),ExtentColor.GREEN));
		}
		
		@Override
		public void onTestSkipped(ITestResult Result) {
			System.out.println("Name of the test Method Skipped:"+ Result.getName());
			test=reports.createTest(Result.getName());
			test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped TC is:"+Result.getName(),ExtentColor.YELLOW));
		}
		
		@Override
		public void onFinish(ITestContext Result) {
			System.out.println("========== Test Execution Finished ==========");
			reports.flush();
		}
		
}
