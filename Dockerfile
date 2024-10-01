# Usar uma imagem base do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado para o diretório de trabalho no contêiner
COPY target/petconnect-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta em que o Spring Boot irá rodar
EXPOSE 8080

# Definir o comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
