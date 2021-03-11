FROM tomcat:8.5-jdk11

MAINTAINER spicedpine

COPY ./web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/reha.war

ENV MY_SQL_USERNAME=root
ENV MY_SQL_PASSWOED=1235
ENV ACTIVE_MQ_USERNAME=admin
ENV ACTIVE_MQ_PASSWOED=admin
ENV ACTIVE_MQ_TOPIC_NAME=eventsTopic

EXPOSE 8081

CMD ["catalina.sh", "run"]