package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

import java.util.List;

public class PatientActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private RecyclerView recyclerView;
    private PatientViewModel patientViewModel;
    private Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        initRecycler();

        nurse = new Nurse();

        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            LiveData<List<Patient>> patients =
                    patientViewModel.getPatientsForNurse(nurse.getNurseID());
            patients.observe(this, this);
        } else {
            Toast.makeText(this,
                "You are not logged in!",
                Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.patientRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onChanged(List<Patient> patients) {
        PatientAdapter adapter = new PatientAdapter(patients);
        recyclerView.setAdapter(adapter);
    }
}