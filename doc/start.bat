%1 mshta vbscript:CreateObject("WScript.Shell").Run("%~s0 ::",0,FALSE)(window.close)&&exit

.\jdk1.8.0_101_x64\bin\java.exe -jar hotel-1.0-SNAPSHOT.jar application.properties > log.txt