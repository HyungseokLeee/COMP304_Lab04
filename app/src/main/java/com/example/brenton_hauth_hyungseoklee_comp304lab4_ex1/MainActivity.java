package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(LoginActivity.LOGIN_PREFS, 0);

        clearUsername();

        checkIfLoggedIn();
    }

    private void checkIfLoggedIn() {
        String username = prefs.getString("username", null);
        if (username == null) {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        }
    }

    private void clearUsername() {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("username", null);
        edit.apply();
    }
}