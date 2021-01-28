package com.example.lmctest.critics;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.lmctest.R;

public class CriticsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;

    private static final String TAG = "LMCTest";

    @SuppressLint("StaticFieldLeak")
    public static EditText editTextCriticName;
    public static RecyclerView criticView;

    public CriticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        criticView = view.findViewById(R.id.criticsRecyclerView);
        criticView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        editTextCriticName = view.findViewById(R.id.editTextCriticName);
        editTextCriticName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER))
                    new CriticsInfo().networkService(getContext(),
                            criticView,
                            getEditTextCriticName());
                return false;
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_critics);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);

        new CriticsInfo().networkService(getContext(), criticView, getEditTextCriticName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_critics, container, false);
    }

    public static String getEditTextCriticName() {
        return editTextCriticName.getText().toString();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CriticsAdapter.refreshResults(getContext(), criticView);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}