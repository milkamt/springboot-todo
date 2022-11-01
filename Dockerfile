FROM gradle:7.4.2-jdk11

WORKDIR springboot-todo
COPY . .
RUN ./gradlew clean build -x test

CMD ./gradlew bootRun