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
import android.widget.Spinner;
import android.widget.TextView;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.models.DataModel;
import com.ticka.application.helpers.SpinnerHelper;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

public class GeneralDetailsFragment extends Fragment {

    private Context context;
    private TextView title , state , city , address , description;
    private Spinner stateList , cityList;

    private DataModel dataModel;
    private Button btnNext;

    private String[] listState = {
            "انتخاب کنید",
            "تهران",
            "کرج"
    };

    private String[] listCity = {
            "انتخاب کنید",
            "اندیشه",
            "ملارد"
    };


    public GeneralDetailsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_details, container, false);
        context = container.getContext();
        dataModel = MainActivity.getActivity().getDataModel();

        initViews(view);
        init();

        initSpinnerState(listState);
        initSpinnerCity(listCity);

        return view;
    }

    private void initViews(View view){
        title = view.findViewById(R.id.title);
        state = view.findViewById(R.id.state);
        city = view.findViewById(R.id.city);
        address = view.findViewById(R.id.address);
        btnNext = view.findViewById(R.id.btnNext);
        description = view.findViewById(R.id.description);
        stateList = view.findViewById(R.id.stateList);
        cityList = view.findViewById(R.id.cityList);
    }

    private void init(){
        title.setText(R.string.general_title);
        state.setText(R.string.general_state);
        city.setText(R.string.general_city);
        address.setText(R.string.general_address);
        description.setText(R.string.general_description);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.sendBroadcast(
                        new Intent(ACTION_UPDATE_ACTIVITY)
                );
            }
        });
    }

    private void initSpinnerState(String[] list){
        stateList.setAdapter(SpinnerHelper.getSpinnerAdapter(context , list));
    }

    private void initSpinnerCity(String[] list){
        cityList.setAdapter(SpinnerHelper.getSpinnerAdapter(context , list));
    }

}
