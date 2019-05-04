package com.ticka.application.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.adapters.CheckboxAdapter;
import com.ticka.application.models.DataModel;

public class UserRulesFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private CheckboxAdapter adapter;
    private DataModel dataModel = MainActivity.getDataModel();

    public UserRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_rules, container, false);

        context = container.getContext();

        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL , false
        ));
        recyclerView.setHasFixedSize(true);

        adapter = new CheckboxAdapter(context , null);
        recyclerView.setAdapter(adapter);

        return root;
    }

}
