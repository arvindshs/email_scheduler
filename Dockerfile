# ==============================
# 🏗️ Build stage
# ==============================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and Maven wrapper
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# 👇 Fix permission issue (this line is essential)
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# ==============================
# 🚀 Runtime stage
# ==============================
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Optional: create non-root user for safety
RUN useradd -m springuser
USER springuser

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Railway sets $PORT automatically)
ENV PORT=8080
EXPOSE 8080

# Optional: JVM tuning for container environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=80.0"

# Start the app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
