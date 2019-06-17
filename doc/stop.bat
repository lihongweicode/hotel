@echo off
set port=8080
echo port : %port%

for /f "usebackq tokens=1-5" %%a in (`netstat -ano ^| findstr %port%`) do (
	if [%%d] EQU [LISTENING] (
		set pid=%%e
	)
)

for /f "usebackq tokens=1-5" %%a in (`tasklist ^| findstr %pid%`) do (
	set image_name=%%a
)

echo now will kill process : pid %pid%, image_name %image_name%
pause
taskkill /f /pid %pid%
pause
