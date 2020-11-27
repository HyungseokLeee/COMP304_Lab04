package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "Patient_Test",
        primaryKeys = {"ptId", "tstId"},
        foreignKeys = {
                @ForeignKey(
                    entity = Patient.class,
                    parentColumns ="patientId",
                    childColumns = "ptId"),
                @ForeignKey(
                        entity = Test.class,
                        parentColumns = "testId",
                        childColumns = "tstId")
        })

public class PatientTest {
    @NonNull
    private int ptId;
    @NonNull
    private int tstId;

    public PatientTest(int ptId, int tstId)
    {
        this.ptId = ptId;
        this.tstId = tstId;
    }

    public int getPtId() {
        return ptId;
    }

    public int getTstId() {
        return tstId;
    }
}
