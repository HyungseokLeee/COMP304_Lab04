package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
public class PatientRoomRepository {
    private PatientDao mPatientDao;
    private TestDao mTestDao;
    private NurseDao mNurseDao;
    private PatientTestDao mPatientTestDao;

    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Test>> mAllTests;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Patient>> mPatientsForTest;

    PatientRoomRepository(Application application)
    {
        PatientRoomDatabase db =PatientRoomDatabase.getDatabase(application);
        mPatientDao = db.PatientDao();
        mTestDao = db.TestDao();
        mNurseDao = db.NurseDao();
        mPatientTestDao = db.PatientTestDao();

        mAllPatients = mPatientDao.getAllPatients();
        mAllTests = mTestDao.getAllTest();
        mAllTests = mTestDao.getAllTest();
        mAllNurses = mNurseDao.getAllNurse();
    }
    LiveData<List<Patient>> getmAllPatients() {return mAllPatients;}
    LiveData<List<Test>> getmAllTests() {return mAllTests;}
    LiveData<List<Nurse>> getmAllNurses() {return mAllNurses;}

    LiveData<List<Patient>> getmPatientsForTest(int tId)
    {
        mPatientsForTest = mPatientTestDao.getPatientForTest(tId);
        return mPatientsForTest;
    }

    void insertPatient(Patient patient)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(()->{
            mPatientDao.insert(patient);
        });
    }
    void insertTest(Test test)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(()->{
            mTestDao.insert(test);
        });
    }
    void insertNurse(Nurse nurse)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(()->{
            mNurseDao.insert(nurse);
        });
    }
}
