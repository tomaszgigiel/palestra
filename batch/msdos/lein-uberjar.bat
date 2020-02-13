if not exist "%HOMEPATH%\_delete_content\" md "%HOMEPATH%\_delete_content\"
pushd %~dp0\..\..
if exist target rmdir /s /q target
call lein uberjar
pause
popd
