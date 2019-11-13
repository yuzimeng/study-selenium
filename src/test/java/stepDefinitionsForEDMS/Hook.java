package stepDefinitionsForEDMS;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	
	private static WebDriver driver;
	
	private static String uploadTool=System.getProperty("user.dir")+"\\Driver\\UploadFile.exe";
	private static String downloadTool=System.getProperty("user.dir")+"\\Driver\\downloadFile.exe";
	private static String uploadPath=System.getProperty("user.dir")+"\\DIPFilePath\\";
	private static String  downloadPath=System.getProperty("user.dir")+"\\Download\\";
	
	@Before
	public void setUP(Scenario scenario) {
		
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dr")+"\\Driver\\IEDriverServer.exe");
		InternetExplorerOptions ops =new InternetExplorerOptions();
		ops.requireWindowFocus();
		ops.ignoreZoomSettings();
		ops.introduceFlakinessByIgnoringSecurityDomains();
		driver=new InternetExplorerDriver(ops);
		

}
	@After
	
	public void afterScenario(Scenario scenario) {
		
		try { byte[]screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
			
		}catch(WebDriverException ex) {
			System.err.println(ex.getMessage());
			
     	}catch(ClassCastException cce) {
     		cce.printStackTrace();
     	}
		
		
	}
	
public static WebDriver getDriver() {
	
	return driver;
}
	
	public static String getUploadTool() {
		return uploadTool;
		
	}
	
	public static String getUploadPath() {
		return uploadPath;	
	}
	
	public static String getDownloadPath() {
		return downloadPath;
		
	}
	
	public static String getDownloadTool() {
		return downloadTool;
		
	}
	
	
	
	
	
	
	
	
}
