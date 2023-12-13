@echo off

SET "TLC_DBURL=%1postgresql://localhost:5432/challenge%"
SET "TLC_DBUSER=%2postgres%"
SET "TLC_DBPASSWORD=%3secret%"

mvn "-Dmaven.test.skip=true" "clean" "install" "compile" "package" && mvn "-pl" "challenge" "-Dflyway.url="jdbc:%TLC_DBURL%"" "-Dflyway.user="%TLC_DBUSER%"" "-Dflyway.password="%TLC_DBPASSWORD%"" "flyway:migrate" && mvn "-pl" "challenge" "liberty:run" "-Dliberty.jvm.debug=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7777"