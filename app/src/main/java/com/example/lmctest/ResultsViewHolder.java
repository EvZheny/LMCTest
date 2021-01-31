package com.example.lmctest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ResultsViewHolder extends RecyclerView.ViewHolder {
    final ImageView reviewImage;
    final TextView textReview;
    final TextView displayTitle;
    final TextView displayDateUpdated;
    final TextView byline;
    ResultsViewHolder(View view){
        super(view);
        reviewImage = view.findViewById(R.id.reviewImage);
        textReview = view.findViewById(R.id.textReview);
        displayTitle = view.findViewById(R.id.display_title);
        displayDateUpdated = view.findViewById(R.id.display_publication_date);
        byline = view.findViewById(R.id.display_reviewer_name);
    }
}
