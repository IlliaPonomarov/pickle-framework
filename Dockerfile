FROM openjdk:17-alpine

WORKDIR /app

COPY . /app/

# Install Maven
RUN apk update && apk add --no-cache maven
RUN mvn package
RUN mkdir /app/input && mkdir /app/output

COPY target/pickle-1.0-jar-with-dependencies.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--input=/app/input", "--output=/app/output"]