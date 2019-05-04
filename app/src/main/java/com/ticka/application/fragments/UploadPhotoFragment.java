package com.ticka.application.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.models.DataModel;

public class UploadPhotoFragment extends Fragment {

    private DataModel dataModel = MainActivity.getDataModel();

    public UploadPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upload_photo, container, false);

        return view;
    }

}
