package com.example.lmctest;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ReviewsFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;

    public String textKeyword;
    public RecyclerView recyclerView;
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

        final EditText editTextKeyword = view.findViewById(R.id.editTextKeyword);
        final EditText editTextDate = view.findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(this);

        editTextKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keyAction(editTextKeyword, editTextDate);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);

        new ReviewsInfo().networkService(getContext(), recyclerView, getTextKeyword(), "");
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                sortDate = y + "-0" + (m + 1) + "-" + d;
                new ReviewsInfo().networkService(getContext(), recyclerView, getTextKeyword(), "");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void keyAction(EditText editTextKeyword, EditText editTextDate) {
        setSortDate(editTextDate);
        setTextKeyword(editTextKeyword);
        new ReviewsInfo().networkService(getContext(), recyclerView, getTextKeyword(), "");
    }

    public void setTextKeyword(EditText editText) {
        textKeyword = editText.getText().toString();
    }

    public String getTextKeyword() {
        return textKeyword;
    }


    public void setSortDate(EditText editText) {
        String date = editText.getText().toString();
        if (date.equals("")) sortDate = "1900-01-01";
        else sortDate = date;
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
        new ReviewsInfo().networkService(getContext(), recyclerView, sortDate, "");
        swipeRefreshLayout.setRefreshing(false);
    }
}