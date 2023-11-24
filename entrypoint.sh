#!/bin/sh

# build all projects, also skip tests since we've
# included bugs on purpose.
mvn -Dmaven.test.skip=true clean install compile package

# run database migrations
mvn -pl challenge \
    -Dflyway.url="jdbc:$TLC_DBURL" \
    -Dflyway.user="$TLC_DBUSER" \
    -Dflyway.password="$TLC_DBPASSWORD" \
    flyway:migrate

# start the server
mvn -pl challenge liberty:run -Dliberty.jvm.debug="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7777"
