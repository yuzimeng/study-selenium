package com.xyz.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				dataList = JSON.parseObject(content, new TypeReference<List<Map<String, Object>>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}
	
	public static List<Map<String, String>> addressResolution(String address) {
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		table = addressResolutionFromProvince(address);
		if (table == null || table.isEmpty()) {
			table = addressResolutionFromCity(address);
		}
		if (table == null || table.isEmpty()) {
			table = addressResolutionFromCounty(address);
		}
		return table;
	}

	private static List<Map<String, String>> addressResolutionFromProvince(String address) {
		/*
		 * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。它包括两个类：Pattern和Matcher Pattern
		 * 一个Pattern是一个正则表达式经编译后的表现模式。 Matcher
		 * 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
		 * 首先一个Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，
		 * 然后一个Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配工作。
		 */
		String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m = Pattern.compile(regex).matcher(address);
		String province = null, city = null, county = null, town = null, village = null;
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		while (m.find()) {
			row = new LinkedHashMap<String, String>();
			province = m.group("province");
			row.put("province", province == null ? "" : province.trim());
			city = m.group("city");
			row.put("city", city == null ? "" : city.trim());
			county = m.group("county");
			row.put("county", county == null ? "" : county.trim());
			town = m.group("town");
			row.put("town", town == null ? "" : town.trim());
			village = m.group("village");
			row.put("village", village == null ? "" : village.trim());
			table.add(row);
		}
		return table;
	}
	
	private static List<Map<String, String>> addressResolutionFromCity(String address) {
		String regex = "(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m = Pattern.compile(regex).matcher(address);
		String city = null, county = null, town = null, village = null;
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		while (m.find()) {
			row = new LinkedHashMap<String, String>();
			city = m.group("city");
			row.put("city", city == null ? "" : city.trim());
			county = m.group("county");
			row.put("county", county == null ? "" : county.trim());
			town = m.group("town");
			row.put("town", town == null ? "" : town.trim());
			village = m.group("village");
			row.put("village", village == null ? "" : village.trim());
			table.add(row);
		}
		return table;
	}
	private static List<Map<String, String>> addressResolutionFromCounty(String address) {
		String regex = "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m = Pattern.compile(regex).matcher(address);
		String county = null, town = null, village = null;
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		while (m.find()) {
			row = new LinkedHashMap<String, String>();
			county = m.group("county");
			row.put("county", county == null ? "" : county.trim());
			town = m.group("town");
			row.put("town", town == null ? "" : town.trim());
			village = m.group("village");
			row.put("village", village == null ? "" : village.trim());
			table.add(row);
		}
		return table;
	}
}
