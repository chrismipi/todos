FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./build/libs/*.jar application.jar
ENTRYPOINT ["java","-jar","./application.jar"]