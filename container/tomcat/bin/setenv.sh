#!/bin/sh

JAVA_OPTS="$JAVA_OPTS"

#JVM
JAVA_OPTS="$JAVA_OPTS -Xms${JAVA_XMS:-256M} -Xmx${JAVA_XMX:-2048M}"
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
JAVA_OPTS="$JAVA_OPTS -Ddb_url=${DB_URL}"
JAVA_OPTS="$JAVA_OPTS -Ddb_usr=${DB_USERNAME}"
JAVA_OPTS="$JAVA_OPTS -Ddb_pwd=${DB_PASSWORD}"
JAVA_OPTS="$JAVA_OPTS -Dapp_port=${APP_PORT:-8080}"