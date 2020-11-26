package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestAdapter
        extends RecyclerView.Adapter<TestAdapter.TestViewHolder>
        implements View.OnClickListener {

    private final List<Test> tests;

    public TestAdapter(List<Test> data) {
        tests = data;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup p, int type) {
        LayoutInflater in = LayoutInflater.from(p.getContext());

        View view = in.inflate(R.layout.test_view_layout, p, false);
        view.setOnClickListener(this);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int i) {
        holder.setTest(tests.get(i));
    }

    @Override
    public int getItemCount() { return tests.size(); }

    @Override
    public void onClick(View view) { /* ... */ }

    //--------------------------------------------------
    public static class TestViewHolder extends RecyclerView.ViewHolder {

        private final TextView testTextView;

        public TestViewHolder(@NonNull View view) {
            super(view);
            testTextView = view.findViewById(R.id.testTextView);
        }

        @SuppressLint("SetTextI18n")
        public void setTest(Test test) {
            String text = Integer.toString(test.getTestID());
            // Cannot set id directly because it is an int
            testTextView.setText(text);
        }
    }
    //--------------------------------------------------
}
