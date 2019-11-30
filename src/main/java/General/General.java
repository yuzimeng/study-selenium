package General;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

enum LocatorType {
	ID, XPATH, LINKTEXT, PARTIALLINKTEXT, NAME, TAGNAME, CLASSNAME, CSSSELECTOR
}

public class General {

	private static String downloadFilepath = System.getProperty("user.dir") + File.separator
			+ GetConfig.getConfigValue("DownloadFilePath");

private static String dataSourcePath=System.getProperty("user.dir")+"\\DataSource\\DataSource.xlsx";
	
	public static String getDataSourcePath() {
	return dataSourcePath;
}

public static void setDataSourcePath(String dataSourcePath) {
	General.dataSourcePath = dataSourcePath;
}

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

/**
 * 
 * @param driver
 * @param seconds
 * @param locator
 */
	public static void waitUntilvisiable(WebDriver driver,int seconds, By locator) {
		
		WebDriverWait wait =new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		
	}

	
	public static void deleteFiles(File subFile) {
		File[] fileList=subFile.listFiles();
		
		for(File f: fileList) {
			if(f.isDirectory()) {
				deleteFiles(f);
			}else {
				f.delete();
			}
		}
		subFile.delete();
		
	}
	
	
	
public static void cleanFolder(String path) {
	File file=new File(path);
	if(file==null ||!file.exists()) {
		file.mkdir();
	}else {
		deleteFiles(file);
		file.mkdir();
	}
}

/**
 * 
 * @param driver
 * @param by
 * @return
 */

public static ArrayList<List<WebElement>> getTable(WebDriver driver,By by){
	
	ArrayList<List<WebElement>>list =new ArrayList<List<WebElement>>();
	
	WebElement table=driver.findElement(by);
	List<WebElement> rows=table.findElements(by.tagName("tr"));
	
	for(int i=0;i<rows.size();i++) {
		List<WebElement> cols= rows.get(i).findElements(by.tagName("td"));
		List<WebElement> listrow =new ArrayList<WebElement>();
		for(int j=0;j<cols.size();j++) {
			listrow.add(cols.get(j));
		}
		
		list.add(listrow);
	}
	return list;
	
}

public static void writeExcel(String excelFilePath,int sheetnum,int rownum,int colnum,String value) throws Exception  {
	
	//
	
	FileOutputStream fout = null;
	try {
		File src =new File(excelFilePath);
		if (!src.exists()) {
			throw new Exception("path is not exists, current path is :"+excelFilePath);
		}
		
		//FileInputStream fis=new FileInputStream(src);
		//第一步，创建一个workbook对应一个excel文件
		XSSFWorkbook wb= new XSSFWorkbook();
		//第二步，在workbook中创建一个sheet对应excel中的sheet
		XSSFSheet sh=wb.createSheet("PlanningPage");
		//第三步，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
		XSSFRow row = sh.createRow(0);
		//第四步，创建单元格，设置表头
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("systemCode");
		
		cell = row.createCell(1);
		cell.setCellValue("caseId");
		
		//第五步，写入数据
		XSSFRow row1 = sh.createRow(1);
		row1.createCell(0).setCellValue(value);
		
		fout = new FileOutputStream(new File(excelFilePath));
		wb.write(fout);
		fout.flush();
			
	}catch (Exception e){
		e.getMessage();
	}finally {
		if (fout != null) {
			fout.close();
		}
	}
	
	
	
}



















}
