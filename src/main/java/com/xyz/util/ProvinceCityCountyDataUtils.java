package com.xyz.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class ProvinceCityCountyDataUtils {

	public static List<Map<String, Object>> getProvinceCityCountyData() {
		List<Map<String, Object>> dataList = null;
		String jsonFileName = "/json/province_city_county.json";
		URL url = ProvinceCityCountyDataUtils.class.getResource(jsonFileName);
		String path = url.getPath();
		File file = new File(path);
		if (file.exists()) {
			try {
				String content = FileUtils.readFileToString(file, "UTF-8");
				dataList = JSON.parseObject(content, new TypeReference<List<Map<String, Object>>>() {});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}
}
