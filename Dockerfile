FROM maven as build
WORKDIR /build
COPY pom.xml .
COPY . .
RUN mvn clean package

FROM openjdk:8
COPY --from=build /build/target/TenantAPI-0.0.1-SNAPSHOT.jar  tenant.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","tenant.jar"]
