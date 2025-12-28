# 1️⃣ Stage build: cần Maven + JDK để build
FROM eclipse-temurin:21.0.9_10-jdk-alpine-3.23 AS build

WORKDIR /app

# Copy pom.xml để cache dependencies
COPY pom.xml .
COPY .mvn/wrapper .mvn/wrapper
COPY mvnw .
RUN pwd
RUN ls
RUN java -version

RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build project
RUN ./mvnw clean package -DskipTests

# 2️⃣ Stage runtime: chỉ cần JRE / JVM
FROM eclipse-temurin:21.0.9_10-jre-alpine-3.23

# Tạo user không phải root
RUN addgroup -S blockwiki && adduser -S blockwiki -G blockwiki
USER blockwiki

WORKDIR /app

# Copy jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]