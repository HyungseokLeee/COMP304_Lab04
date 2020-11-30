package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.ValidationHelper;

import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText signUpUsername,
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
        nurse.setNurseID(-1);

        signUpFirstName = findViewById(R.id.signUpFirstName);
        signUpLastName = findViewById(R.id.signUpLastName);
        signUpDepartment = findViewById(R.id.signUpDepartment);
        signUpUsername = findViewById(R.id.signUpUsername);
        signUpPassword = findViewById(R.id.signUpPassword);

        patientViewModel.getAllNurses().observe(this, nurses -> {
            if (nurses == null) {
                Log.d("SIGN_UP:OBSERVE(lambda)", "Nurses was null!!!");
                return;
            }
            for (Nurse n : nurses) {
                if (n.getNurseID() == nurse.getNurseID()) {
                    Log.d("SIGN_UP:OBSERVE(lambda)",
                            "Found nurse with id: " + n.getNurseID());
                    finish();
                    return;
                }
            }
            Log.d("SIGN_UP:OBSERVE(lambda)", "Could not locate nurse!");
        });
    }


    public void onSignUpButtonClick(View v) {

        // Validates all sign up fields & sets values accordingly
        boolean result = ValidationHelper.validateId(signUpUsername, nurse::setNurseID);
        result &= ValidationHelper.validatePassword(signUpPassword, nurse::setPassword);
        result &= ValidationHelper.validateName(signUpFirstName, nurse::setFirstName);
        result &= ValidationHelper.validateName(signUpLastName, nurse::setLastName);
        result &= ValidationHelper.validate(signUpDepartment,
                ".+", R.string.department_err_msg, nurse::setDepartment);

        if (result) {
            // If all results are find, then try to insert the nurse into db
            try {
                patientViewModel.insert(nurse);
            } catch (Exception e) {
                Toast.makeText(this,
                    "Could not sign up.",
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private boolean validate(TextView field, String regex, int errMsg, Parameter p) {
//        CharSequence txt = field.getText();
//        if (Pattern.matches(regex, txt)) {
//            try {
//                p.fn(txt.toString());
//                return true;
//            } catch (Exception ignore) { }
//        }
//        String err = getResources().getString(errMsg);
//        field.setError(err);
//        return false;
//    }

    interface Parameter { void fn(String s); }
}