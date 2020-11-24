package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Patient.class,Test.class,PatientTest.class,Nurse.class} , version = 1, exportSchema = false)
public abstract class PatientRoomDatabase extends RoomDatabase {
    public abstract PatientDao PatientDao();
    public abstract NurseDao NurseDao();
    public abstract TestDao TestDao();
    public abstract PatientTestDao PatientTestDao();

    private static volatile PatientRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PatientRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (PatientRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PatientRoomDatabase.class,"patient_test_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
