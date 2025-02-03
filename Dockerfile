FROM maven:3.6.1-jdk-8-alpine as build-app

WORKDIR /
COPY pom.xml pom.xml
COPY quest-app quest-app
COPY quest-database quest-database

RUN mvn clean package

FROM tomcat:9.0.10-jre8-alpine as build-container

WORKDIR /
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build-app build/*.war /usr/local/tomcat/webapps/ROOT.war
COPY container/tomcat/bin/setenv.sh  /usr/local/tomcat/bin/
COPY container/tomcat/conf/context.xml  /usr/local/tomcat/conf/
COPY container/tomcat/conf/server.xml  /usr/local/tomcat/conf/

CMD ["catalina.sh", "run"]