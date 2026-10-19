#!/usr/bin/env bash
# ============================================================
#  CampusConnect - Student Management System (Linux/Mac build)
# ============================================================
set -e

echo "[1/3] Cleaning previous build..."
rm -rf out

echo "[2/3] Compiling Java sources..."
javac -cp "lib/sqlite-jdbc.jar" -d out \
  src/com/campusconnect/model/Student.java \
  src/com/campusconnect/db/Database.java \
  src/com/campusconnect/ui/CampusApp.java

echo "[3/3] Launching CampusConnect..."
java -cp "out:lib/sqlite-jdbc.jar" com.campusconnect.ui.CampusApp
