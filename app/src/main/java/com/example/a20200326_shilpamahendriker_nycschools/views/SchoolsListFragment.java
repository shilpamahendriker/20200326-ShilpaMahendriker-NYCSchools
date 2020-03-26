package com.example.a20200326_shilpamahendriker_nycschools.views;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.a20200326_shilpamahendriker_nycschools.R;
import com.example.a20200326_shilpamahendriker_nycschools.adapter.SchoolAdapter;
import com.example.a20200326_shilpamahendriker_nycschools.model.School;
import com.example.a20200326_shilpamahendriker_nycschools.viewmodels.SchoolViewModel;
import java.util.ArrayList;


public class SchoolsListFragment extends Fragment {
    RecyclerView recyclerView;
    SchoolAdapter adapter;

    private final ArrayList<School> schoolList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_schools_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        setupRecyclerView();


        // Calling the retrofit initialization method from the School model and retrieving the data into arraylist
        final SchoolViewModel schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);
        schoolViewModel.getSchools().observe(getViewLifecycleOwner(), new Observer<ArrayList<School>>() {
            @Override
            public void onChanged(@Nullable ArrayList<School> schools) {
                schoolList.addAll(schools);
                adapter.notifyDataSetChanged();

            }
        });
        return view;
    }
    // Method to setup recycler view wit the help of School Adapter

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getContext(), LinearLayoutManager.VERTICAL, false);
        if (adapter == null) {
            adapter = new SchoolAdapter(getContext(), schoolList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
