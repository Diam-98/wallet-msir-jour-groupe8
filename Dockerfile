FROM openjdk:20-jdk-slim
VOLUME /tmp
EXPOSE 8088
ADD target/wallet-msir-jour-groupe8-0.0.1-SNAPSHOT.jar tp-wallet-msir-jour.jar
ENTRYPOINT ["java","-jar","/tp-wallet-msir-jour.jar"]