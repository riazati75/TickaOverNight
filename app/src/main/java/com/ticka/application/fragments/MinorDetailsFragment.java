package com.ticka.application.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.helpers.SpinnerHelper;
import com.ticka.application.models.DataModel;

public class MinorDetailsFragment extends Fragment {

    private Context context;

    private DataModel dataModel = MainActivity.getDataModel();
    private EditText buildingArea , landArea;
    private Spinner spLeft , spRight , spTip;
    private TextView ca1 , ca2;

    private String[] type = {
            "نوع ساختمان",
            "ویلایی",
            "آپارتمان",
            "سویت",
            "خانه",
            "کلبه"
    };

    private String[] location = {
            "نوع منطقه ای",
            "ساحلی",
            "شهری",
            "جنگلی",
            "کوهستانی",
            "کویری",
            "روستایی"
    };

    private String[] buildingTip = {
            "تیپ سازه",
            "هم سطح",
            "دوبلکس",
            "تریبلکس"
    };

    public MinorDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minor_details, container, false);
        context = container.getContext();

        initViews(view);
        init();

        return view;
    }

    private void initViews(View view){

        landArea     = view.findViewById(R.id.landArea);
        buildingArea = view.findViewById(R.id.buildingArea);
        spLeft       = view.findViewById(R.id.spLeft);
        spRight      = view.findViewById(R.id.spRight);
        spTip        = view.findViewById(R.id.spTip);
        ca1          = view.findViewById(R.id.ca1);
        ca2          = view.findViewById(R.id.ca2);

        spLeft.setAdapter(SpinnerHelper.getSpinnerAdapter(context , location));
        spRight.setAdapter(SpinnerHelper.getSpinnerAdapter(context , type));
        spTip.setAdapter(SpinnerHelper.getSpinnerAdapter(context , buildingTip));

        ca1.setText(R.string.minor_1);
        ca2.setText(R.string.minor_2);

    }

    private void init(){

    }

}
