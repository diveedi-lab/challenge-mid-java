@echo off

mvn "-Dmaven.test.skip=true" "clean" "install" "compile" "package"
SET "TLC_DBURL=%1:-postgresql://localhost:5432/challenge%"
SET "%1:-postgresql://localhost:5432/challenge%TLC_DBUSER=%2:-postgres%"
SET "%1:-postgresql://localhost:5432/challenge%%2:-postgres%TLC_DBPASSWORD=%3:-secret%"
mvn "-pl" "challenge" "-Dflyway.url="jdbc:%TLC_DBURL%"" "-Dflyway.user="%TLC_DBUSER%"" "-Dflyway.password="%TLC_DBPASSWORD%"" "flyway:migrate"
env "TLC_DBURL=%TLC_DBURL%" "TLC_DBUSER=%TLC_DBUSER%" "TLC_DBPASSWORD=%TLC_DBPASSWORD%" "mvn" "-pl" "challenge" "liberty:run" "-Dliberty.jvm.debug=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7777"