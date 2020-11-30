package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

import java.util.List;

public class UpdateInfoActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private PatientViewModel patientViewModel;
    private TextView patientInfoTextView;
    private TextView editTextLastName;
    private TextView editTextFirstName;
    private TextView editTextDepartment;
    private TextView editTextRoom;

    private int nurseId, patientId;
    private Patient currentPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        patientInfoTextView = findViewById(R.id.patientInfoTextView);
        editTextLastName = findViewById(R.id.editTextTestId);
        editTextFirstName = findViewById(R.id.editTextBPL);
        editTextDepartment = findViewById(R.id.editTextBPH);
        editTextRoom = findViewById(R.id.editTextTemperature);

        Intent in = getIntent();
        patientId = in.getIntExtra(Patient.PATIENT_ID_EXTRA, -1);
        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        // HAVE to set nurse to an empty object
        Nurse nurse = new Nurse();
        nurseId = -1;

        // Only gets list if we passed a patientId, and we have a saved nurse
        if (patientId != -1 && PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            nurseId = nurse.getNurseID();
            getList();
        } else {
            // Displays an error message if there's any issues
            Toast.makeText(this,
                String.format("Issue with either Patient (%s) or nurse. (%s)",
                        patientId, nurseId),
                Toast.LENGTH_SHORT).show();
        }
    }

    public void getList() {
        // Binds to class directly instead of creating a lambda (is cheaper on resources)
        patientViewModel.getAllPatients().observe(this, this);
    }

    @Override // from implementing 'Observer<List<Patient>>'
    public void onChanged(List<Patient> patients) {
        for(Patient patient : patients) {
            // Checking id, instead of "currentPatient == patient";
            if(patient.getPatientId() == patientId) {

                String output = "Patient ID: " + patient.getPatientId()
                        + "\nName: " + patient.getFirstName() + " " + patient.getLastName()
                        + "\nDepartment: " + patient.getDepartment()
                        + "\nNurse ID: " + patient.getNurseId()
                        + "\nRoom: " + patient.getRoom();
                currentPatient = patient;
                patientInfoTextView.setText(output);
                // Added break because, we don't need to continue
                // looping when we found the patient
                break;
            }
        }
    }

    public void update(View view) {
        try {
            // TODO: Disable 'patientIdTextView' and 'nurseIdTextView'
            // If you want, you can use editText.setError() to give the user a specific error
            //int newPatientId = Integer.parseInt(patientIdTextView.getText().toString());

            String newFirstName = editTextFirstName.getText().toString();
            String newLastName = editTextLastName.getText().toString();
            String newDepartment = editTextDepartment.getText().toString();
            //int newNurseId = Integer.parseInt(nurseIdTextView.getText().toString());
            String newRoom = editTextRoom.getText().toString();
            // Removed observer
            // if nothing is entered, use currentPatient to fill the empty fields.
            Patient newPatient = new Patient(
                patientId, // PK don't change,
                newFirstName.isEmpty()
                    ? currentPatient.getFirstName()
                    : newFirstName,
                newLastName.isEmpty()
                    ? currentPatient.getLastName()
                    : newLastName,
                newDepartment.isEmpty()
                    ? currentPatient.getDepartment()
                    : newDepartment,
                nurseId, // FK don't change
                newRoom.isEmpty()
                    ? currentPatient.getRoom()
                    : newRoom
            );
            // Calling update works when all fields are filled
            patientViewModel.update(newPatient);
            // we don't need to call getList() here because we're observing it
        } catch(Exception e) {
            // System.err.println(e);
            Toast.makeText(this,
                    "Updating failed. Please check values are correctly typed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
