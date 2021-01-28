package com.example.lmctest;

import com.example.lmctest.critics.CriticsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL = "https://api.nytimes.com/svc/movies/v2/";
    private static NetworkService instance;

    private NetworkService(){
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static NetworkService getInstance() {
        if (instance == null) instance = new NetworkService();
        return instance;
    }

    public ReviewsApi getReviewsApi() {
        return retrofit.create(ReviewsApi.class);
    }

    public CriticsApi getCriticsApi() {
        return retrofit.create(CriticsApi.class);
    }
}
