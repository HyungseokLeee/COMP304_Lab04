package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

// import androidx.room.Entity;
// import androidx.room.PrimaryKey;

//@Entity
public class Nurse {

    //@PrimaryKey
    private String nurseId; // is also username, so must be a String
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public Nurse() {}

    public Nurse(String id, String first, String last, String dep, String pwd) {
        nurseId = id;
        firstName = first;
        lastName = last;
        department = dep;
        password = pwd;
    }

    public String getNurseId() {
        return nurseId;
    }
    public void setNurseId(String id) {
        this.nurseId = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String last) {
        this.lastName = last;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String dep) {
        this.department = dep;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
