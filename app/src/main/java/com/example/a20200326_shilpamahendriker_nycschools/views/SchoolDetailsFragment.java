package com.example.a20200326_shilpamahendriker_nycschools.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.a20200326_shilpamahendriker_nycschools.R;
import com.example.a20200326_shilpamahendriker_nycschools.model.SchoolPerformance;
import com.example.a20200326_shilpamahendriker_nycschools.viewmodels.SchoolPerformanceViewModel;

import java.util.ArrayList;



public class SchoolDetailsFragment extends Fragment {

    TextView textViewSchoolName;
    TextView textViewSchoolLocation;
    TextView textViewSatScoresLbl;
    TextView textViewCriticalReading;
    TextView textViewMathAvg;
    TextView textViewWritingAvg;

    private String location;
    private String name;

    public SchoolDetailsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_school_details, container, false);


        // Extracting data received through the intent
        String dbn = getArguments().getString("DBN");
        location = getArguments().getString("LOCATION");
        name = getArguments().getString("NAME");
        Log.v("myLogs", "DBN " + dbn);

        //Defining textviews to display school data and sat scores

        textViewSchoolName = view.findViewById(R.id.txtSchoolName);
        textViewSchoolLocation = view.findViewById(R.id.txtSchoolLocation);
        textViewSatScoresLbl = view.findViewById(R.id.txtSatScoresLabel);
        textViewCriticalReading = view.findViewById(R.id.txtCriticalReadingAvg);
        textViewMathAvg = view.findViewById(R.id.txtMathAvg);
        textViewWritingAvg = view.findViewById(R.id.txtWritingAvg);

        // Calling the refrofit initialization method from the School model and retrieving the data into arraylist

        final SchoolPerformanceViewModel schoolPerformanceViewModel = ViewModelProviders.of(this).get(SchoolPerformanceViewModel.class);
        schoolPerformanceViewModel.getSchoolDetails(dbn).observe(getViewLifecycleOwner(), new Observer<ArrayList<SchoolPerformance>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SchoolPerformance> schoolDetails) {

                // Assiging appropriate text if the no sat scores are found for the selected school in the main activity

                if (schoolDetails.isEmpty()){

                    textViewSchoolName.setText(name);
                    location = location.split("\\(")[0];
                    textViewSchoolLocation.setText(location);
                    textViewSatScoresLbl.setText(R.string.no_sat_data);
                    textViewCriticalReading.setVisibility(View.GONE);
                    textViewMathAvg.setVisibility(View.GONE);
                    textViewWritingAvg.setVisibility(View.GONE);


                } else {

                    // Assiging correspoding text  to the textviews when sat scores are found for the selected school in the main activity
                    String schoolName = schoolDetails.get(0).getSchoolName();
                    String criticalReadingAvgScore = schoolDetails.get(0).getSatCriticalReadingAvgScore();
                    String satMathAvgScore = schoolDetails.get(0).getSatMathAvgScore();
                    String satWritingAvgScore = schoolDetails.get(0).getSatWritingAvgScore();
                    textViewSchoolName.setText(schoolName);
                    location = location.split("\\(")[0];
                    textViewSchoolLocation.setText(location);
                    textViewSatScoresLbl.setText(R.string.sat_scores);
                    String criticalReadingLabel = getResources().getString(R.string.critical_reading_label);
                    textViewCriticalReading.setText(getResources().getString(R.string.critical_reading_label) + (criticalReadingAvgScore));
                    textViewMathAvg.setText(getResources().getString(R.string.Math_average_label) + (satMathAvgScore));
                    textViewWritingAvg.setText(getResources().getString(R.string.Writing_average) + (satWritingAvgScore));

                }



            }
        });
        return view;
    }


}

