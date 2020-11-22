package com.example.brenton_hauth_hyungseoklee_comp304lab4_ex1;

//@Entity
public class Test {
    private int testId;
    private int patientId;
    private String nurseId;
    private int BPL;
    private int BPH;
    private int temperature;

    public Test() {}

    public Test(int patientId, String nurseId, int bpl, int bph, int temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        BPL = bpl;
        BPH = bph;
        this.temperature = temperature;
    }

    public int getTestId() {
        return testId;
    }
    public void setTestId(int id) {
        testId = id;
    }

    public String getNurseId() {
        return nurseId;
    }
    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getBPL() {
        return BPL;
    }
    public void setBPL(int bpl) {
        BPL = bpl;
    }

    public int getBPH() {
        return BPH;
    }
    public void setBPH(int bph) {
        BPH = bph;
    }

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temp) {
        temperature = temp;
    }
}
