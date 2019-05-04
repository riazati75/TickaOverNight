package com.ticka.application.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.models.DataModel;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

public class RulesFragment extends Fragment {

    private Context context;
    private DataModel dataModel = MainActivity.getDataModel();

    public RulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_rules, container, false);
       context = container.getContext();

       initViews(view);

        return view;
    }

    private void initViews(View view) {

        final Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        final CheckBox accept = view.findViewById(R.id.accept);

        accept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    btnNext.setEnabled(true);
                }
                else {
                    btnNext.setEnabled(false);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.sendBroadcast(
                        new Intent(ACTION_UPDATE_ACTIVITY)
                );
            }
        });
    }

}
