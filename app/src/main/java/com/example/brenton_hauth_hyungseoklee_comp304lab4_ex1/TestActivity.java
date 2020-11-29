package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    private EditText editTextTestId, editTextPatientId, editTextNurseId,
    editTextBPL, editTextBPH, editTextTemperature;
    private PatientViewModel patientViewModel;
    private Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test = new Test();
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
    }
    public void insertTest(View view)
    {
        editTextTestId = findViewById(R.id.editTextTextTestId);
        editTextPatientId = findViewById(R.id.editTextTextPatientId);
        editTextNurseId = findViewById(R.id.editTextNurseId);
        editTextBPL = findViewById(R.id.editTextTextBPL);
        editTextBPH = findViewById(R.id.editTextTextBPH);
        editTextTemperature = findViewById(R.id.editTextTextTemperature);
        try {
            test.setTestId(Integer.parseInt(editTextTestId.getText().toString()));
            test.setPatientID(Integer.parseInt(editTextPatientId.getText().toString()));
            test.setNurseID(Integer.parseInt(editTextNurseId.getText().toString()));
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