package com.xyz.excel;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.common.io.Files;

public class RenameFileTest {
	public static void main(String[] args) {
		System.out.println(copyFileWithTime("F:/temp", "first.txt"));
	}

	public static String copyFileWithTime(String fileDir, String sourceFileName) {
		if (fileDir == null || sourceFileName == null) {
			return null;
		}
		File sourceFileDir = new File(fileDir);
		if (!sourceFileDir.exists()) {
			// 源目录不存在，返回空
			return null;
		}
		if (sourceFileDir.isFile()) {
			// 不是目录，返回空
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// 目录中查找判断
		File[] sourceFiles = sourceFileDir.listFiles();
		for (File srcFile : sourceFiles) {
			if (srcFile.isDirectory()) {
				// 如果出现子目录，是否要把子目录下的文件再找一遍？？
				continue;
			}
			if (srcFile.getName().equals(sourceFileName)) {
				String distFilePath = null;
				String distFileName = sourceFileName.substring(0, sourceFileName.lastIndexOf(".")) + "_"
						+ df.format(Calendar.getInstance().getTime())
						+ sourceFileName.substring(sourceFileName.lastIndexOf("."));
				distFilePath = sourceFileDir + File.separator + distFileName;
				File distFile = new File(distFilePath);
				try {
					// JDK1.7开始支持
					Files.copy(srcFile, distFile);
					// TODO start upload distFile
					System.out.println("====== start upload distFile =====");
					// when the file is uploaded success, the distFile will be deleted.
					System.out.println("==== delete distFile ====");
					distFile.deleteOnExit();
				} catch (IOException e) {
					System.out.println("copy source file exception!!!");
				}
				return distFilePath;
			}
		}
		return null;
	}

}
