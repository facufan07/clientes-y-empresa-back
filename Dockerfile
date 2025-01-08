FROM openjdk:21-jdk-slim

COPY target/manejoVentas-0.0.1-SNAPSHOT.jar app_ventas.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app_ventas.jar"]
