# Etapa 1: Imagem base para construção do projeto
FROM maven:3.9.5-eclipse-temurin-17 AS builder

# Definir diretório de trabalho dentro do container
WORKDIR /app

# Copiar arquivos do projeto para dentro do container
COPY pom.xml .
COPY src ./src

# Baixar dependências e compilar o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem para rodar a aplicação
FROM eclipse-temurin:17-jdk-alpine

# Definir diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Expor a porta que o Spring Boot usará
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
