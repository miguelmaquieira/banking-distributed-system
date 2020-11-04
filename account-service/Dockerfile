FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/account-service-0.0.1.jar
ADD ${JAR_FILE} account-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "account-service-0.0.1.jar"]
