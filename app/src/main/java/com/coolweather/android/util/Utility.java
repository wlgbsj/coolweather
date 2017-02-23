package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by att on 2017/2/23.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */

    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray  allProvinceObject = new JSONArray(response);
                for(int i=0;i<allProvinceObject.length();i++){
                    JSONObject provinceObject = allProvinceObject.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();

                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * 解析服务器返回的市级数据
     */
    public  static  boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city =new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }

    /**
     * 解析和处理服务器返回的显级数据
     */

    public  static  boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allcountries = new JSONArray(response);
                for(int i=0;i<allcountries.length();i++){
                    JSONObject countryObject = allcountries.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countryObject.getString("name"));
                    county.setWeatherId(countryObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;

    }
}
