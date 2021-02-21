call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD.BAT has errors - breaking work
goto fail

:openbrowser
start chrome --new-window http://localhost:8080/crud/v1/task/getTasks
echo .
echo Browser opened
goto end

:fail
echo.
echo There were errors in STARTBROWSER.BAT file

:end
echo .
echo STARTBROWSER.BAT finished

cmd /k