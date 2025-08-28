1. Create Dockerfile
=================================
FROM openjdk:17
WORKDIR /app
COPY ./target/account-mngmt-api-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "account-mngmt-api-0.0.1-SNAPSHOT.jar"]
=================================

2. Build docker image
   %>docker build -t mulesie/accountmngmtapi .

3. Run app
   %>docker run -d -p 8081:8080 --name accountmngmtapi mulesie/accountmngmtapi:latest
   564261b8bf6c41ac2e65c2de45a5f3d91303f526ce08c3c399b8875d7028f9cf
4. Check runninng process.
   %>docker container ps

5. curl --location 'http://localhost:8081/api/heart-beat'

6. docker kill 564261b8bf6c41ac2e65c2de45a5f3d91303f526ce08c3c399b8875d7028f9cf