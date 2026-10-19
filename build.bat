@echo off
REM ============================================================
REM  CampusConnect - Student Management System (Windows build)
REM ============================================================
setlocal

echo [1/3] Cleaning previous build...
if exist out rmdir /s /q out

echo [2/3] Compiling Java sources...
javac -cp "lib\sqlite-jdbc.jar" -d out ^
  src\com\campusconnect\model\Student.java ^
  src\com\campusconnect\db\Database.java ^
  src\com\campusconnect\ui\CampusApp.java

if errorlevel 1 (
  echo [ERROR] Compilation failed.
  exit /b 1
)

echo [3/3] Launching CampusConnect...
java -cp "out;lib\sqlite-jdbc.jar" com.campusconnect.ui.CampusApp

endlocal
