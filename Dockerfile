FROM openjdk:11
EXPOSE 8080
ADD build/libs/ConnectionWithMySQL-0.0.1-SNAPSHOT.jar ConnectionWithMySQL-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ConnectionWithMySQL-0.0.1-SNAPSHOT.jar"]