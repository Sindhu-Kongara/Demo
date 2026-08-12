#stage1 build
FROM gradle:9.6.1-jdk26-ubi10 AS builder
# Set working directory
WORKDIR /home/gradle/src

# Copy project files
COPY build.gradle settings.gradle /home/gradle/src/
COPY src /home/gradle/src/src

# Build the application
RUN gradle clean build --refresh-dependencies --no-daemon --stacktrace

#stage2 runtime

FROM amazoncorretto:26.0.2-al2023-headless AS runner1

# Set working directory
WORKDIR /tmp

# Copy built artifact from the builder stage
COPY --from=builder /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

VOLUME ["/tmp"]


# Expose port 8080 for incoming traffic
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]
