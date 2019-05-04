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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ticka.application.MainActivity;
import com.ticka.application.R;
import com.ticka.application.helpers.SpinnerHelper;
import com.ticka.application.models.DataModel;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

public class GeneralDetailsFragment extends Fragment {

    private Context context;
    private TextView title , state , city , address , description;
    private Spinner stateList , cityList;
    private DataModel dataModel = MainActivity.getDataModel();
    private EditText inputTitle , inputAddress , inputDescription;
    private String spState , spCity;
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

        initViews(view);
        init();

        initSpinnerState(listState);
        initSpinnerCity(listCity);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLayoutData();
            }
        });

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
        inputTitle = view.findViewById(R.id.inputTitle);
        inputAddress = view.findViewById(R.id.inputAddress);
        inputDescription = view.findViewById(R.id.inputDescription);
    }

    private void init(){
        title.setText(R.string.general_title);
        state.setText(R.string.general_state);
        city.setText(R.string.general_city);
        address.setText(R.string.general_address);
        description.setText(R.string.general_description);
    }

    private void initLayoutData(){

        String title   = inputTitle.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();
        String desc    = inputDescription.getText().toString().trim();

        if(title.equals("")){
            setError(inputTitle);
        }
        else if(address.equals("")){
            setError(inputAddress);
        }
        else if(spState.equals(listState[0]) || spCity.equals(listCity[0])){
            Toast.makeText(context, "لطفا استان و شهر را صحیح و دقیق انتخاب کنید", Toast.LENGTH_SHORT).show();
        }
        else {

            dataModel.setTitle(title);
            dataModel.setAddress(address);
            dataModel.setDescriptionGeneral(desc);
            dataModel.setState(spState);
            dataModel.setCity(spCity);

            Log.d("DATA_TESTER" , dataModel.toString());

            context.sendBroadcast(
                    new Intent(ACTION_UPDATE_ACTIVITY)
            );

        }
    }

    private void setError(EditText editText){
        editText.requestFocus();
        editText.setError("این فیلد نمیتواند خالی باشد");
    }

    private void initSpinnerState(String[] list){
        stateList.setAdapter(SpinnerHelper.getSpinnerAdapter(context , list));
        stateList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spState = listState[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spState = listState[0];
            }
        });
    }

    private void initSpinnerCity(String[] list){
        cityList.setAdapter(SpinnerHelper.getSpinnerAdapter(context , list));
        cityList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spCity = listCity[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spCity = listCity[0];
            }
        });
    }

}
