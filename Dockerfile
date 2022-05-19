FROM maven:3.6.3-jdk-11-slim
COPY Files /app/Files
COPY pdftest /app/pdftest
RUN mvn -f /app/pdftest/pom.xml clean package

ENTRYPOINT ["java","-jar","/app/pdftest/target/jumpstart-1.0-SNAPSHOT.jar"]