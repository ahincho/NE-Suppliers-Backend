# Package This Project Using Maven Running this Command on Terminal
# > mvn clean
# > mvn package
# Run this Command on Terminal to Create the Docker Image
# > docker build -t suppliers:1.1 .
FROM amazoncorretto:17.0.8-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/suppliers-1.1.jar
ADD ${JAR_FILE} suppliers.jar
LABEL name="springboot-colors"
LABEL authors="Angel Hincho"
LABEL mainteiner="ahincho"
COPY target/suppliers-1.1.jar suppliers.jar
ENTRYPOINT ["java","-jar","/suppliers.jar"]