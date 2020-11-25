package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TestDao {
    @Insert
    void insert(Test test);
    @Query("select * from test order by patientID and nurseID")
    LiveData<List<Test>> getAllTest();

    // In case Tests-to-Patients is many-to-1
    @Query("select * from test where patientID = :pId " +
            "order by patientID")
    LiveData<List<Test>> getTestsForPatient(int pId);

//    @Update void update(Test... tests);
//    @Delete void delete(Test... tests);
}
