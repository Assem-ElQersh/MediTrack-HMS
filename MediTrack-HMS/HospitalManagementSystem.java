import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;

    public HospitalManagementSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added successfully.");
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients to display.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient.toString());
            }
        }
    }

    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors to display.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor.toString());
            }
        }
    }

    public void updatePatient(int patientId, String newName, int newAge, String newCondition) {
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                patient.setName(newName);
                patient.setAge(newAge);
                patient.setCondition(newCondition);
                System.out.println("Patient updated successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    public void updateDoctor(int doctorId, String newName, int newAge, String newSpecialization) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                doctor.setName(newName);
                doctor.setAge(newAge);
                doctor.setSpecialization(newSpecialization);
                System.out.println("Doctor updated successfully.");
                return;
            }
        }
        System.out.println("Doctor not found.");
    }

    public void deletePatient(int patientId) {
        Patient patientToRemove = null;
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                patientToRemove = patient;
                break;
            }
        }
        if (patientToRemove != null) {
            patients.remove(patientToRemove);
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void deleteDoctor(int doctorId) {
        Doctor doctorToRemove = null;
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                doctorToRemove = doctor;
                break;
            }
        }
        if (doctorToRemove != null) {
            doctors.remove(doctorToRemove);
            System.out.println("Doctor deleted successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }
}