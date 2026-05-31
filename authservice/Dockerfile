# Use the official Maven image as the base image
# --------- Stage 1: Build ---------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy only build files first (for caching dependencies)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon || return 0

# Copy source code
COPY . .

# Build the app (generates a fat jar)
RUN ./gradlew clean bootJar --no-daemon

# Create a new image for running the application
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]