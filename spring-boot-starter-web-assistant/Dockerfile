FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/spring-boot-starter-web-assistant-1.0.0.jar spring-boot-starter-web-assistant.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /spring-boot-starter-web-assistant.jar