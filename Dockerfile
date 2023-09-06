FROM adoptopenjdk:17-jdk-hotspot

RUN apt-get update && \
    apt-get install -y maven=3.8.3-0ubuntu0.20.04.1 && \
    apt-get clean

WORKDIR /app

COPY pom.xml /app/

RUN mvn clean install -DskipTests

COPY target/banking-service-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
