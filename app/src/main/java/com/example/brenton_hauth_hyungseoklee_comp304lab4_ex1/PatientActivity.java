package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;
import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.ValidationHelper;

import java.util.List;
import java.util.Random;

public class PatientActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private static Random random = new Random();

    private PatientViewModel patientViewModel;

    private RecyclerView recyclerView;
    private EditText patientIdEditText,
        patientRoomEditText, patientFirstNameEditText,
        patientLastNameEditText, patientDepEditText;

    private Nurse nurse;
    private Patient newPatient;
    private int nextRandomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        initRecycler();


        nurse = new Nurse();
        newPatient = new Patient();
        nextRandomId = randomId();
        // Gets login prefs
        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        // Checks if nurse is stored in login prefs
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            initUI();

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

    private void initUI() {
        // sets header for Nurse
        setTitle(String.format("Patients for %s", nurse.getNurseID()));

        patientIdEditText = findViewById(R.id.patientIdEditText);
        patientRoomEditText = findViewById(R.id.patientRoomEditText);
        patientFirstNameEditText = findViewById(R.id.patientFirstNameEditText);
        patientLastNameEditText = findViewById(R.id.patientLastNameEditText);
        patientDepEditText = findViewById(R.id.patientDepEditText);

        patientDepEditText.setHint(String.format("Department (%s)", nurse.getDepartment()));
    }

    @Override
    public void onChanged(List<Patient> patients) {
        PatientAdapter adapter = new PatientAdapter(patients);
        recyclerView.setAdapter(adapter);
    }

    public void onAddPatientButtonClick(View v) {
        //if (ValidationHelper.validateId(patientIdEditText, )) {

        //}
    }

    private static int randomId() {
        return 0;
    }
}