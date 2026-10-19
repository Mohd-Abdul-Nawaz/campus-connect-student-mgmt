package com.campusconnect.db;

import com.campusconnect.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database - Handles all SQLite JDBC operations for CampusConnect.
 * Demonstrates JDBC, SQL, and Data Access Object (DAO) pattern.
 */
public class Database {
    private static final String DRIVER_CLASS = "org.sqlite.JDBC";
    private final String url = "jdbc:sqlite:campus.db";

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found on classpath. "
                    + "Add sqlite-jdbc.jar to the classpath. Cause: " + e.getMessage());
        }
    }

    public Database() {
        initializeTables();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void initializeTables() {
        String sql = """
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                roll_number TEXT NOT NULL UNIQUE,
                department TEXT NOT NULL,
                email TEXT NOT NULL,
                gpa REAL NOT NULL
            );
        """;
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Table init error: " + e.getMessage());
        }
    }

    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students(name, roll_number, department, email, gpa) VALUES(?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getName());
            pstmt.setString(2, s.getRollNumber());
            pstmt.setString(3, s.getDepartment());
            pstmt.setString(4, s.getEmail());
            pstmt.setDouble(5, s.getGpa());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Add error: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("roll_number"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getDouble("gpa")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Fetch error: " + e.getMessage());
        }
        return list;
    }

    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, roll_number=?, department=?, email=?, gpa=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getName());
            pstmt.setString(2, s.getRollNumber());
            pstmt.setString(3, s.getDepartment());
            pstmt.setString(4, s.getEmail());
            pstmt.setDouble(5, s.getGpa());
            pstmt.setInt(6, s.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
            return false;
        }
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getDouble("gpa")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Find error: " + e.getMessage());
        }
        return null;
    }
}