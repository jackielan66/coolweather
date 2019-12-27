package com.lxydev.coolweather.util;

import android.text.TextUtils;

import com.lxydev.coolweather.db.City;
import com.lxydev.coolweather.db.County;
import com.lxydev.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 解析後台返回的數據省份数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     *  处理市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i=0;i<allCities.length();i++){
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     *  处理县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countryObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countryObj.getString("name"));
                    county.setId(countryObj.getInt("id"));
                    county.setCityId(cityId);
                    county.setWeatherId(countryObj.getString("weather_id"));
                    county.save();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
