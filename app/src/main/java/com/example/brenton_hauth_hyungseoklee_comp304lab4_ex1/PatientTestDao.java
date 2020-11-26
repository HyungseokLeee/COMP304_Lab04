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
            "patient.patientId = Patient_Test.tstId WHERE " +
            "Patient_Test.tstId=:tId")
    LiveData<List<Patient>> getPatientsForTest(int tId); // Not sure if we still need this

    @Query("SELECT * FROM Test INNER JOIN Patient_Test ON " +
            "test.testID = Patient_Test.tstId WHERE " +
            "Patient_Test.ptId =:ptId")
    LiveData<List<Test>> getTestsForPatient(int ptId);
}
