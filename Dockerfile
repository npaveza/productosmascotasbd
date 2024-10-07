FROM eclipse-temurin:22-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_FullStackS6 /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk 

COPY --from=buildstage /app/target/productosmascotasbd-0.0.1-SNAPSHOT.jar /app/productosmascotas.jar

COPY Wallet_FullStackS6 /app/wallet

EXPOSE 8080

ENTRYPOINT [ "java", "-jar","/app/productosmascotas.jar" ]