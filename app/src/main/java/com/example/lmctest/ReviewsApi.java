package com.example.lmctest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewsApi {
    @GET("reviews/search.json")
    Call<Reviews> getReviews(
            @Query("api-key") String token,
            @Query("reviewer") String reviewer,
            @Query("offset") int offset,
            @Query("publication-date") String publication_date,
            @Query("query") String query);
}
