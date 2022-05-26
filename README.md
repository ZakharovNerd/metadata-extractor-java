# metadata-extractor-java
## Начало
* Добавьте файлы, которые хотите обработать в папку Files
* Запустите программу
* рузультат лежит в текстовом файле result.txt
## Инструкция по запуску
```bash
cd pdftest
mvn install
mvn compile
java -jar target/application-jar-with-dependencies.jar

```
С использованием docker:
```bash
docker build -t metadata-extractor .
docker run metadata-extractor
```
----

[CRC card](CRC-cards.md)
