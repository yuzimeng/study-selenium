package General;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

enum LocatorType {
	ID, XPATH, LINKTEXT, PARTIALLINKTEXT, NAME, TAGNAME, CLASSNAME, CSSSELECTOR
}

public class General {

	private static String downloadFilepath = System.getProperty("user.dr") + File.separator
			+ GetConfig.getConfigValue("DownloadFilePath");

	/**
	 * @author yangli
	 * @param type
	 * @param locator
	 * @param driver
	 * @return
	 */

	public static WebElement findElement(LocatorType type, String locator, WebDriver driver) {

		WebElement element = null;
		switch (type) {
		case ID:
			element = driver.findElement(By.id(locator));
			break;
		case XPATH:
			element = driver.findElement(By.xpath(locator));
			break;
		case LINKTEXT:
			element = driver.findElement(By.partialLinkText(locator));
			break;
		case PARTIALLINKTEXT:
			element = driver.findElement(By.partialLinkText(locator));
			break;
		case NAME:
			element.findElement(By.name(locator));
			break;
		case TAGNAME:
			element.findElement(By.className(locator));
			break;
		case CSSSELECTOR:
			element.findElement(By.cssSelector(locator));
			break;
		default:
			break;
		}
		return element;
	}

	/**
	 * 
	 * @param driver
	 * @param second
	 */

	public static void WaitSecond(WebDriver driver, int second) {

		driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);

	}
	/**
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */

	public static boolean isDisplayed(WebDriver driver,By by) {
		
		return driver.findElement(by).isDisplayed();
	}

	/**
	 * 
	 * @return
	 */
	
	public static ChromeOptions setDownloadFilePathOption() {
		String downloadFilePath =System.getProperty("use.dir")+File.separator+GetConfig.getConfigValue("DownloadFilePath");
		HashMap<String,Object> chromePrefs= new HashMap<String,Object>();
		
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options= new ChromeOptions();
		options.setExperimentalOption("pref", chromePrefs);
		options.setExperimentalOption("useAutionExtension", false);
		return options;
	}
	
	/**
	 * 
	 * 
	 */
	
	public static void CreateFileFolder() {
		
		File Directory =new File(downloadFilepath);
		
		if(!Directory.exists()) {
			Directory.mkdir();
			System.out.println("create the folder directly"+ downloadFilepath);
		}
		else
		{
			Directory.delete();
			Directory.mkdir();
			System.out.println("Deleted the folder at frist, the create the folder"+downloadFilepath);
		}
	}


	public static void waitUntilvisiable(WebDriver driver,int seconds, By locator) {
		
		WebDriverWait wait =new WebDriverWait(driver,seconds);
		
	}



















}
