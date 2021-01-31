package com.example.lmctest.critics;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lmctest.R;

public class CriticsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;

    public String textCriticName;
    public RecyclerView criticView;

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

        final EditText editTextCriticName = view.findViewById(R.id.editTextCriticName);
        setTextCriticName(editTextCriticName);
        editTextCriticName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setTextCriticName(editTextCriticName);
                new CriticsInfo()
                        .networkService(getContext(), criticView, getTextCriticName());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_critics);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);

        new CriticsInfo().networkService(getContext(), criticView, getTextCriticName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_critics, container, false);
    }

    public void setTextCriticName(EditText editText) {
        textCriticName = editText.getText().toString();
    }

    public String getTextCriticName() {
        return textCriticName;
    }

    @Override
    public void onRefresh() {
        new CriticsInfo()
                .networkService(getContext(), criticView, textCriticName);
        swipeRefreshLayout.setRefreshing(false);
    }
}