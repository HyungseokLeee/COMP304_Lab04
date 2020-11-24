package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Patient_Test",
        primaryKeys = {"patientId", "testId"},
        foreignKeys = {
                @ForeignKey(
                    entity = Patient.class,
                    parentColumns ="patientId",
                    childColumns = "ptId"),
                @ForeignKey(
                        entity = Test.class,
                        parentColumns = "testId",
                        childColumns = "tId")
        })
public class PatientTest {
    private int ptId;
    @NonNull
    private int tId;

    public PatientTest(int patientId, int testId)
    {
        this.ptId = patientId;
        this.tId = testId;
    }

    public int getPatientId() {
        return ptId;
    }
    public int getTestId() {
        return tId;
    }
}
