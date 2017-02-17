package com.grepfails.proyectofintrim;

/**
 * Created by grep on 12/01/2017.
 */

public class APIInfo {
    public static String BASE_URL;
    public static String API_KEY;
    public static String API_BASE_IMG;

    public APIInfo(String base_url, String api_key, String api_base_img){

        BASE_URL = base_url;
        API_KEY = api_key;
        API_BASE_IMG = api_base_img;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getApiBaseImg() {
        return API_BASE_IMG;
    }

    public static void setApiBaseImg(String apiBaseImg) {
        API_BASE_IMG = apiBaseImg;
    }
}
