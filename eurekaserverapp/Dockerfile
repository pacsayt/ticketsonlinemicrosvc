# pt++ : docker run -p 8761:8761 --name eurekaserverapp
FROM openjdk:13-jdk-alpine
# pt++ : the second will not be equal to host's /home/.../ticketsonlinecfg
VOLUME ["/var/tmp", "/home/tamas/projektek/TicketsOnline/ticketsonlinecfg"]
WORKDIR /opt/eurekaserverapp
EXPOSE 8761
EXPOSE 9411
COPY target/eurekaserverapp*.jar eurekaserverapp.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar ${WORKDIR}/eurekaserverapp.jar" ]
