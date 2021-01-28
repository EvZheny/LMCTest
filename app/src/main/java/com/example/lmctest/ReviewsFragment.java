package com.example.lmctest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

public class ReviewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("StaticFieldLeak")
    public static EditText editTextKeyword;
    public static RecyclerView recyclerView;
    public static String sortDate = "1900-01-01";

    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleRewiews);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        editTextKeyword = view.findViewById(R.id.editTextKeyword);
        final EditText editTextDate = view.findViewById(R.id.editTextDate);

        editTextKeyword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN) &&
                        (i == android.view.KeyEvent.KEYCODE_ENTER))
                    new ReviewsInfo().networkService(getContext(),
                            recyclerView,
                            getEditTextKeyword(), "");
                return false;
            }
        });

        editTextDate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN) &&
                        (i == android.view.KeyEvent.KEYCODE_ENTER))
                    setSortDate(editTextDate.getText().toString());
                return false;
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);

        new ReviewsInfo().networkService(getContext(), recyclerView, getEditTextKeyword(), "");
    }

    public static String getEditTextKeyword() {
        return editTextKeyword.getText().toString();
    }

    public void setSortDate(String date) {
        sortDate = date;
    }

    public static String getSortDate() {
        return sortDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewPager,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, viewPager, false);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ResultsAdapter.refreshResults(getContext(), recyclerView);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}