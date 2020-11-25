package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter
        extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder>
        implements View.OnClickListener {

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
        view.setOnClickListener(this);

        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int i) {
        holder.setPatient(patients.get(i));
    }

    @Override
    public int getItemCount() { return patients.size(); }

    @Override
    public void onClick(View view) {
        // May move listener to PatientViewHolder
        // ...
    }


    //--------------------------------------------------
    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView patientTextView;

        public PatientViewHolder(@NonNull View view) {
            super(view);
            patientTextView = view.findViewById(R.id.patientTextView);
            // ...
        }

        public void setPatient(Patient patient) {
            patientTextView.setText(patient.getFullName());
            // ...
        }
    }
    //--------------------------------------------------
}