FROM openjdk:20-jdk-slim
VOLUME /tmp
EXPOSE 8088
ADD target/walette_app-0.0.1-SNAPSHOT.jar wallet-msir-jour-groupe8.jar
ENTRYPOINT ["java","-jar","/wallet-msir-jour-groupe8.jar"]