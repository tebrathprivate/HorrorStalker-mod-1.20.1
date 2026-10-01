@echo off
setlocal
title Horror Stalker - Fabric 1.20.1 Builder

where java >nul 2>nul
if errorlevel 1 (
  echo Java nahi mila. Java 17 ya newer install karo.
  pause
  exit /b 1
)

if not exist ".gradle-local\gradle-8.7\bin\gradle.bat" (
  echo First time setup: Gradle 8.7 download ho raha hai...
  if not exist ".gradle-local" mkdir ".gradle-local"
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.7-bin.zip' -OutFile '.gradle-local\gradle.zip'"
  if errorlevel 1 (
    echo Gradle download fail hua. Internet check karo.
    pause
    exit /b 1
  )
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "Expand-Archive -Path '.gradle-local\gradle.zip' -DestinationPath '.gradle-local' -Force"
  del ".gradle-local\gradle.zip"
)

call ".gradle-local\gradle-8.7\bin\gradle.bat" build --no-daemon
if errorlevel 1 (
  echo.
  echo BUILD FAIL hua. Upar wala error screenshot bhej dena.
  pause
  exit /b 1
)

echo.
echo ==========================================
echo BUILD SUCCESS!
echo JAR:
echo build\libs\horrorstalker-1.0.0.jar
echo ==========================================
pause
