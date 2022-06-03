FROM maven:3.6.3-jdk-11-slim

COPY pdftest/pom.xml /app/pdftest/pom.xml
COPY pdftest/result.txt /app/pdftest/result.txt
COPY pdftest/src /app/pdftest/src
RUN mvn -f /app/pdftest/pom.xml clean package

WORKDIR /app/pdftest/
ENTRYPOINT ["java", "-jar","/app/pdftest/target/application-jar-with-dependencies.jar"]
