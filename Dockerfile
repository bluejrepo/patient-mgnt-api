FROM openjdk:17
WORKDIR /app
COPY ./target/account-mngmt-api-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "account-mngmt-api-0.0.1-SNAPSHOT.jar"]