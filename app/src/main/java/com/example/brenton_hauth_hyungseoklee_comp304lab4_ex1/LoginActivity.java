package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;
import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.ValidationHelper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
//    private static final Pattern
//            usernamePattern = Pattern.compile("^[\\w]{8,}$", Pattern.CASE_INSENSITIVE),
//            passwordPattern = Pattern.compile("^.{8,32}$");

    private EditText usernameText, passwordText;

    private SharedPreferences loginPrefs;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        loginPrefs = PrefsHelper.getLoginPrefs(this);

        // Quits the login activity if they are already logged in
        if (PrefsHelper.hasSavedNurse(loginPrefs)) {
            finish();
        }

        usernameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);
    }

    public void onLoginButtonClick(View v) {
        Nurse nurse = new Nurse();
//        final int[] nurseId = new int[1];

  /*      String username = validateField(
                usernameText, usernamePattern, R.string.username_err_msg);*/
        //String password;// = validateField(
                //passwordText, passwordPattern, R.string.password_err_msg);

//        locateUser(username, password);

        boolean valid1 = ValidationHelper.validateId(usernameText, nurse::setNurseID);
        boolean valid2 = ValidationHelper.validatePassword(passwordText, nurse::setPassword);

        if (valid1 && valid2) {
            locateNurse(nurse);
        }
    }

    public void onSignUpTextClick(View v) {
        Intent in = new Intent(this, SignUpActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent e) {
        // Overrides the back button so user
        // can't back out of login activity
        return key == KeyEvent.KEYCODE_BACK || super.onKeyDown(key, e);
    }

    private void locateNurse(Nurse nurse) {


        LiveData<List<Nurse>> data = patientViewModel.getNurseByLoginInfo(
                nurse.getNurseID(), nurse.getPassword());
        if (data.hasObservers()) {
            data.removeObservers(this);
        }
        data.observe(this, nurses -> {
            if (nurses == null || nurses.size() != 1) {
                Log.d("LOGIN:OBSERVE(lambda)", "Nurses: " + nurses);
                Toast.makeText(this,
                    "Unable to login with provided credentials.",
                    Toast.LENGTH_SHORT).show();
            } else {
                // Saves the login data to the SharedPrefs
                Nurse n = nurses.get(0);
                if (n.getNurseID() != nurse.getNurseID()) {
                    return;
                }
                Log.d("LOGIN:OBSERVE(lambda)",
                        "Found nurse!  Id: " + n.getNurseID());
                PrefsHelper.saveNurse(loginPrefs, n);
                finish(); // closes and returns to MainActivity
            }
        });
        //if (nurse == null) return false;
        // return true if found,
        // otherwise return false
        // ...
    }
}