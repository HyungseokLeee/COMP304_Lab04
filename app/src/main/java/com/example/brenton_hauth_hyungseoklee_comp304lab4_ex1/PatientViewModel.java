package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.PatientRoomRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    private PatientRoomRepository mRepository;
    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Nurse>> mAllNurses;
    private LiveData<List<Test>> mAllTests;


    public PatientViewModel(Application app) {
        super(app);
        mRepository = new PatientRoomRepository(app);
        mAllPatients = mRepository.getAllPatients();
        mAllNurses = mRepository.getAllNurses();
        mAllTests = mRepository.getAllTests();
    }

    public LiveData<List<Patient>> getPatientsForNurse(int nurseId) {
        return mRepository.getPatientsForNurse(nurseId);
    }

    public LiveData<List<Test>> getTestsForPatient(int patientId) {
        return mRepository.getTestsForPatient(patientId);
    }

    // may change logic, what do you think?
    public LiveData<List<Nurse>> getNurseByLoginInfo(int nurseId, String password) {
        //List<Nurse> nurses = liveData.getValue();
        //return nurses != null ? nurses.get(0) : null;
        return mRepository.getNurseByLoginInfo(nurseId, password);
    }

    public LiveData<List<Patient>> getAllPatients() { return mAllPatients; }
    public LiveData<List<Nurse>> getAllNurses() { return mAllNurses; }
    public LiveData<List<Test>> getAllTests() { return mAllTests; }

    public void insert(Patient patient) { mRepository.insertPatient(patient); }
    public void update(Patient patient) {mRepository.updatePatient(patient);}
    public void delete(Patient patient) {mRepository.deletePatient(patient);}
    public void insert(Nurse nurse) { mRepository.insertNurse(nurse); }
    public void insert(Test test) { mRepository.insertTest(test); }

    // public interface GetItemCallback<T> { void onItem(T item); }
}
