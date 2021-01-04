package com.example.lab3.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class OmdbApi {

    private static final String BASE_URL = "https://www.omdbapi.com";

    private static final String API_KEY = "4e090489";

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("dd MMM yyyy")
            .create();

    private static final Retrofit omdbApi = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();

    private static final Map<String, String> defaultUrlParams = new HashMap<String, String>(){{
        put("apikey", API_KEY);
        put("type", "movie");
    }};

    public static Map<String, String> getDefaultUrlParams(){
        return new HashMap<>(defaultUrlParams);
    }

    public static <S> S createService(Class<S> serviceType){
        return omdbApi.create(serviceType);
    }

}
