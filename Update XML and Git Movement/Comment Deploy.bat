@echo off
setlocal enabledelayedexpansion
java CommentPom

@REM Change
cd /d D:\UOB - Finastra\Automation\Batch Pom xml Jenkins deploy test automation 

@REM Change
git -c http.sslVerify=false add pom.xml commented_lines.txt
git -c http.sslVerify=false commit -m "Commented out line in pom.xml and updated commented_lines.txt"
git -c http.sslVerify=false push -u origin master

echo Changes committed and pushed successfully.
pause
