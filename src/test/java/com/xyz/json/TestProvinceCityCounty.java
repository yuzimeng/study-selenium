package com.xyz.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyz.util.ProvinceCityCountyDataUtils;

public class TestProvinceCityCounty {

	public static void main(String[] args) {
//		String str = "长春市农安县朝阳路200号";
//		String str = "农安县朝阳路200号";
//		String str = "昆山市开元路1300号";
//		String str = "太仓市光明路1300号";
//		String str = "上海市黄浦区北京路123号";
//		String str = "江苏省张家港市长江中路123号";
//		String str = "北京市朝阳区光明路55号";
//		String str = "吉林省南关区中心路421号";
		String str = "驻马店市驿城区中心路481号";
		
		System.out.println(getProvinceFromAddress(str));

	}

	@SuppressWarnings("unchecked")
	public static List<String> getProvinceFromAddress(String address) {
		List<Map<String, Object>> dataList = ProvinceCityCountyDataUtils.getProvinceCityCountyData();
		Map<String, String> provinceData = new HashMap<String, String>();
		Map<String, String> cityProvinceData = new HashMap<String, String>();
		Map<String, String> countyCityData = new HashMap<String, String>();
		for (Map<String, Object> province : dataList) {
			String provinceName = (String) province.get("name");
			String provinceCode = (String) province.get("code");
			provinceData.put(provinceName, provinceCode);
			List<Map<String, Object>> cityList = (List<Map<String, Object>>) province.get("cityList");
			for (Map<String, Object> city : cityList) {
				String cityName = (String) city.get("name");
				cityProvinceData.put(cityName, provinceName);
//				String cityCode = (String) city.get("code");
				List<Map<String, Object>> areaList = (List<Map<String, Object>>) city.get("areaList");
				for (Map<String,Object> area : areaList) {
//					String countyCode = (String) area.get("code");
					String countyName = (String) area.get("name");
					countyCityData.put(countyName, cityName);
				}
			}
		}

		String[] result = { "", "", "" };
//		List<String> cityList = Arrays.asList(cities);
		List<Map<String, String>> addrList = ProvinceCityCountyDataUtils.addressResolution(address);
		if (addrList != null && !addrList.isEmpty()) {
			Map<String, String> addr = addrList.get(0);
			// [{province=长春市, city=农安县, county=, town=, village=朝阳路200号}]
			String province = addr.get("province");
			String city = addr.get("city");
			String county = addr.get("county");
			if (province != null && province.trim().length() > 0 ) {
				result[0] =  province;
				if (city != null && countyCityData.containsKey(city)) {
					result[1] =  countyCityData.get(city);
					result[2] =  city;
				}else {
					result[1] =  city;
					result[2] =  county;
				}
				return Arrays.asList(result);
			}
			if (city != null && city.trim().length() > 0 ) {
				String provinceName = cityProvinceData.get(city);
				result[1] = city;
				result[2] =  county;
				if (provinceName == null) {
					provinceName = cityProvinceData.get(countyCityData.get(city));
					result[1] = countyCityData.get(city);
					result[2] =  city;
				}
				result[0] =  provinceName;
				return Arrays.asList(result);
			}
			if (county != null && county.trim().length() > 0 ) {
				result[0] = cityProvinceData.get(countyCityData.get(county));
				result[1] = countyCityData.get(county);
				result[2] = city;
				return Arrays.asList(result);
			}
		}
		return null;
	}

}
