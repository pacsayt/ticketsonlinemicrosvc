FROM openjdk:13
VOLUME /tmp
ADD target/eurekaserverapp-0.0.1-SNAPSHOT.jar eurekaserverapp.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eurekaserverapp.jar" ]
