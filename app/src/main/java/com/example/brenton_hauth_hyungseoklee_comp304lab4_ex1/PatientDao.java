package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("select * from Patient order by firstname and lastname")
    LiveData<List<Patient>> getAllPatients();

    @Query("select * from Patient where nurseId = :nId " +
            "order by firstname and lastname")
    LiveData<List<Patient>> getPatientsForNurse(int nId);

    @Insert
    void insert(Patient... patients);
    @Update
    void update(Patient... patients);
    @Delete
    void delete(Patient... patients);
}
