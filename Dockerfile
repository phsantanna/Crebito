FROM maven.3.8.3-openjdk-17 as build

WORKDIR /out
COPY . /out/
RUN mvn clean package

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /out/artifacts/Crebito_jar/Crebito.jar /out/app.jar
EXPOSE 8080
ENTRYPOINT["java", "-jar", "app.jar"]
