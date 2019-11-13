package stepDefinitionsForEDMS;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import EDMSPage.UploadFilePage;

public class CheckUploadFilePage {
	
	WebDriver driver=Hook.getDriver();
	UploadFilePage ufp = new UploadFilePage(driver);
	
	
	public void uploadFile(String Filename) throws Throwable {
		
	Runtime downloadexe =Runtime.getRuntime();
	
	try {
		//download file
		downloadexe.exec("C:/Windows/System32/cmd.exe /k start "+Hook.getDownloadTool()+" "+Hook.getDownloadPath()+Filename.replaceAll(" ", "").replaceAll("-", "")+"TemplateNew");
		
	}catch(IOException e) {
		e.printStackTrace();
	}
		Thread.sleep(1000);
		
	}
	
	//justment
	public void thereisExcelwasdownload() throws Throwable {
		
		if(Hook.getDownloadPath().isEmpty()) {
			throw new Exception("Export report failed");
		}else {
			System.out.println("Export report file successfully");
		}
		
	}

}
