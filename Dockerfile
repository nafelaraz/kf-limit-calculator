FROM openjdk:8
ADD target/*.jar loan-limit-calculator-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "loan-limit-calculator-service.jar"]