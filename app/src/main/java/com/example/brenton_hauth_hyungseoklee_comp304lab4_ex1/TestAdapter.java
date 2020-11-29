package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private final List<Test> tests;

    public TestAdapter(List<Test> data) {
        tests = data;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup p, int type) {
        LayoutInflater in = LayoutInflater.from(p.getContext());

        View view = in.inflate(R.layout.test_view_layout, p, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int i) {
        holder.setTest(tests.get(i));
    }

    @Override
    public int getItemCount() { return tests.size(); }


    //--------------------------------------------------
    public static class TestViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView testIdTextView,
                testBPHTextView, testBPLTextView,
                testPatientIdTextView, testTempTextView;

        private final Button testEditButton;

        private Test test;

        public TestViewHolder(@NonNull View view) {
            super(view);
            testIdTextView = view.findViewById(R.id.testIdTextView);
            testBPHTextView = view.findViewById(R.id.testBPHTextView);
            testBPLTextView = view.findViewById(R.id.testBPLTextView);
            testPatientIdTextView = view.findViewById(R.id.testPatientIdTextView);
            testTempTextView = view.findViewById(R.id.testTempTextView);
            testEditButton = view.findViewById(R.id.testEditButton);
        }


        public void setTest(Test test) {
            this.test = test;
            testIdTextView.setText(String.format("Test ID: %s", test.getTestId()));
            testPatientIdTextView.setText(String.format("(Patient: %s)", test.getPatientID()));
            testBPHTextView.setText(String.format("BPH: %s", test.getBPH()));
            testBPLTextView.setText(String.format("BPL: %s", test.getBPL()));
            testTempTextView.setText(String.format("Temperature: %s°C", test.getBPL()));

            testEditButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),
                "Edit " + test.getTestId(),
                Toast.LENGTH_SHORT).show();
        }
    }
    //--------------------------------------------------
}
