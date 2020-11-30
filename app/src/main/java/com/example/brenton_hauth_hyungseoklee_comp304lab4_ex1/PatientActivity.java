package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.PrefsHelper;
import com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1.helpers.ValidationHelper;

import java.util.List;


public class PatientActivity
        extends AppCompatActivity
        implements Observer<List<Patient>> {

    private PatientViewModel patientViewModel;

    private RecyclerView recyclerView;
    private EditText patientIdEditText,
        patientRoomEditText, patientFirstNameEditText,
        patientLastNameEditText, patientDepEditText;

    private Nurse nurse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        // Get patientViewModel
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        initRecycler(); //

        nurse = new Nurse(); // inits nurse
        // Gets login prefs
        SharedPreferences loginPrefs = PrefsHelper.getLoginPrefs(this);

        // Checks if nurse is stored in login prefs
        if (PrefsHelper.hasSavedNurse(loginPrefs, nurse)) {
            initUI();

            // Gets patients associated with 'nurse'
            LiveData<List<Patient>> patients =
                    patientViewModel.getPatientsForNurse(nurse.getNurseID());

            patients.observe(this, this); // Attaches observer
        } else {
            // Displays message just in case
            Toast.makeText(this,
                "You are not logged in!",
                Toast.LENGTH_SHORT).show();
        }
/*        patientViewModel.insert(new Patient(107983021, "Jaakkima", "Romilly", nurse.getDepartment(), nurse.getNurseID(), "D-14"));
        patientViewModel.insert(new Patient(107983027, "September", "Sweet", nurse.getDepartment(), nurse.getNurseID(), "A-19"));
        patientViewModel.insert(new Patient(107983034, "Leanne", "Norman", nurse.getDepartment(), nurse.getNurseID(), "H-23"));
        patientViewModel.insert(new Patient(107983039, "Bram", "Strong", nurse.getDepartment(), nurse.getNurseID(), "F-13"));
        patientViewModel.insert(new Patient(107983044, "Nikki", "Hamilton", nurse.getDepartment(), nurse.getNurseID(), "J-28"));*/
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.patientRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    private void initUI() {
        // sets header for Nurse
        setTitle(String.format("Patients for %s", nurse.getNurseID()));

        patientIdEditText = findViewById(R.id.patientIdEditText);
        patientRoomEditText = findViewById(R.id.patientRoomEditText);
        patientFirstNameEditText = findViewById(R.id.patientFirstNameEditText);
        patientLastNameEditText = findViewById(R.id.patientLastNameEditText);
        patientDepEditText = findViewById(R.id.patientDepEditText);

        // Makes department field unique
        patientDepEditText.setHint(String.format("Department (%s)", nurse.getDepartment()));
    }

    @Override
    public void onChanged(List<Patient> patients) {
        // Updates the recycler with the list of patients
        PatientAdapter adapter = new PatientAdapter(patients);
        recyclerView.setAdapter(adapter);
    }

    public void onAddPatientButtonClick(View v) {

        Patient p = new Patient(); // creates empty patient

        boolean results = true; // result defaults to true

        // Checks & sets patient id, gives a randomId as the default
        if (!ValidationHelper.validateId(patientIdEditText, randomId(), p::setPatientId)) {
            results = false;
        }

        // Checks & sets room number
        if (!ValidationHelper.validateRoom(patientRoomEditText, p::setRoom)) {
            results = false;
        }

        // Checks & sets department
        if (!ValidationHelper.validate(patientDepEditText,
                ".+", nurse.getDepartment(), p::setDepartment)) {
            results = false;
        }

        // Checks & sets first name
        if (!ValidationHelper.validateName(patientFirstNameEditText, p::setFirstName)) {
            results = false;
        }

        // Checks & sets last name
        if (!ValidationHelper.validateName(patientLastNameEditText, p::setLastName)) {
            results = false;
        }

        if (results) { // if all the fields are valid
            p.setNurseId(nurse.getNurseID()); // sets nurse ID
            try {
                patientViewModel.insert(p);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            clearFields();
            Toast.makeText(this,
                String.format("Added %s (%s)!", p.getFullName(), p.getPatientId()),
                Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        patientFirstNameEditText.setText("");
        patientLastNameEditText.setText("");
        patientDepEditText.setText("");
        patientIdEditText.setText("");
        patientRoomEditText.setText("");
    }

    private static int randomId() {
        // Last 2 digits of nanoTime are 0 so we remove them
        // plus 1 more spot for safety. After an int cast,
        // the value may still be negative.
        // TODO: Remove very very small chance of duplicates
        return Math.abs((int)(System.nanoTime() >> 3));
    }
}