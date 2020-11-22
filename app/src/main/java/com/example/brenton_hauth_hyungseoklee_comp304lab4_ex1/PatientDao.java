package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);
    @Query("select * from Patient order by firstname and lastname")
    LiveData<List<Patient>> getAllPatients();
}
