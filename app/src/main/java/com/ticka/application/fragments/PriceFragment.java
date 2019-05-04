package com.ticka.application.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.models.DataModel;

public class PriceFragment extends Fragment {

    private DataModel dataModel = MainActivity.getDataModel();

    public PriceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_price, container, false);



        return root;
    }

}
