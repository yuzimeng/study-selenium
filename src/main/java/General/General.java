package General;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

enum LocatorType{
	ID,XPATH,LINKTEXT,PARTIALLINKTEXT,NAME,TAGNAME,CLASSNAME,CSSSELECTOR
}

public class General {
	
private static String downloadFilepath=System.getProperty("user.dr")+File.separator+GetConfig.getConfigValue("DownloadFilePath");


/**
 * @author yangli
 * @param type
 * @param locator
 * @param driver
 * @return
 */

public static WebElement findElement(LocatorType type,String locator,WebDriver driver) {
	
	WebElement element=null;
	switch(type) {
	case ID:
		element=driver.findElement(By.id(locator));
		break;
	case XPATH:
		element=driver.findElement(By.xpath(locator));
		break;
	case LINKTEXT:
		element=driver.findElement(By.partialLinkText(locator));
		break;
	case PARTIALLINKTEXT:
		element=driver.findElement(By.partialLinkText(locator));
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



}
