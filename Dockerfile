FROM amazoncorretto:17
ENV JAR_NAME app.jar
WORKDIR /webapp
COPY ./build/libs/*-SNAPSHOT.jar $JAR_NAME
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]