FROM alpine:latest
USER root

RUN apk update
RUN apk fetch openjdk17
RUN apk add openjdk17
COPY target/KursProjSpringBootDocker-0.0.1-SNAPSHOT.jar /usr/local/KursProjSpringBootDocker-0.0.1-SNAPSHOT.jar
WORKDIR /usr/local/
EXPOSE 5500
CMD ["java", "-jar", "KursProjSpringBootDocker-0.0.1-SNAPSHOT.jar"]