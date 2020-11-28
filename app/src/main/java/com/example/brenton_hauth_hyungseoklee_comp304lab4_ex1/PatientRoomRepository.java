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
    private PatientTestDao mPatientTestDao;

    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Test>> mAllTests;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Patient>> mPatientsForTest;

    public PatientRoomRepository(Application application) {
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

    LiveData<List<Patient>> getAllPatients() { return mAllPatients; }
    LiveData<List<Test>> getAllTests() { return mAllTests; }
    LiveData<List<Nurse>> getAllNurses() { return mAllNurses; }

    LiveData<List<Patient>> getPatientsForTest(int tId)
    {
        mPatientsForTest = mPatientTestDao.getPatientsForTest(tId);
        return mPatientsForTest;
    }

    LiveData<List<Test>> getTestsForPatient(int patientId) {
        // mPatientTestDao.getTestsForPatient(patientId);
        return mTestDao.getTestsForPatient(patientId);
    }

    LiveData<List<Patient>> getPatientsForNurse(int nurseId) {
        return mPatientDao.getPatientsForNurse(nurseId);
    }

    LiveData<List<Nurse>> getNurseByLoginInfo(int nurseId, String password) {
        LiveData<List<Nurse>> nurses = mNurseDao.getNurseByLoginInfo(nurseId, password);
        List<Nurse> list = nurses.getValue();

        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            LiveData<List<Nurse>> data = mNurseDao.getAllNurse();
            int s = 0;
            try {
                s = data.getValue().size();
            } catch (Exception e) {
                s = -1;
            }

            Log.d("THREAD->EXECUTE", Integer.toString(s));
        });

        if (list != null) {
            Log.d("PATIENT_ROOM_REPOSITORY", "List size: " + list.size());
        } else {
            Log.d("PATIENT_ROOM_REPOSITORY", "List is NULL!!!");
        }
        return nurses;
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
/*
            LiveData<List<Nurse>> data = mNurseDao.getNurseByLoginInfo(
                    nurse.getNurseID(), nurse.getPassword());
            List<Nurse> list = data.getValue();
            Log.d("execute(:lambda:)", "" + (list != null ? list.size() : -1));
 */
