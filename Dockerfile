# Usa una imagen oficial de Java (JDK 25)
FROM eclipse-temurin:25-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia todo el contenido del proyecto al contenedor
COPY . .

# Construye el JAR con Maven (usa el wrapper del proyecto)
RUN ./mvnw clean package -DskipTests

# Expone el puerto 8080 (Spring Boot)
EXPOSE 8080

# Lanza la aplicación
CMD ["java", "-jar", "target/contacts-0.0.1-SNAPSHOT.jar"]
