FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .
COPY testng.xml .

RUN mvn -B -DskipTests dependency:go-offline

COPY src ./src

CMD ["mvn", "-B", "clean", "test", "-Dheadless=true", "-Ddocker=true", "-Dselenium.grid.url=http://selenium-chrome:4444"]