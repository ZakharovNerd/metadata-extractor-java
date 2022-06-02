FROM maven:3.6.3-jdk-11-slim
<<<<<<< HEAD
COPY pdftest/Files /app/Files
=======
COPY Files /app/Files
>>>>>>> 9721aa1250920bff26bc2b743d63312c90a0e4e8
COPY pdftest /app/pdftest
RUN mvn -f /app/pdftest/pom.xml clean package

ENTRYPOINT ["java","-jar","/app/pdftest/target/jumpstart-1.0-SNAPSHOT.jar"]