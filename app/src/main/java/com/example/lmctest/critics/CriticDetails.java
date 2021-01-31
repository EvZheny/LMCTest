package com.example.lmctest.critics;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lmctest.R;
import com.example.lmctest.ReviewsInfo;
import com.squareup.picasso.Picasso;

public class CriticDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critic_details);

        Bundle arguments = getIntent().getExtras();
        String displayName = arguments.getString("displayName");
        String bio = arguments.getString("bio");
        String status = arguments.getString("status");
        String imagePath = arguments.getString("imagePath");

        TextView textDisplayName = this.findViewById(R.id.critic_details_display_name);
        TextView criticDetailsBio = this.findViewById(R.id.critic_details_bio);
        TextView criticDetailsStatus = this.findViewById(R.id.critic_details_status);
        ImageView criticDetailsImage = this.findViewById(R.id.critic_details_image);

        textDisplayName.setText(displayName);
        criticDetailsBio.setText(bio);
        criticDetailsStatus.setText(status);

        if (imagePath.equals(""))
            criticDetailsImage.setImageResource(R.drawable.ic_launcher_foreground);
        else
            Picasso.get().load(imagePath).into(criticDetailsImage);

        RecyclerView recyclerView = this.findViewById(R.id.review_by_critic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ReviewsInfo()
                .networkService(this, recyclerView, "", textDisplayName.getText().toString());
    }
}