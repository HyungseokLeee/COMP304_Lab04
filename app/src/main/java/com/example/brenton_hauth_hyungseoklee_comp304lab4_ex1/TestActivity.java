package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

public class TestActivity extends AppCompatActivity {
    private EditText editTextTestId, editTextPatientId, editTextNurseId,
    editTextBPL, editTextBPH, editTextTemperature;
    private PatientViewModel patientViewModel;
    private Test test;
    private int nurseId, patientId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test = new Test();
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        Intent in = getIntent();
        patientId = in.getIntExtra(Patient.PATIENT_ID_EXTRA, -1);
        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        // HAVE to set nurse to an empty object
        Nurse nurse = new Nurse();
        nurseId = -1;

        // Only gets list if we passed a patientId, and we have a saved nurse
        if (patientId != -1 && PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            nurseId = nurse.getNurseID();
        } else {
            // Displays an error message if there's any issues
            Toast.makeText(this,
                    String.format("Issue with either Patient (%s) or nurse. (%s)",
                            patientId, nurseId),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void insertTest(View view)
    {
        editTextTestId = findViewById(R.id.editTextTestId);
        editTextBPL = findViewById(R.id.editTextBPL);
        editTextBPH = findViewById(R.id.editTextBPH);
        editTextTemperature = findViewById(R.id.editTextTemperature);
        try {
            test.setTestId(Integer.parseInt(editTextTestId.getText().toString()));
            test.setPatientID(patientId);
            test.setNurseID(nurseId);
            test.setBPL(Integer.parseInt(editTextBPL.getText().toString()));
            test.setBPH(Integer.parseInt(editTextBPH.getText().toString()));
            test.setTemperature(Float.parseFloat(editTextTemperature.getText().toString()));
            patientViewModel.insert(test);
            Toast.makeText(this.getApplicationContext(),"Entered test information is successfully stored.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
         Toast.makeText(this.getApplicationContext(),"Entered value(s) is(are) not acceptable.",Toast.LENGTH_SHORT).show();
        }
    }
}