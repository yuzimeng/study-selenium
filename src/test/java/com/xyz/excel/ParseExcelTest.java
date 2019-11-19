package com.xyz.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseExcelTest {

	public static void main(String[] args) {

		String checkStr = "E";
		List<List<String>> excelDataList = creatExcelMockData();
		for (List<String> row : excelDataList) {
			String fiveColoumData = row.get(4);
			if (!checkStr.equals(fiveColoumData)) {
				System.out.println("check invalid,throw exception!");
				break;
			}
		}
	}

	public static List<List<String>> creatExcelMockData() {
		String[] row1 = { "A1", "B1", "C1", "D1", "E", "F1" };
		String[] row2 = { "A2", "B2", "C2", "D2", "E", "F2" };
		String[] row3 = { "A3", "B3", "C3", "D3", "E3", "F3" };
		String[] row4 = { "A4", "B4", "C4", "D4", "E", "F4" };

		List<List<String>> dataList = new ArrayList<List<String>>();
		dataList.add(Arrays.asList(row1));
		dataList.add(Arrays.asList(row2));
		dataList.add(Arrays.asList(row3));
		dataList.add(Arrays.asList(row4));
		return dataList;
	}

}
