package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.Nurse;

public class PrefsHelper {
    private static final String
        LOGIN_PREFS = "login_prefs",
        PREF_NURSE_ID = "nurse_username",
        PREF_NURSE_FIRST_NAME = "nurse_first_name",
        PREF_NURSE_LAST_NAME = "nurse_last_name",
        PREF_NURSE_DEPARTMENT = "nurse_department",
        PREF_NURSE_PASSWORD = "nurse_password";

    public static Nurse getSavedNurse(Context ctx) {
        return getSavedNurse(getLoginPrefs(ctx));
    }

    public static SharedPreferences getLoginPrefs(Context ctx) {
        return ctx.getSharedPreferences(LOGIN_PREFS, 0);
    }

    public static Nurse getSavedNurse(SharedPreferences prefs) {
        Nurse nurse = new Nurse();
        return hasSavedNurse(prefs, nurse) ? nurse : null;
    }

    public static boolean hasSavedNurse(SharedPreferences prefs) {
        try {
            return Integer.parseInt(stringFromPref(prefs, PREF_NURSE_ID)) != 0;
        } catch (Exception e) { return false; }
    }

    public static boolean hasSavedNurse(SharedPreferences prefs, Nurse result) {
        try {
            result.setNurseID(Integer.parseInt(stringFromPref(prefs, PREF_NURSE_ID)));
            result.setFirstName(stringFromPref(prefs, PREF_NURSE_FIRST_NAME));
            result.setLastName(stringFromPref(prefs, PREF_NURSE_LAST_NAME));
            result.setDepartment(stringFromPref(prefs, PREF_NURSE_DEPARTMENT));
            result.setPassword(stringFromPref(prefs, PREF_NURSE_PASSWORD));
        } catch (Exception e) { return false; }
        return true;
    }

    public static void saveNurse(SharedPreferences prefs, Nurse nurse) {
        SharedPreferences.Editor edit = prefs.edit();
        if (nurse == null) {
            edit.clear();
            edit.apply();
            return;
        }

        edit.putString(PREF_NURSE_ID, Integer.toString(nurse.getNurseID()));
        edit.putString(PREF_NURSE_FIRST_NAME, nurse.getFirstName());
        edit.putString(PREF_NURSE_LAST_NAME, nurse.getLastName());
        edit.putString(PREF_NURSE_DEPARTMENT, nurse.getDepartment());
        edit.putString(PREF_NURSE_PASSWORD, nurse.getPassword());
        edit.apply();
    }

    private static String stringFromPref(SharedPreferences p, String n) throws Exception {
        String value = p.getString(n, null);
        if (value == null) throw new Exception();
        return value;
    }
}
