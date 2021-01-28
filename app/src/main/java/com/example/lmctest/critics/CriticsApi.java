package com.example.lmctest.critics;

import com.example.lmctest.Reviews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CriticsApi {
    @GET(value = "critics/{reviewer}.json")
    Call<Critics> getCritics(
            @Path("reviewer") String reviewer,
            @Query("api-key") String token);
}
