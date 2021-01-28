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

import java.util.List;

public class CriticDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critic_details);

        Bundle arguments = getIntent().getExtras();
        int position = arguments.getInt("position");
        List<Result> resultList = CriticsAdapter.getResults();
        Result result = resultList.get(position);

        TextView text_display_name = this.findViewById(R.id.critic_details_display_name);
        TextView critic_details_bio = this.findViewById(R.id.critic_details_bio);
        TextView critic_details_status = this.findViewById(R.id.critic_details_status);
        ImageView critic_details_image = this.findViewById(R.id.critic_details_image);

        text_display_name.setText(result.getDisplayName());
        critic_details_bio.setText((CharSequence) result.getBio());
        critic_details_status.setText(result.getStatus());

        if (result.getMultimedia() != null)
            Picasso.get().load(result
                    .getMultimedia()
                    .getResource()
                    .getSrc())
                    .into(critic_details_image);
        else
            critic_details_image.setImageResource(R.drawable.ic_launcher_foreground);

        RecyclerView recyclerView = this.findViewById(R.id.review_by_critic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ReviewsInfo()
                .networkService(this, recyclerView, "", text_display_name.getText().toString());
    }
}