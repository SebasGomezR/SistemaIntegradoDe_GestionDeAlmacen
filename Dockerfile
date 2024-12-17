# Etapa 1: Construcción de la aplicación
FROM maven:3.9-jdk-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/your-app.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
