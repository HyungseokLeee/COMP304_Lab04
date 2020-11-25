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
    @Query("select * from nurse order by firstName and lastName")
    LiveData<List<Nurse>> getAllNurse();

    @Query("select * from nurse where nurseID = :nId and password = :pwd")
    LiveData<List<Nurse>> getNurseByLoginInfo(int nId, String pwd);
}
