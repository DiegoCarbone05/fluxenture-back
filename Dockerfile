# syntax=docker/dockerfile:1.7

# Stage 1: Build the Spring Boot artifact with cached dependencies
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy build definition files first to maximize Docker layer reuse.
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Resolve dependencies in a dedicated cached layer.
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle --no-daemon --build-cache dependencies

# Copy source code after dependencies so code changes do not invalidate dep cache.
COPY src ./src

# Build the executable jar.
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle --no-daemon --build-cache bootJar

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]