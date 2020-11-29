package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Patient.class,Test.class,Nurse.class} , version = 2, exportSchema = false)
public abstract class PatientRoomDatabase extends RoomDatabase {
    public abstract PatientDao PatientDao();
    public abstract NurseDao NurseDao();
    public abstract TestDao TestDao();

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
                    Migration fromV1ToV2 = new Migration(1, 2) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) { }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PatientRoomDatabase.class,"patient_test_database")
                            .addMigrations(fromV1ToV2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
