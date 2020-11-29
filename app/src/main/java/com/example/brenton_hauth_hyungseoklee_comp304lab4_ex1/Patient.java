
package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {
    public static final String
        PATIENT_ID_EXTRA = "patient_id",
        PATIENT_NAME_EXTRA = "patient_name";

    @PrimaryKey
    @ColumnInfo(name = "patientId")
    private int patientId;
    @ColumnInfo(name = "firstname")
    private String firstName;
    @ColumnInfo(name = "lastname")
    private String lastName;
    @ColumnInfo(name = "department")
    private String department;
    @ColumnInfo(name = "nurseId")
    private int nurseId;
    @ColumnInfo(name = "room")
    private String room;
    //
    public Patient(){}
    public Patient(int patientId, String firstName, String lastname, String department, int nurseId,String room)
    {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastname;
        this.department = department;
        this.nurseId = nurseId;
        this.room = room;
    }
    //
    public String getFullName() { return firstName + " " + lastName; }
    public int getPatientId(){return patientId;}
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getDepartment(){return department;}
    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
