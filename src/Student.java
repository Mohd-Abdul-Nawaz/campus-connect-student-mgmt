package com.campusconnect.model;

/**
 * Student - Domain model representing a student record.
 * Demonstrates Object-Oriented Programming principles: encapsulation, fields, constructors.
 */
public class Student {
    private int id;
    private String name;
    private String rollNumber;
    private String department;
    private String email;
    private double gpa;

    public Student() {}

    public Student(int id, String name, String rollNumber, String department, String email, double gpa) {
        this.id = id;
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.email = email;
        this.gpa = gpa;
    }

    public Student(String name, String rollNumber, String department, String email, double gpa) {
        this(0, name, rollNumber, department, email, gpa);
    }

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Roll: %s | Dept: %s | Email: %s | GPA: %.2f",
                id, name, rollNumber, department, email, gpa);
    }
}