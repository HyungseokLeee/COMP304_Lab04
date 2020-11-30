package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences loginPrefs;
    private Nurse nurse;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nurse = new Nurse();

        welcomeTextView = findViewById(R.id.mainWelcomeTextView);
        loginPrefs = PrefsHelper.getLoginPrefs(this);

        // clearUsername(); // For testing, will clear the login every time
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIfLoggedIn();
//        String str = welcomeTextView.getText().toString();
//        str += "\nonStart()";
//        welcomeTextView.setText(str);
    }

    private void checkIfLoggedIn() {
        // Was having a bug, this seem to fix it
        if (nurse == null) nurse = new Nurse();
        if (loginPrefs == null) loginPrefs = PrefsHelper.getLoginPrefs(this);

        //
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            Toast.makeText(this, "HAS NURSE", Toast.LENGTH_SHORT).show();
            String text = String.format("Welcome, %s (%s)!",
                    nurse.getFullName(), nurse.getNurseID());

            welcomeTextView.setText(text);
        } else {
            Log.d("MAIN:checkIfLoggedIn", "Nurse is null!");
            launchLoginActivity(); Log.d("MAIN:checkIfLoggedIn",
                    "Found nurse!  Id: " + nurse.getNurseID());
        }
    }

    public void onNavigationItemClick(View v) {
        Class<?> activity;

        int id = v.getId();
        // Switch case shows shows warning
        if (id == R.id.mainPatientsTextView) {
            activity = PatientActivity.class;
        // } else if () {
        }
        else {
            Log.d("MAIN:onNavItemClick", "view Id did not match!");
            return;
        }

        Intent in = new Intent(this, activity);
        startActivity(in);
    }

    public void onLogoutButtonClick(View v) {
        PrefsHelper.saveNurse(loginPrefs, null);
        launchLoginActivity();
    }

    private void launchLoginActivity() {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }
}