package com.example.lmctest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsViewHolder> {
    private final LayoutInflater inflater;
    private List<Result> reviewsResult;

    public ResultsAdapter(Context context, List<Result> resultList) {
        this.reviewsResult = resultList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return reviewsResult.size();
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reviews_list, parent, false);
        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        Result result = reviewsResult.get(position);

        if ((result.getPublicationDate() != null)
                && (ReviewsFragment.getSortDate() != null)
                && dateFormat(result.getPublicationDate())
                .after(dateFormat(ReviewsFragment.getSortDate()))) {
            holder.textReview.setText(result.getSummaryShort());
            holder.displayTitle.setText(result.getDisplayTitle());
            holder.displayDateUpdated.setText(result.getDateUpdated());
            holder.byline.setText(result.getByline());

            if (result.getMultimedia() != null)
                Picasso.get().load(result.getMultimedia().getSrc()).into(holder.reviewImage);
            else
                holder.reviewImage.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    public Date dateFormat(String dateString) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}