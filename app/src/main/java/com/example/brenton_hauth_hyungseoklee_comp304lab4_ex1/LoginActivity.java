package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_PREFS = "login";

    private static final Pattern
            usernamePattern = Pattern.compile("^[\\w]{8,}$", Pattern.CASE_INSENSITIVE),
            passwordPattern = Pattern.compile("^.{8,32}$");

    private EditText usernameText, passwordText;

    private SharedPreferences prefs;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        prefs = getSharedPreferences(LOGIN_PREFS, 0);

        // Gets nurse's username from prefs
        String username = prefs.getString("username", null);

        // Quits the login activity if they are already logged in
        if (username != null) {
            finish();
        }

        usernameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextPassword);
    }

    public void onLoginButtonClick(View v) {
        String username = validateField(
                usernameText, usernamePattern, R.string.username_err_msg);
        String password = validateField(
                passwordText, passwordPattern, R.string.password_err_msg);

        locateUser(username, password);
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

    private boolean locateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        int usr;
        try { usr = Integer.parseInt(username); }
        catch (Exception e) { return false; }

        List<Nurse> all = patientViewModel.getAllNurses().getValue();
        Log.d("ALL NURSES:", "" + (all != null ? all.size() : -1));

        LiveData<List<Nurse>> data = patientViewModel.getNurseByLoginInfo(usr, password);
        if (data.hasObservers()) {
            data.removeObservers(this);
        }
        data.observe(this, nurses -> {
            if (nurses == null) {
                Log.d("LOGIN:OBSERVE(lambda)", "nurses is null!!!");
            } else if (nurses.size() != 1) {
                Log.d("LOGIN:OBSERVE(lambda)", "nurses.size() == " + nurses.size());
            } else {
                // Saves the login data to the SharedPrefs
                Log.d("LOGIN:OBSERVE(lambda)",
                        "Found nurse!  Id: " + nurses.get(0).getNurseID());
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("username", username);
                edit.apply();
                finish(); // closes and returns to MainActivity
            }
        });
        //if (nurse == null) return false;
        // return true if found,
        // otherwise return false
        // ...
        return true;
    }

    private String validateField(EditText field, Pattern pattern, int errMsg) {
        String text = field.getText().toString();
        Matcher m = pattern.matcher(text);

        if (m.matches()) return text;

        String msg = getResources().getString(errMsg);
        field.setError(msg);
        return null;
    }
}