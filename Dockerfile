# Etapa 1: Construcción del proyecto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copiamos el archivo pom.xml y descargamos las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y construimos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución
FROM openjdk:21-jdk-slim
WORKDIR /app
# Exponemos el puerto en el que se ejecutará la aplicación
EXPOSE 8081
# Copiamos el archivo JAR generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "SistemaIntegradoDe_GestionDeAlmacenYMantenimiento.jar"]
