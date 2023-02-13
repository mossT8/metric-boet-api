FROM openjdk:17-alpine

WORKDIR /app

COPY target/metric-boet-api-3.0.2.jar /app/metric-app.jar

EXPOSE 8080

CMD ["java", "-jar", "metric-app.jar"]
