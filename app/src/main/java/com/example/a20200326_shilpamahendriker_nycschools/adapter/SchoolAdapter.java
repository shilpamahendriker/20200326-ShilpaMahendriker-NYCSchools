package com.example.a20200326_shilpamahendriker_nycschools.adapter;

import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a20200326_shilpamahendriker_nycschools.R;
import com.example.a20200326_shilpamahendriker_nycschools.model.School;
import com.example.a20200326_shilpamahendriker_nycschools.views.SchoolDetailsFragment;

import java.util.ArrayList;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {
    //this context used to inflate the layout
    private Context context;

    //storing all the Locations in a list
    private ArrayList<School> Schools;

    //getting the context and Locations list with constructor
    public SchoolAdapter(Context context, ArrayList<School> Schools) {

        this.context = context;
        this.Schools = Schools;

    }
    @NonNull
    @Override
    public SchoolAdapter.SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating and returning the view holder

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_inner_layout, viewGroup,false);
        return new SchoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapter.SchoolViewHolder schoolViewHolder,  final int i) {
        //getting the location of the specified position
        final School School = Schools.get(i);

        //binding the data with the view holder views
        schoolViewHolder.textSchoolName.setText(School.getSchoolName());
        schoolViewHolder.textSchoolBorough.setText("Borough: " + School.getBorough());




        //Creating on click listener for the item selected on the view holder
        schoolViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                School selection = Schools.get(i);

                // Sending DBN, Location and school name through the intent to school details activity
                Log.v("test","testing");
                Bundle bundle = new Bundle();
                bundle.putString("DBN", selection.getDbn());
                bundle.putString("LOCATION", selection.getLocation());
                bundle.putString("NAME",selection.getSchoolName());
                Fragment fragment = new SchoolDetailsFragment();
                fragment.setArguments(bundle);


                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment,null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Schools.size();
    }

    class SchoolViewHolder extends RecyclerView.ViewHolder {
        TextView textSchoolName,textSchoolBorough;


        /*constructor that accepts the entire item row and lookups to find each subview*/

        public SchoolViewHolder(View itemView) {
            super(itemView);
            textSchoolName = itemView.findViewById(R.id.txtSchoolName);
            textSchoolBorough = itemView.findViewById(R.id.txtBorough);


        }
    }
}
