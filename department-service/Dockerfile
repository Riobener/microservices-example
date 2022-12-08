FROM openjdk:17
ARG JAR_FILE=./build/libs/department-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} department-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","department-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082