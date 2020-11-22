package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGIN_PREFS = "login";

    private static final Pattern
            usernamePattern = Pattern.compile("^[\\w]{8,}$", Pattern.CASE_INSENSITIVE),
            passwordPattern = Pattern.compile("^.{8,32}$");

    private EditText usernameText, passwordText;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        if (locateUser(username, password)) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("username", username);
            edit.apply();
            finish();
        }
    }

    private boolean locateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
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