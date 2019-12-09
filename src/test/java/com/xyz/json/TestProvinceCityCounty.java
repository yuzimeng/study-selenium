package com.xyz.json;

import java.util.List;
import java.util.Map;

import com.xyz.util.ProvinceCityCountyDataUtils;

public class TestProvinceCityCounty {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Map<String, Object>> dataList = ProvinceCityCountyDataUtils.getProvinceCityCountyData();
		for (Map<String, Object> province : dataList) {
			String provinceName = (String) province.get("name");
			String provinceCode = (String) province.get("code");
			System.out.println(provinceCode + " : " + provinceName);
			List<Map<String, Object>> cityList = (List<Map<String, Object>>) province.get("cityList");
			for (Map<String,Object> city : cityList) {
				String cityName = (String) city.get("name");
				String cityCode = (String) city.get("code");
				System.out.println(cityCode + " : " + cityName);
				List<Map<String, Object>> areaList = (List<Map<String, Object>>) city.get("areaList");
				for (Map<String,Object> area : areaList) {
					String countyName = (String) area.get("name");
					String countyCode = (String) area.get("code");
					System.out.println(countyCode + " : " + countyName);
					
				}
			}
		}

	}

}
