# Etapa 1: Builder (Construção do Projeto com Maven e JDK 21)
FROM eclipse-temurin:21-jdk AS builder

# Instalar Maven no container
RUN apt-get update && apt-get install -y maven

# Definir diretório de trabalho dentro do container
WORKDIR /app

# Copiar arquivos do projeto para o container
COPY pom.xml .
COPY src ./src

# Executar o Maven para compilar e empacotar o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar a aplicação
FROM eclipse-temurin:21-jre

# Definir diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado da etapa de build para esta imagem final
COPY --from=builder /app/target/*.jar app.jar

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
