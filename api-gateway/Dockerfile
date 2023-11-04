FROM maven:3.8.7-eclipse-temurin-17-alpine@sha256:8592edb5fa55225ae4f2e218d6caf719ebd88ecb56698b563c2b71936544981f AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine@sha256:f4766a483f0754930109771aebccb93c6e7a228b1977cf2e3fd49285270a2eb3
RUN apk add dumb-init && apk cache clean
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway-0.0.1-SNAPSHOT.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "api-gateway-0.0.1-SNAPSHOT.jar"