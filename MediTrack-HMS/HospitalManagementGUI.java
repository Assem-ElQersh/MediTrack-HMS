import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class HospitalManagementGUI extends JFrame {
    private HospitalManagementSystem hospitalSystem;
    private JButton addButton;
    private JButton displayButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JTextArea outputTextArea;

    public HospitalManagementGUI() {
        this.hospitalSystem = new HospitalManagementSystem();
        initializeComponents();
        createLayout();
        createListeners();
        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        addButton = new JButton("Add");
        displayButton = new JButton("Display");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        exitButton = new JButton("Exit");
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
    }

    private void createLayout() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenu();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMenu();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMenu();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMenu();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addMenu() {
        String[] options = { "Patient", "Doctor" };
        int choice = JOptionPane.showOptionDialog(this, "Choose type to add:", "Add Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            addPatientDialog();
        } else if (choice == 1) {
            addDoctorDialog();
        }
    }

    private void addPatientDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField conditionField = new JTextField();
        Object[] fields = { "ID:", idField, "Name:", nameField, "Age:", ageField, "Condition:", conditionField };
        int result = JOptionPane.showConfirmDialog(this, fields, "Add Patient", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String condition = conditionField.getText();
                Patient newPatient = new Patient(id, name, age, condition);
                hospitalSystem.addPatient(newPatient);
                updateOutput("Patient added successfully.\n");
            } catch (NumberFormatException e) {
                updateOutput("Invalid input. Please enter numeric values for ID and Age.\n");
            }
        }
    }

    private void addDoctorDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField specializationField = new JTextField();
        Object[] fields = { "ID:", idField, "Name:", nameField, "Age:", ageField, "Specialization:",
                specializationField };
        int result = JOptionPane.showConfirmDialog(this, fields, "Add Doctor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String specialization = specializationField.getText();
                Doctor newDoctor = new Doctor(id, name, age, specialization);
                hospitalSystem.addDoctor(newDoctor);
                updateOutput("Doctor added successfully.\n");
            } catch (NumberFormatException e) {
                updateOutput("Invalid input. Please enter numeric values for ID and Age.\n");
            }
        }
    }

    private void displayMenu() {
        String[] options = { "Patients", "Doctors" };
        int choice = JOptionPane.showOptionDialog(this, "Choose type to display:", "Display Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            displayPatientsDialog();
        } else if (choice == 1) {
            displayDoctorsDialog();
        }
    }

    private void displayPatientsDialog() {
        StringBuilder patientsInfo = new StringBuilder();
        for (Patient patient : hospitalSystem.getPatients()) {
            patientsInfo.append(patient.toString()).append("\n");
        }
        updateOutput(patientsInfo.toString());
    }

    private void displayDoctorsDialog() {
        StringBuilder doctorsInfo = new StringBuilder();
        for (Doctor doctor : hospitalSystem.getDoctors()) {
            doctorsInfo.append(doctor.toString()).append("\n");
        }
        updateOutput(doctorsInfo.toString());
    }

    private void updateMenu() {
        String[] options = { "Patient", "Doctor" };
        int choice = JOptionPane.showOptionDialog(this, "Choose type to update:", "Update Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            updatePatientDialog();
        } else if (choice == 1) {
            updateDoctorDialog();
        }
    }

    private void updatePatientDialog() {
        try {
            int patientId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter patient ID to update:"));
            String newName = JOptionPane.showInputDialog(this, "Enter new name for patient:");
            int newAge = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new age for patient:"));
            String newCondition = JOptionPane.showInputDialog(this, "Enter new condition for patient:");
            hospitalSystem.updatePatient(patientId, newName, newAge, newCondition);
            updateOutput("Patient updated successfully.\n");
        } catch (NumberFormatException e) {
            updateOutput("Invalid input. Please enter a numeric value for patient ID.\n");
        }
    }

    private void updateDoctorDialog() {
        try {
            int doctorId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter doctor ID to update:"));
            String newName = JOptionPane.showInputDialog(this, "Enter new name for doctor:");
            int newAge = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new age for doctor:"));
            String newSpecialization = JOptionPane.showInputDialog(this, "Enter new specialization for doctor:");
            hospitalSystem.updateDoctor(doctorId, newName, newAge, newSpecialization);
            updateOutput("Doctor updated successfully.\n");
        } catch (NumberFormatException e) {
            updateOutput("Invalid input. Please enter a numeric value for doctor ID.\n");
        }
    }

    private void deleteMenu() {
        String[] options = { "Patient", "Doctor" };
        int choice = JOptionPane.showOptionDialog(this, "Choose type to delete:", "Delete Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            deletePatientDialog();
        } else if (choice == 1) {
            deleteDoctorDialog();
        }
    }

    private void deletePatientDialog() {
        try {
            int patientId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter patient ID to delete:"));
            hospitalSystem.deletePatient(patientId);
            updateOutput("Patient deleted successfully.\n");
        } catch (NumberFormatException e) {
            updateOutput("Invalid input. Please enter a numeric value for patient ID.\n");
        }
    }

    private void deleteDoctorDialog() {
        try {
            int doctorId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter doctor ID to delete:"));
            hospitalSystem.deleteDoctor(doctorId);
            updateOutput("Doctor deleted successfully.\n");
        } catch (NumberFormatException e) {
            updateOutput("Invalid input. Please enter a numeric value for doctor ID.\n");
        }
    }

    private void updateOutput(String message) {
        outputTextArea.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HospitalManagementGUI();
            }
        });
    }
}