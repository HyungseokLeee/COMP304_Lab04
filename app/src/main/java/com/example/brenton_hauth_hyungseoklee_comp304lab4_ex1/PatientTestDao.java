package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientTestDao {
    @Insert
    void insert(PatientTest patientTest);

    @Query("SELECT * FROM Patient INNER JOIN Patient_Test ON " +
            "patient.patientId = Patient_Test.tId WHERE " +
            "Patient_Test.tId=:tId")
    LiveData<List<Patient>> getPatientForTest(int tId);

    @Query("SELECT * FROM Test INNER JOIN Patient_Test ON " +
            "test.testID = Patient_Test.tId WHERE " +
            "Patient_Test.ptId =:ptId")
    LiveData<List<Test>>getTestForPatient(int ptId);
}