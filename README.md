# 🎓 CampusConnect — Student Management System

A desktop application for managing student records built with **Java, Swing, and SQLite**.  
Created as a showcase project for the **Honeywell Software Engineering Intern** application.

---

## 📸 Features

| Feature | Description |
|---------|-------------|
| ✅ Add Student | Create new student records with validation |
| 📋 View All | List all students in a scrollable table |
| ✏️ Update | Edit existing student information |
| 🗑️ Delete | Remove student records with confirmation |
| 🔍 Search by ID | Retrieve individual student details |
| 💾 SQLite | Local file-based database (zero-config) |

---

## 🛠️ Tech Stack

- **Language:** Java 11+
- **GUI:** Java Swing (AWT/Swing components)
- **Database:** SQLite via JDBC
- **Pattern:** DAO (Data Access Object) for database separation
- **Build:** Compile with `javac`, run with `java` (no Maven required)

---

## 📁 Project Structure

```
campus-connect-student-mgmt/
├── src/
│   ├── com/campusconnect/model/Student.java      # Domain model (encapsulation)
│   ├── com/campusconnect/db/Database.java        # JDBC + SQL operations
│   └── com/campusconnect/ui/CampusApp.java       # Swing GUI (main entry)
├── campus.db                                     # Auto-created SQLite database
└── README.md
```

---

## 🚀 Quick Start (Local)

### 1. Compile
```bash
cd campus-connect-student-mgmt/src
javac com/campusconnect/model/Student.java com/campusconnect/db/Database.java com/campusconnect/ui/CampusApp.java
```

### 2. Run
```bash
cd ..
java -cp src com.campusconnect.ui.CampusApp
```

### 3. SQLite JDBC Driver
The project uses SQLite. If you get a `ClassNotFoundException` for the SQLite JDBC driver, download `sqlite-jdbc-3.x.x.jar` from:
https://github.com/xerial/sqlite-jdbc/releases

Then run with the driver on the classpath:
```bash
# Windows
java -cp "src;sqlite-jdbc.jar" com.campusconnect.ui.CampusApp

# Mac/Linux
java -cp "src:sqlite-jdbc.jar" com.campusconnect.ui.CampusApp
```

> **Note:** For simplicity, the included code uses Java's built-in `javax.sql` with SQLite via the JDBC driver. Add the sqlite-jdbc JAR to your classpath when running.

---

## 🎓 Why This Project for Honeywell

- Demonstrates **Java programming** (one of Honeywell's valued languages)
- Shows **Object-Oriented Design** — encapsulation, classes, domain modeling
- Uses **JDBC + SQL** — database connectivity skills
- **Swing GUI** — event-driven desktop application development
- Clean separation of concerns: Model (Student), DAO (Database), View/Controller (CampusApp)
- Follows **software engineering principles** — modular, readable, maintainable

---

## 📄 License

MIT License.