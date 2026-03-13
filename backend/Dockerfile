FROM maven:4.0.0-rc-5-ibm-semeru-25-noble AS build-stage
ARG DB_USERNAME
ARG DB_PASSWORD
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
COPY . /app
WORKDIR /app
RUN sed -i "s|spring.datasource.username=.*|spring.datasource.username=$DB_USERNAME|" src/main/resources/application.properties
RUN sed -i "s|spring.datasource.password=.*|spring.datasource.password=$DB_PASSWORD|" src/main/resources/application.properties
RUN mvn -B package --file pom.xml -DskipTests
FROM ibm-semeru-runtimes:open-17-jdk AS production-stage
RUN mkdir /opt/app
COPY --from=build-stage /app/target/ecommerce-0.0.1-SNAPSHOT.jar /opt/app
# CMD ["java", "-jar", "/opt/app/ecommerce-0.0.1-SNAPSHOT.jar"]