package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NurseDao {
    @Insert
    void insert(Nurse nurse);
    @Query("SELECT * FROM Nurse;")
    LiveData<List<Nurse>> getAllNurse();

    @Query("SELECT * FROM Nurse WHERE nurseID = :nId AND password = :pwd;")
    LiveData<List<Nurse>> getNurseByLoginInfo(int nId, String pwd);

    @Query("SELECT * FROM Nurse WHERE nurseID = :nId;")
    LiveData<List<Nurse>> getNurseById(int nId);
}
