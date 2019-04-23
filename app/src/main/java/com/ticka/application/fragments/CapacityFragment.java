package com.ticka.application.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ticka.application.R;
import com.ticka.application.widgets.ValueChanger;

public class CapacityFragment extends Fragment {

    private Context context;
    private TextView ca1 , ca2 , ca3 , ca4 , description;
    private ValueChanger c1 , c2 , c3 , c4;

    public CapacityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_capacity, container, false);
        context = container.getContext();

        initViews(view);
        init();

        return view;
    }

    private void initViews(View view){

        ca1 = view.findViewById(R.id.ca1);
        ca2 = view.findViewById(R.id.ca2);
        ca3 = view.findViewById(R.id.ca3);
        ca4 = view.findViewById(R.id.ca4);
        description = view.findViewById(R.id.description);

        ca1.setText(R.string.capacity_1);
        ca2.setText(R.string.capacity_2);
        ca3.setText(R.string.capacity_3);
        ca4.setText(R.string.capacity_4);
        description.setText(R.string.general_description);

    }

    private void init(){

    }

}
