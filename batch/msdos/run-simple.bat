if not exist "%HOMEPATH%\_delete_content\" md "%HOMEPATH%\_delete_content\"
pushd %~dp0\..\..
REM if exist target rmdir /s /q target
java -jar ./target/uberjar/palestra-uberjar.jar
pause
popd
