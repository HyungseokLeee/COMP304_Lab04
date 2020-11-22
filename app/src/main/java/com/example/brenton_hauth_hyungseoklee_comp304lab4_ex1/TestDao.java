package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TestDao {
    @Insert
    void insert(Test test);
    @Query("select * from test order by patientID and nurseID")
    LiveData<List<Test>> getAllTest();
}
