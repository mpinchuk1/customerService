FROM java:8

EXPOSE 8083

ADD target/customerService-1.0-SNAPSHOT.jar customerService-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "customerService-1.0-SNAPSHOT.jar"]