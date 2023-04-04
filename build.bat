@echo off
call mvn clean package
echo.
echo Done. Unless there where any compile errors you should be able to find your plugin jar in
echo %cd%\target
echo.
echo Place the compiled jar file in the plugin folder of your server to install your plugin
pause