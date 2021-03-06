package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;


public class PatientRoomRepository {
    private PatientDao mPatientDao;
    private TestDao mTestDao;
    private NurseDao mNurseDao;

    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Test>> mAllTests;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Patient>> mPatientsForTest;

    public PatientRoomRepository(Application application) {
        PatientRoomDatabase db =PatientRoomDatabase.getDatabase(application);
        mPatientDao = db.PatientDao();
        mTestDao = db.TestDao();
        mNurseDao = db.NurseDao();

        mAllPatients = mPatientDao.getAllPatients();
        mAllTests = mTestDao.getAllTest();
        mAllTests = mTestDao.getAllTest();
        mAllNurses = mNurseDao.getAllNurse();
    }

    LiveData<List<Patient>> getAllPatients() { return mAllPatients; }
    LiveData<List<Test>> getAllTests() { return mAllTests; }
    LiveData<List<Nurse>> getAllNurses() { return mAllNurses; }

    LiveData<List<Test>> getTestsForPatient(int patientId) {
        // mPatientTestDao.getTestsForPatient(patientId);
        return mTestDao.getTestsForPatient(patientId);
    }

    LiveData<List<Patient>> getPatientsForNurse(int nurseId) {
        return mPatientDao.getPatientsForNurse(nurseId);
    }

    LiveData<List<Nurse>> getNurseByLoginInfo(int nurseId, String password) {
        return mNurseDao.getNurseByLoginInfo(nurseId, password);
    }

    void insertPatient(Patient patient)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPatientDao.insert(patient);
            mPatientDao.update(patient);
        });
    }
    void updatePatient(Patient patient)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPatientDao.update(patient);
        });
    }
    void deletePatient(Patient patient)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPatientDao.delete(patient);
        });
    }
    void insertTest(Test test)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTestDao.insert(test);
        });
    }
    void insertNurse(Nurse nurse)
    {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNurseDao.insert(nurse);
        });
    }
}
