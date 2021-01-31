package com.example.lmctest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsInfo {
    private final String TAG = "LMCTest";
    private final String TOKEN = "p6bEPFJXQdB0ZGhSAcpcGFwrTszl3pRC";
    public String publication_date;
    public int offset;

    public void networkService(final Context context,
                               final RecyclerView recyclerView,
                               String query,
                               String reviewer) {
        NetworkService.getInstance()
                .getReviewsApi()
                .getReviews(TOKEN, reviewer, offset, publication_date, query)
                .enqueue(new Callback<Reviews>() {
                    @Override
                    public void onResponse(@NonNull Call<Reviews> call,
                                           @NonNull Response<Reviews> response) {
                        if (response.body() != null && response.body().getResults()!= null) {
                            ResultsAdapter resultsAdapter =
                                    new ResultsAdapter(context, response.body().getResults());
                            recyclerView.setAdapter(resultsAdapter);
                            resultsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Reviews> call, Throwable t) {
                        Log.i(TAG,"failure " + t);
                    }
                });
    }
}