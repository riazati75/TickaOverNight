package com.ticka.application.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.models.DataModel;
import com.ticka.application.widgets.ValueChanger;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

public class CapacityFragment extends Fragment {

    private DataModel dataModel = MainActivity.getDataModel();
    private Context context;
    private TextView ca1 , ca2 , ca3 , ca4 , description;
    private ValueChanger c1 , c2 , c3 , c4;
    private EditText inputDescription;
    private Button btnNext;

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
        inputDescription = view.findViewById(R.id.inputDescription);

        ca1.setText(R.string.capacity_1);
        ca2.setText(R.string.capacity_2);
        ca3.setText(R.string.capacity_3);
        ca4.setText(R.string.capacity_4);
        description.setText(R.string.general_description);

        c1 = view.findViewById(R.id.c1);
        c2 = view.findViewById(R.id.c2);
        c3 = view.findViewById(R.id.c3);
        c4 = view.findViewById(R.id.c4);

        btnNext = view.findViewById(R.id.btnNext);

    }

    private void init(){

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataModel.setStandardCapacity(String.valueOf(c1.getValue()));
                dataModel.setMaxCapacity(String.valueOf(c2.getValue()));
                dataModel.setSingleBedCapacity(String.valueOf(c3.getValue()));
                dataModel.setDoubleBedCapacity(String.valueOf(c4.getValue()));

                dataModel.setDescriptionCapacity(inputDescription.getText().toString().trim());

                Log.d("DATA_TESTER" , dataModel.toString());

                context.sendBroadcast(
                        new Intent(ACTION_UPDATE_ACTIVITY)
                );
            }
        });
    }

}
