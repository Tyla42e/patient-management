FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY billing-service/pom.xml .

RUN mvn dependency:go-offline -B

COPY billing-service/src ./src 

RUN mvn clean package

FROM openjdk:21-jdk AS runner

WORKDIR /app
COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

RUN mkdir -p /usr/local/newrelic
ADD ./newrelic/newrelic.jar /usr/local/newrelic/newrelic.jar
ADD ./newrelic/newrelic.yml /usr/local/newrelic/newrelic.yml

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java","-javaagent:/usr/local/newrelic/newrelic.jar","-jar","/app/app.jar"]
