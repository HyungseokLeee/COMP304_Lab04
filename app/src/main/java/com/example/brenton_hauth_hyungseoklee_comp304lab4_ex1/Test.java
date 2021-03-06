
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
    public static final String TEST_ID_EXTRA = "test_id";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "testId")
    private int testId;
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
    public Test(int patientId, int nurseID, int BPL, int BPH, float temperature) {
        this.patientID = patientId;
        this.nurseID = nurseID;
        this.BPL = BPL;
        this.BPH = BPH;
        this.temperature = temperature;
    }

    public int getTestId() { return testId;}

    public void setTestId(int testId) { this.testId = testId;}

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

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
