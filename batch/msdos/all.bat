pushd %~dp0

REM lein-repl.bat
call lein-compile.bat 0<a-key-press-in-a-ms-dos-batch-file.txt
call lein-run.bat 0<a-key-press-in-a-ms-dos-batch-file.txt
call lein-test.bat 0<a-key-press-in-a-ms-dos-batch-file.txt
call lein-uberjar.bat 0<a-key-press-in-a-ms-dos-batch-file.txt
call run-simple.bat 0<a-key-press-in-a-ms-dos-batch-file.txt

popd
pause
