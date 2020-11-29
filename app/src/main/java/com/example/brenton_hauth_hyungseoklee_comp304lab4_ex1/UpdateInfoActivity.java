package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

        nurse = new Nurse();
        loginPrefs = PrefsHelper.getLoginPrefs(this);


    }
    @Override
    protected void onStart() {
        super.onStart();
        //getNurseId();
        getList();
        //Toast.makeText(this.getApplicationContext(),""+nurseId,Toast.LENGTH_SHORT).show();
    }
/*    public void getNurseId()
    {
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            nurseId = nurse.getNurseID();
        }
    }*/
    public void getList() {
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                String output = "";
                if(!patients.isEmpty())
                {
                    for(Patient patient:patients)
                    {
                        output += "Patient ID:" + patient.getPatientId() + "\nName: " +patient.getFirstName() + "  " + patient.getLastName()
                                + "\nDepartment: " + patient.getDepartment() + "\nNurse ID: " + patient.getNurseId() + "\nRoom: " + patient.getRoom();
                    }
                }
                else
                {
                    output += "There is no patient yet.";
                }
                patientInfoTextView.setText(output);
            }

        });
    }
    public void update(Patient patient)
    {
        
    }
    public void updatePatient(View view)
    {
    }
}
