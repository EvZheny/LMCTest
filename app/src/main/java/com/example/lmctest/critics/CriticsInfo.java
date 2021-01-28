package com.example.lmctest.critics;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lmctest.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticsInfo {
    private static final String TAG = "LMCTest";
    private static final String TOKEN = "p6bEPFJXQdB0ZGhSAcpcGFwrTszl3pRC";

    public void networkService(final Context context,
                               final RecyclerView recyclerView,
                               String reviewer) {
        if (reviewer.equals("")) reviewer = "all";

        NetworkService.getInstance()
                .getCriticsApi()
                .getCritics(reviewer, TOKEN)
                .enqueue(new Callback<Critics>() {
                    @Override
                    public void onResponse(Call<Critics> call, Response<Critics> response) {
                        if (response.body() != null && response.body().getResults()!= null)
                        CriticsAdapter.setResults(response.body().getResults());
                        CriticsAdapter criticsAdapter =
                                new CriticsAdapter(context, CriticsAdapter.getResults());
                        recyclerView.setAdapter(criticsAdapter);
                        criticsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Critics> call, Throwable t) {
                        Log.i(TAG,"failure " + t);
                    }
                });
    }
}
