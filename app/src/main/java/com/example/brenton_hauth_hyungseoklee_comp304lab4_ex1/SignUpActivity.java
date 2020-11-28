package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private TextView signUpUsername,
            signUpPassword, signUpDepartment,
            signUpFirstName, signUpLastName;

    private PatientViewModel patientViewModel;

    private Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        nurse = new Nurse();

        signUpFirstName = findViewById(R.id.signUpFirstName);
        signUpLastName = findViewById(R.id.signUpLastName);
        signUpDepartment = findViewById(R.id.signUpDepartment);
        signUpUsername = findViewById(R.id.signUpUsername);
        signUpPassword = findViewById(R.id.signUpPassword);
    }


    public void onSignUpButtonClick(View v) {
        boolean result = true;
            if (!validate(signUpUsername, "^\\d{8,}$",
                R.string.username_err_msg,
                (s) -> nurse.setNurseID(Integer.parseInt(s)))) {
                result = false;
            }
            if (!validate(signUpPassword, "^.{8,32}$",
                R.string.password_err_msg,
                (s) -> nurse.setPassword(s))) {
                result = false;
            }
            if (!validate(signUpFirstName, "^[\\w\\-']+$",
                R.string.first_name_err_msg,
                (s) -> nurse.setFirstName(s))) {
                result = false;
            }
            if (!validate(signUpLastName, "^[\\w\\-']+$",
                R.string.last_name_err_msg,
                (s) -> nurse.setLastName(s))) {
                result = false;
            }
            if (!validate(signUpDepartment, "^\\w+$",
                R.string.department_err_msg,
                (s) -> nurse.setDepartment(s))) {
                result = false;
            }

        if (result) {
            try {
                patientViewModel.insert(nurse);
            } catch (Exception e) {
                Toast.makeText(this,
                    "Could not sign up.",
                    Toast.LENGTH_SHORT).show();
                return;
            }
            finish();
        }
    }

    private boolean validate(TextView field, String regex, int errMsg, Parameter p) {
        CharSequence txt = field.getText();
        if (Pattern.matches(regex, txt)) {
            try {
                p.fn(txt.toString());
                return true;
            } catch (Exception ignore) { }
        }
        String err = getResources().getString(errMsg);
        field.setError(err);
        return false;
    }

    interface Parameter { void fn(String s); }
}