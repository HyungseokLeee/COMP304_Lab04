package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

//@Entity
public class Patient {
    //@PrimaryKey(autoGenerate = true)
    private int patientId;
    private int room;

    private String firstName;
    private String lastName;
    private String department;
    private String nurseId;


    public Patient() {}

    public Patient(int room, String first, String last,
            String department, String nurseId) {
        this.room = room;
        this.firstName = first;
        this.lastName = last;
        this.department = department;
        this.nurseId = nurseId;
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

    public String getNurseId() {
        return nurseId;
    }
    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int id) {
        this.patientId = id;
    }
}
