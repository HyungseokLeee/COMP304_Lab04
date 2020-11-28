
package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//        nurseId
//        firstname
//        lastname
//        department
//        password
@Entity
public class Nurse {

    private static final String
        PREF_NURSE_ID = "nurse_username",
        PREF_NURSE_FIRST_NAME = "nurse_first_name",
        PREF_NURSE_LAST_NAME = "nurse_last_name",
        PREF_NURSE_DEPARTMENT = "nurse_department",
        PREF_NURSE_PASSWORD = "nurse_password";


    // May be better to have nurseID as a String, because it is also a username
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nurseID")
    private int nurseID;
    @ColumnInfo(name = "firstName")
    private String firstName;
    @ColumnInfo(name = "lastName")
    private String lastName;
    @ColumnInfo(name = "department")
    private String department;
    @ColumnInfo(name = "password")
    private String password;
    public Nurse(){}
    public Nurse(int nurseID, String firstName, String lastName, String department, String password)
    {
        this.nurseID = nurseID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public int getNurseID() {
        return nurseID;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public static Nurse fromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(LoginActivity.LOGIN_PREFS, 0);
        return fromPrefs(prefs);
    }

    public static Nurse fromPrefs(SharedPreferences prefs) {
        Nurse nurse = new Nurse();
        try {
            nurse.setNurseID(Integer.parseInt(stringFromPref(prefs, PREF_NURSE_ID)));
            nurse.setFirstName(stringFromPref(prefs, PREF_NURSE_FIRST_NAME));
            nurse.setLastName(stringFromPref(prefs, PREF_NURSE_LAST_NAME));
            nurse.setDepartment(stringFromPref(prefs, PREF_NURSE_DEPARTMENT));
            nurse.setPassword(stringFromPref(prefs, PREF_NURSE_PASSWORD));
        } catch (Exception e) { return null; }
        return nurse;
    }

    public static void saveToPrefs(SharedPreferences prefs, Nurse nurse) {
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
