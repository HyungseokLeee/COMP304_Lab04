package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private final List<Patient> patients;

    public PatientAdapter(List<Patient> data) {
        patients = data;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup p, int type) {
        LayoutInflater in = LayoutInflater.from(p.getContext());
        // inflates the patient view layout
        View view = in.inflate(R.layout.patient_view_layout, p, false);

        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int i) {
        // if (i == (patients.size() - 1)) { }
        holder.setPatient(patients.get(i));
    }

    @Override
    public int getItemCount() { return patients.size(); }


    //--------------------------------------------------
    public static class PatientViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView patientNameTextView, patientIdTextView,
                patientDepTextView, patientRoomTextView;

        private final Button patientViewTestsButton, patientEditButton;

        private Patient patient;

        public PatientViewHolder(@NonNull View view) {
            super(view);
            // Find text views
            patientNameTextView = view.findViewById(R.id.patientNameTextView);
            patientIdTextView = view.findViewById(R.id.patientIdTextView);
            patientDepTextView = view.findViewById(R.id.patientDepTextView);
            patientRoomTextView = view.findViewById(R.id.patientRoomTextView);

            // Find buttons
            // Buttons may change
            patientViewTestsButton = view.findViewById(R.id.patientViewTestsButton);
            patientEditButton = view.findViewById(R.id.patientEditButton);
        }

        public void setPatient(Patient patient) {
            this.patient = patient;

            // Updates the text views to match the patient
            patientNameTextView.setText(patient.getFullName());
            patientIdTextView.setText(String.format("(ID: %s)", patient.getPatientId()));
            patientDepTextView.setText(String.format("Department: %s", patient.getDepartment()));
            patientRoomTextView.setText(String.format("Room: %s", patient.getRoom()));

            // Re-binds the click listeners
            patientViewTestsButton.setOnClickListener(this);
            patientEditButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            String temp;

            if (id == R.id.patientViewTestsButton) {
                temp = "View Patient Tests";
            } else if (id == R.id.patientEditButton) {
                temp = "Edit Patient";
            } else {
                Log.d("PATIENT_VH:onClick", id + " did not match!");
                return;
            }
            Toast.makeText(v.getContext(), temp, Toast.LENGTH_SHORT).show();
        }
    }
    //--------------------------------------------------
}