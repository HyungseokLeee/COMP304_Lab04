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

import java.util.List;

public class PatientActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private RecyclerView recyclerView;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        initRecycler();

        int nurseId = getNurseFromPrefs();
        if (nurseId == -1) return;
        LiveData<List<Patient>> patients = patientViewModel.getPatientsForNurse(nurseId);
        onChanged(patients.getValue());
        patients.observe(this, this);
    }

    private int getNurseFromPrefs() {
        SharedPreferences prefs = getSharedPreferences(LoginActivity.LOGIN_PREFS, 0);
        String username = prefs.getString("username", null);
        if (username == null) return -1;

        try {
            return Integer.parseInt(username);
        } catch (Exception e) { return -1; }
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