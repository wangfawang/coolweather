package com.coolweather.app.util;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.R.bool;
import android.R.integer;
import android.R.string;
import android.text.TextUtils;

/*
 * �������ݶ��Ǵ���|���У�����|���У����ָ�ʽ�ġ���������������ṩһ���������������ʹ�����������
 * */
public class Utility {
	
	/*
	 * �����ʹ�����������ص�ʡ������
	 * */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response) {
		if(!TextUtils.isEmpty(response)) {//TextUtils���µ��ж��Ƿ�Ϊ�գ���Ϊ��ʱ���Զ��б��Ƿ��пո�
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0) {
				for(String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province��
					coolWeatherDB.saveProveince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * �����ʹ�����������ص��м�����
	 * */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId) {
		if(!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null &&allCities.length>0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//���������������ݴ洢��City��
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * �����ʹ�����������ص�������
	 * */
	public static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");//������ת���ַ�|����˼
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//���������������ݴ洢��County��
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
