package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.Nurse;

public class PrefsHelper {
    private static final String
        LOGIN_PREFS = "login_prefs",
        PREF_NURSE_ID = "nurse_username",
        PREF_NURSE_FIRST_NAME = "nurse_first_name",
        PREF_NURSE_LAST_NAME = "nurse_last_name",
        PREF_NURSE_DEPARTMENT = "nurse_department",
        PREF_NURSE_PASSWORD = "nurse_password";


    // Quick way to get the login prefs
    public static SharedPreferences getLoginPrefs(Context ctx) {
        return ctx.getSharedPreferences(LOGIN_PREFS, 0);
    }

    // Retrieves saved nurse from prefs
    public static Nurse getSavedNurse(SharedPreferences prefs) {
        Nurse nurse = new Nurse();
        return hasSavedNurse(prefs, nurse) ? nurse : null;
    }

    public static boolean hasSavedNurse(SharedPreferences prefs) {
        try {
            // Quicker check to see if id is stored in prefs
            return intFromPref(prefs, PREF_NURSE_ID) != 0;
        } catch (Exception e) { return false; }
    }

    // saves a nurse from prefs, and saves it to result
    public static boolean hasSavedNurse(SharedPreferences prefs, @NonNull Nurse result) {
        try {
            // Assigns all fields of the nurse,
            // will stop if there's an error
            result.setNurseID(intFromPref(prefs, PREF_NURSE_ID));
            result.setFirstName(stringFromPref(prefs, PREF_NURSE_FIRST_NAME));
            result.setLastName(stringFromPref(prefs, PREF_NURSE_LAST_NAME));
            result.setDepartment(stringFromPref(prefs, PREF_NURSE_DEPARTMENT));
            result.setPassword(stringFromPref(prefs, PREF_NURSE_PASSWORD));
        } catch (Exception e) { return false; }
        // returns false if the prefs does not have proper data, or partial data
        return true;
    }

    // Saves a nurse to prefs, pass null to erase nurse from prefs
    public static void saveNurse(SharedPreferences prefs, Nurse nurse) {
        SharedPreferences.Editor edit = prefs.edit();
        if (nurse == null) {
            // if null, clear the nurse
            edit.clear();
        } else {
            // otherwise, save nurse to prefs
            edit.putInt(PREF_NURSE_ID, nurse.getNurseID());
            edit.putString(PREF_NURSE_FIRST_NAME, nurse.getFirstName());
            edit.putString(PREF_NURSE_LAST_NAME, nurse.getLastName());
            edit.putString(PREF_NURSE_DEPARTMENT, nurse.getDepartment());
            edit.putString(PREF_NURSE_PASSWORD, nurse.getPassword());
        }
        edit.apply();
    }

    // Gets an int from prefs, throws an error if it doesn't exist
    private static int intFromPref(SharedPreferences p, String n) throws Exception {
        int i = p.getInt(n, Integer.MIN_VALUE);
        if (i == Integer.MIN_VALUE) throw new Exception();
        return i;
    }

    // Gets a String from prefs, throws an error if it doesn't exist
    private static String stringFromPref(SharedPreferences p, String n) throws Exception {
        String value = p.getString(n, null);
        if (value == null) throw new Exception();
        return value;
    }
}
