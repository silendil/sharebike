FROM adoptopenjdk:11-jre-hotspot
ADD build/libs/*SNAPSHOT.jar application.jar
RUN sh -c 'touch /application.jar'
ENTRYPOINT ["java", "-jar", "application.jar"]