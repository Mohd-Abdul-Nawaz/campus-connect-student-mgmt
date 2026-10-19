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
│   └── com/campusconnect/
│       ├── model/Student.java      # Domain model (encapsulation)
│       ├── db/Database.java        # JDBC + SQL operations
│       └── ui/CampusApp.java       # Swing GUI (main entry)
├── lib/sqlite-jdbc.jar             # SQLite JDBC driver
├── build.bat                       # Windows build script
├── build.sh                        # Linux/Mac build script
├── pom.xml                         # Maven build (optional)
├── campus.db                       # Auto-created SQLite database
└── README.md
```

---

## 🚀 Quick Start (Local)

### Option A — Build Scripts (easiest)

**Windows:**
```bash
build.bat
```

**Linux / Mac:**
```bash
chmod +x build.sh
./build.sh
```

Both scripts compile the sources and launch the GUI with the SQLite driver on the classpath.

### Option B — Manual Compile & Run

**1. Compile**
```bash
javac -cp "lib/sqlite-jdbc.jar" -d out \
  src/com/campusconnect/model/Student.java \
  src/com/campusconnect/db/Database.java \
  src/com/campusconnect/ui/CampusApp.java
```

**2. Run**
```bash
# Windows
java -cp "out;lib/sqlite-jdbc.jar" com.campusconnect.ui.CampusApp

# Linux / Mac
java -cp "out:lib/sqlite-jdbc.jar" com.campusconnect.ui.CampusApp
```

### Option C — Maven

```bash
mvn clean package
java -cp "target/classes;lib/sqlite-jdbc.jar" com.campusconnect.ui.CampusApp
```

> **Note:** `campus.db` is created automatically in the working directory on first run.
> The SQLite JDBC driver is bundled in `lib/sqlite-jdbc.jar` — no extra setup needed.

### Git
```bash
git init
git add .
git commit -m "Initial commit: CampusConnect student management system"
git remote add origin https://github.com/Mohd-Abdul-Nawaz/campus-connect-student-mgmt.git
git push -u origin main
```

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