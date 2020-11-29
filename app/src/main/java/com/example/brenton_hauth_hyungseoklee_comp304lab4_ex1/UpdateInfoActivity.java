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

public class UpdateInfoActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private TextView patientInfoTextView;
    private TextView editTextLastName;
    private TextView editTextFirstName;
    private TextView editTextDepartment;
    private TextView nurseIdTextView;
    private TextView editTextRoom;
    private TextView patientIdTextView;

    private SharedPreferences loginPrefs;
    private Nurse nurse;
    private int nurseId;
    private int patientId;
    private Patient currentPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        patientInfoTextView = findViewById(R.id.patientInfoTextView);
        patientIdTextView = findViewById(R.id.editTextTextPatientId);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextDepartment = findViewById(R.id.editTextDepartment);
        nurseIdTextView = findViewById(R.id.editTextNurseId);
        editTextRoom = findViewById(R.id.editTextRoom);

        Intent in = getIntent();
        patientId = in.getIntExtra(Patient.PATIENT_ID_EXTRA, -1);

/*        nurse = new Nurse();
        loginPrefs = PrefsHelper.getLoginPrefs(this);*/
        getList(patientId);
    }
/*    @Override
    protected void onStart() {
        super.onStart();
        //getNurseId();
        getList();
        //Toast.makeText(this.getApplicationContext(),""+nurseId,Toast.LENGTH_SHORT).show();
    }
*//*    public void getNurseId()
    {
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            nurseId = nurse.getNurseID();
        }
    }*/
    public void getList(int patientId) {
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                String output = "";
                for(Patient patient : patients)
                {
                    if(patient.getPatientId() == patientId)
                    {
                        output += "Patient ID: " + patient.getPatientId()
                                + "\nName: " + patient.getFirstName() + " " + patient.getLastName()
                                + "\nDepartment: " + patient.getDepartment()
                                + "\nNurse ID: " + patient.getNurseId()
                                + "\nRoom: " + patient.getRoom();
                        currentPatient = patient;
                    }
                    patientInfoTextView.setText(output);
                }
            }
        });
    }
    public void update(View view)
    {

        try {
            int newPatientId = Integer.parseInt(patientIdTextView.getText().toString());
            String newFirstName = editTextFirstName.getText().toString();
            String newLastName = editTextLastName.getText().toString();
            String newDepartment = editTextDepartment.getText().toString();
            int newNurseId = Integer.parseInt(nurseIdTextView.getText().toString());
            String newRoom = editTextRoom.getText().toString();
            patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
                @Override
                public void onChanged(List<Patient> patients) {
                    for(Patient patient : patients)
                    {
                        if(patient == currentPatient)
                        {
/*                            patient.setPatientId(newPatientId);
                            patient.setFirstName(newFirstName);
                            patient.setLastName(newLastName);
                            patient.setDepartment(newDepartment);
                            patient.setNurseId(newNurseId);
                            patient.setRoom(newRoom);
                            patientViewModel.update(patient);*/
                            patientViewModel.delete(patient);
                            Patient newPatient = new Patient(newPatientId,newFirstName,newLastName,newDepartment,newNurseId,newRoom);
                            patientViewModel.insert(newPatient);
                        }
                    }
                    getList(newPatientId);
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(this.getApplicationContext(),"Updatingg is failed. Please check values are correctly typed.",Toast.LENGTH_SHORT).show();
        }
    }
}
