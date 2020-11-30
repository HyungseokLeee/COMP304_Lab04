package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

import java.util.List;

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
            test.setPatientID(patientId);
            test.setNurseID(nurseId);
            int testId = Integer.parseInt(editTextTestId.getText().toString());
            int BPL = Integer.parseInt(editTextBPL.getText().toString());
            int BPH = Integer.parseInt(editTextBPH.getText().toString());
            if(validate("testId",testId,0))
            {
                test.setTestId(testId);
            }
            else
            {
                Toast.makeText(this.getApplicationContext(),"Entered TestID value(s) is(are) not acceptable.",Toast.LENGTH_SHORT).show();
                return;
            }
            if(validate("BP",BPL,BPH))
            {
                test.setBPL(BPL);
                test.setBPH(BPH);
            }
            else
            {
                Toast.makeText(this.getApplicationContext(),"Entered Blood pressure value(s) is(are) not acceptable.",Toast.LENGTH_SHORT).show();
                return;
            }
            float temperature = Float.parseFloat(editTextTemperature.getText().toString());
            if(validate(temperature))
            {
                test.setTemperature(temperature);
            }
            else
            {
                Toast.makeText(this.getApplicationContext(),"Entered temperature value(s) is(are) not acceptable.",Toast.LENGTH_SHORT).show();
                return;
            }
            patientViewModel.insert(test);
            Toast.makeText(this.getApplicationContext(),"Entered test information is successfully stored.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
         Toast.makeText(this.getApplicationContext(),"Entered value(s) is(are) not acceptable. Failed to stroe the test data",Toast.LENGTH_SHORT).show();
         return;
        }
        Intent intent = new Intent(this,ViewTestInfoActivity.class);
        intent.putExtra(Patient.PATIENT_ID_EXTRA,patientId);
        view.getContext().startActivity(intent);
    }
    private boolean validate(float f)
    {
        if(f > 0)
        {
            return true;
        }
        return false;
    }
    private boolean validate(String s, int i1,int i2)
    {
        final boolean[] testIdResult = new boolean[1];
        testIdResult[0] = true;
        if(s=="BP" && i1 < i2 && i1>0 && i2>0)
        {
            return true;
        }
        else if(s=="testId" && i1 > 0 && i2 == 0)
        {
            patientViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
                @Override
                public void onChanged(List<Test> tests) {
                    for(Test test:tests)
                    {
                        if(test.getTestId() == i1)
                        {
                            testIdResult[0] = false;
                            break;
                        }
                    }
                }
            });
            return testIdResult[0];
        }
        return false;
    }
}