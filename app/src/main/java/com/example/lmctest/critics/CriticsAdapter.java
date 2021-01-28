package com.example.lmctest.critics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmctest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CriticsAdapter extends RecyclerView.Adapter<CriticsViewHolder> {
    public static List<Result> criticsResults;
    private final LayoutInflater inflater;

    public CriticsAdapter(Context context, List<Result> result) {
        this.inflater = LayoutInflater.from(context);
        this.criticsResults = result;
    }

    public static List<Result> getResults() {
        return criticsResults;
    }

    public static void setResults(List<Result> results) {
        criticsResults = results;
    }

    @Override
    public int getItemCount() {
        return criticsResults.size();
    }

    @NonNull
    @Override
    public CriticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.critics_list, parent, false);
        return new CriticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CriticsViewHolder holder, int position) {
        Result result = criticsResults.get(position);

        holder.display_name.setText(result.getDisplayName());

        if (result.getMultimedia() != null)
            Picasso.get().load(result
                    .getMultimedia()
                    .getResource()
                    .getSrc())
                    .into(holder.criticImage);
        else
            holder.criticImage.setImageResource(R.drawable.ic_launcher_foreground);
    }

    public static void refreshResults(Context context, RecyclerView recyclerView) {
        new CriticsInfo().networkService(context,
                recyclerView,
                CriticsFragment.getEditTextCriticName());
    }
}