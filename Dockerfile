FROM openjdk:17
EXPOSE 3456
ADD target/docker-jenkins-integration-demo.jar docker-jenkins-integration-demo.jar
ENTRYPOINT ["java","-jar","/docker-jenkins-integration-demo.jar"]