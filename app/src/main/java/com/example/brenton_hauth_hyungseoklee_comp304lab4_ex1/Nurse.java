package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
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
}
