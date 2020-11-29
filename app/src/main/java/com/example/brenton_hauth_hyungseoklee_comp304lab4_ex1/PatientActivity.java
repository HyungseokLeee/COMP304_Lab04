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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

import java.util.List;

public class PatientActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private TextView patientTitleTextView;

    private RecyclerView recyclerView;
    private PatientViewModel patientViewModel;
    private Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        patientTitleTextView = findViewById(R.id.patientTitleTextView);

        initRecycler();

        nurse = new Nurse();

        // Gets login prefs
        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        // Checks if nurse is stored in login prefs
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            // sets header for Nurse
            patientTitleTextView.setText(
                    String.format("Patients for %s", nurse.getNurseID()));

            // Gets patients associated with 'nurse'
            LiveData<List<Patient>> patients =
                    patientViewModel.getPatientsForNurse(nurse.getNurseID());

            patients.observe(this, this); // Attaches observer
        } else {
            // Displays message just in case
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

    public void onAddPatientButtonClick(View v) {
        // TODO: Navigate to Update Info activity to add patient
        Toast.makeText(this,
            "Add patient for nurse " + nurse.getNurseID(),
            Toast.LENGTH_SHORT).show();
    }
}