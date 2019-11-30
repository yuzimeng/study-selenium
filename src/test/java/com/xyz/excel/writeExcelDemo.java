package com.xyz.excel;

import General.General;

public class writeExcelDemo {
	
	public static void main(String[] args) throws Exception {
		
	//	System.out.println("hahha");
		
		General.writeExcel(General.getDataSourcePath(), 0, 1, 0, "test");
		
	} 

}
