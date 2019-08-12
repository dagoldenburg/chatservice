FROM openjdk:11
ADD ./target/chatservice-0.0.1-SNAPSHOT.jar chatservice.jar
EXPOSE 8080
CMD ["java", "-jar", "chatservice.jar"]