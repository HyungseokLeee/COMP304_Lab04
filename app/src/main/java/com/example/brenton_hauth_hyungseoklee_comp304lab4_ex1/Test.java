package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//testId
//        patientId
//        nurseId
//        BPL
//        BPH
//        temperature
@Entity
public class Test {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "patientID")
    private int patientID;
    @ColumnInfo(name = "nurseID")
    private int nurseID;
    @ColumnInfo(name = "BPL")
    private int BPL;
    @ColumnInfo(name = "BPH")
    private int BPH;
    @ColumnInfo(name = "temperature")
    private float temperature;
    public Test(){}
    public Test(int patientID, int nurseID, int BPL, int BPH, float temperature)
    {
        this.patientID = patientID;
        this.nurseID = nurseID;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public int getNurseID() {
        return nurseID;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPH(int BPH) {
        this.BPH = BPH;
    }

    public int getBPH() {
        return BPH;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }
}
