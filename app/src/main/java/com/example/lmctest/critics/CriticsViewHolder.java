package com.example.lmctest.critics;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmctest.R;

import java.util.List;

public class CriticsViewHolder extends RecyclerView.ViewHolder {
    ImageView criticImage;
    TextView displayName;
    public CriticsViewHolder(@NonNull final View itemView, final List<Result> criticsResults) {
        super(itemView);
        this.criticImage = itemView.findViewById(R.id.criticImage);
        this.displayName = itemView.findViewById(R.id.display_name);

        itemView.setClickable(true);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CriticDetails.class);
                intent.putExtra("displayName",displayName.getText().toString());
                intent.putExtra("position", getPosition());
                intent.putExtra("bio", criticsResults.get(getPosition()).getBio());
                intent.putExtra("status", criticsResults.get(getPosition()).getStatus());
                if (criticsResults.get(getPosition()).getMultimedia() != null)
                    intent.putExtra("imagePath",
                            criticsResults.get(getPosition())
                                    .getMultimedia()
                                    .getResource()
                                    .getSrc());
                else
                    intent.putExtra("imagePath", "");
                view.getContext().startActivity(intent);
            }
        };
        itemView.setOnClickListener(click);
    }
}