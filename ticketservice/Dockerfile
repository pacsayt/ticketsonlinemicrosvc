# pt++ : docker run -p 8761:8761 --name eurekaserverapp
FROM openjdk:13-jdk-alpine
VOLUME /var/tmp
WORKDIR /opt/ticketservice
EXPOSE 8761
EXPOSE 9411
COPY target/ticketservice*.jar ticketservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/ticketservice/ticketservice.jar" ]