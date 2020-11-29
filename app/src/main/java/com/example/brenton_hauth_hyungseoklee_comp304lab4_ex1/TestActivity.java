package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private int patientId;
    private TextView patientIdTextView;
    private TextView testListTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        patientIdTextView = findViewById(R.id.patientIdFormPlainText);
        testListTextView = findViewById(R.id.testListTextView);
    }
    public void onClickPatientIdButton(View view)
    {
        try{
            patientId = Integer.parseInt(patientIdTextView.getText().toString());
            Toast.makeText(this.getApplicationContext(), "Patient ID " + patientId + "'s test(s) is(are) displayed.",Toast.LENGTH_SHORT).show();
            getList();
        }
        catch (Exception e)
        {
            Toast.makeText(this.getApplicationContext(),"Patient ID must be consist of integers",Toast.LENGTH_SHORT).show();
        }
    }
    public void getList()
    {
        patientViewModel.getTestsForPatient(patientId).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                String output = "";
                for(Test test:tests)
                {
                    output += "\nTestID: " + test.getTestId()
                    + "\nPatientID: " + test.getPatientID() + "\nNurseID: " + test.getNurseID()
                    + "\nBPH: " + test.getBPH() + "\n BPL: " + "\n Temperature: " + test.getTemperature()+"\n==========";
                }
                testListTextView.setText(output);
            }
        });
    }

}