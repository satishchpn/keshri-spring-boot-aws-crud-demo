FROM openjdk:17
EXPOSE 8080
ADD target/keshri-spring-boot-aws-crud-demo.jar keshri-spring-boot-aws-crud-demo.jar
ENTRYPOINT ["java","-jar","/keshri-spring-boot-aws-crud-demo.jar"]
