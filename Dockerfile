# ETAPA 1: Construcción (El "Chef" con todas las herramientas)
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copiamos los archivos de configuración de Gradle primero
# Esto ayuda a que Docker cachee las librerías y no las descargue siempre
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# Construimos el proyecto generando el archivo .jar executable
# Usamos --no-daemon para que Gradle no se quede corriendo en segundo plano
RUN gradle bootJar --no-daemon

# ETAPA 2: Ejecución (El "Mesero" que solo lleva el plato)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Gradle por defecto guarda el resultado en build/libs/
# Copiamos solo el archivo .jar que termina en .jar (evitando el -plain.jar)
COPY --from=build /app/build/libs/*.jar app.jar

# Exponemos el puerto de Spring Boot
EXPOSE 8080

# Arrancamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]