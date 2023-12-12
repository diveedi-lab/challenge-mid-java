@echo off
setlocal EnableDelayedExpansion

SET _INTERPOLATION_0=
FOR /f "delims=" %%a in ('docker ps -qf "name=app"') DO (SET "_INTERPOLATION_0=!_INTERPOLATION_0! %%a")
SET "container_id=!_INTERPOLATION_0:~1!"
IF "!container_id!" == "" (
  echo "Could not find container ID, make sure it is up and running."
  exit "1"
)
docker "exec" "-it" "!container_id!" "bash"