package com.example.lmctest.critics;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmctest.R;

public class CriticsViewHolder extends RecyclerView.ViewHolder {
    final ImageView criticImage;
    final TextView display_name;
    public CriticsViewHolder(@NonNull final View itemView) {
        super(itemView);
        this.criticImage = itemView.findViewById(R.id.criticImage);
        this.display_name = itemView.findViewById(R.id.display_name);

        itemView.setClickable(true);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CriticDetails.class);
                intent.putExtra("position", getPosition());
                view.getContext().startActivity(intent);
            }
        };
        itemView.setOnClickListener(click);
    }
}
