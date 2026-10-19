package com.campusconnect.ui;

import com.campusconnect.db.Database;
import com.campusconnect.model.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CampusApp extends JFrame {
    private final Database db;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JTextField txtName, txtRoll, txtDept, txtEmail, txtGpa;
    private int selectedId = -1;

    public CampusApp() {
        db = new Database();
        setTitle("CampusConnect — Student Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] cols = {"ID", "Name", "Roll Number", "Department", "Email", "GPA"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> onRowSelected());

        txtName = new JTextField(15);
        txtRoll = new JTextField(12);
        txtDept = new JTextField(15);
        txtEmail = new JTextField(20);
        txtGpa = new JTextField(5);

        buildUI();
        loadStudents();
    }

    private void buildUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("CampusConnect — Student Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 6, 8, 8));
        form.add(makeLabeledField("Name", txtName));
        form.add(makeLabeledField("Roll Number", txtRoll));
        form.add(makeLabeledField("Department", txtDept));
        form.add(makeLabeledField("Email", txtEmail));
        form.add(makeLabeledField("GPA", txtGpa));

        JButton btnSave = new JButton("Save Student");
        JButton btnClear = new JButton("Clear");
        btnSave.addActionListener(e -> saveStudent());
        btnClear.addActionListener(e -> clearForm());
        form.add(btnSave);
        form.add(btnClear);
        panel.add(form, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnUpdate = new JButton("Update Selected");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnRefresh = new JButton("Refresh");
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnRefresh.addActionListener(e -> loadStudents());
        bottom.add(btnUpdate);
        bottom.add(btnDelete);
        bottom.add(btnRefresh);
        panel.add(bottom, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    private JPanel makeLabeledField(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout(4, 4));
        p.add(new JLabel(label), BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        List<Student> students = db.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                s.getId(), s.getName(), s.getRollNumber(),
                s.getDepartment(), s.getEmail(), String.format("%.2f", s.getGpa())
            });
        }
    }

    private void onRowSelected() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        selectedId = (int) tableModel.getValueAt(row, 0);
        txtName.setText((String) tableModel.getValueAt(row, 1));
        txtRoll.setText((String) tableModel.getValueAt(row, 2));
        txtDept.setText((String) tableModel.getValueAt(row, 3));
        txtEmail.setText((String) tableModel.getValueAt(row, 4));
        txtGpa.setText((String) tableModel.getValueAt(row, 5));
    }

    private void saveStudent() {
        try {
            String name = txtName.getText().trim();
            String roll = txtRoll.getText().trim();
            String dept = txtDept.getText().trim();
            String email = txtEmail.getText().trim();
            double gpa = Double.parseDouble(txtGpa.getText().trim());

            if (name.isEmpty() || roll.isEmpty() || dept.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            Student s = new Student(name, roll, dept, email, gpa);
            if (db.addStudent(s)) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearForm();
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student. Roll number may be duplicate.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid GPA value.");
        }
    }

    private void updateStudent() {
        if (selectedId < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.");
            return;
        }
        try {
            Student s = new Student(
                selectedId,
                txtName.getText().trim(),
                txtRoll.getText().trim(),
                txtDept.getText().trim(),
                txtEmail.getText().trim(),
                Double.parseDouble(txtGpa.getText().trim())
            );
            if (db.updateStudent(s)) {
                JOptionPane.showMessageDialog(this, "Student updated!");
                clearForm();
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid GPA value.");
        }
    }

    private void deleteStudent() {
        if (selectedId < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this student record?");
        if (confirm == JOptionPane.YES_OPTION) {
            if (db.deleteStudent(selectedId)) {
                clearForm();
                loadStudents();
            }
        }
    }

    private void clearForm() {
        txtName.setText(""); txtRoll.setText(""); txtDept.setText("");
        txtEmail.setText(""); txtGpa.setText("");
        selectedId = -1;
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CampusApp().setVisible(true));
    }
}