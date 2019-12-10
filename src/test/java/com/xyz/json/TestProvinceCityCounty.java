package com.xyz.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyz.util.ProvinceCityCountyDataUtils;

public class TestProvinceCityCounty {

	public static void main(String[] args) {
//		String str = "长春市农安县朝阳路200号";
		String str = "上海市黄浦区北京路123号";
		
		System.out.println(getProvinceFromAddress(str));

	}

	@SuppressWarnings("unchecked")
	public static String getProvinceFromAddress(String address) {
		List<Map<String, Object>> dataList = ProvinceCityCountyDataUtils.getProvinceCityCountyData();
		Map<String, String> provinceData = new HashMap<String, String>();
		Map<String, String> cityProvinceData = new HashMap<String, String>();
		for (Map<String, Object> province : dataList) {
			String provinceName = (String) province.get("name");
			String provinceCode = (String) province.get("code");
			provinceData.put(provinceName, provinceCode);
//			System.out.println(provinceCode + " : " + provinceName);
			List<Map<String, Object>> cityList = (List<Map<String, Object>>) province.get("cityList");
			for (Map<String, Object> city : cityList) {
				String cityName = (String) city.get("name");
				String cityCode = (String) city.get("code");
//				System.out.println(cityCode + " : " + cityName);
				cityProvinceData.put(cityName, provinceName);
//				List<Map<String, Object>> areaList = (List<Map<String, Object>>) city.get("areaList");
//				for (Map<String,Object> area : areaList) {
//					String countyName = (String) area.get("name");
//					String countyCode = (String) area.get("code");
//					System.out.println(countyCode + " : " + countyName);
//				}
			}
		}

//		String[] cities = { "北京", "上海", "天津", "重庆" };
//		List<String> cityList = Arrays.asList(cities);
		List<Map<String, String>> addrList = ProvinceCityCountyDataUtils.addressResolution(address);
		if (addrList != null && !addrList.isEmpty()) {
			Map<String, String> addr = addrList.get(0);
			// [{province=长春市, city=农安县, county=, town=, village=朝阳路200号}]
			String province = addr.get("province");
			if (province != null && province.trim().length() > 0 && provinceData.containsKey(province)) {
				return province;
			}
			else if (province != null && province.trim().length() > 0 && cityProvinceData.containsKey(province)) {
				return cityProvinceData.get(province);
			}

		}
		return null;
	}

}
