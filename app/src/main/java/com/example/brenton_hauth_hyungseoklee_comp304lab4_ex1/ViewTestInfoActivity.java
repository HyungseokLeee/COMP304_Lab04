package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

import java.util.List;

public class ViewTestInfoActivity
        extends AppCompatActivity
        implements Observer<List<Test>> {

    private PatientViewModel patientViewModel;
    private RecyclerView recyclerView;
    private int patientId;
    private String patientName;

    private TextView testInfoPatientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        Intent in = getIntent();
        patientId = in.getIntExtra(Patient.PATIENT_ID_EXTRA, -1);
        patientName = in.getStringExtra(Patient.PATIENT_NAME_EXTRA);

        testInfoPatientId = findViewById(R.id.testInfoPatientIdTextView);

        if (patientId == -1) {
            Toast.makeText(this,
                    "Could not retrieve Patient ID!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        testInfoPatientId.setText(String.format("Viewing tests for %s", patientName));

        initRecycler();

        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        if (PrefsHelper.hasSavedNurse(loginPrefs)) {
            LiveData<List<Test>> tests =
                    patientViewModel.getTestsForPatient(patientId);
            tests.observe(this, this);
        } else {
            Toast.makeText(this,
                "You are not logged in!",
                Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.testInfoRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onChanged(List<Test> tests) {
        TestAdapter adapter = new TestAdapter(tests);
        recyclerView.setAdapter(adapter);
    }
}