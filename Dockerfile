# Fase 1: Build - Usiamo un'immagine Docker che contiene già Java e Gradle.
FROM gradle:8.5-jdk17 AS build

# Imposta la directory di lavoro all'interno del container.
WORKDIR /home/gradle/project

# Copia tutti i file del progetto nella directory di lavoro.
COPY --chown=gradle:gradle . .

# Rendi lo script gradlew eseguibile.
RUN chmod +x ./gradlew

# Esegui il comando di build per creare il file JAR.
# L'opzione --no-daemon è consigliata per gli ambienti CI/CD come Render.
RUN ./gradlew shadowJar --no-daemon

# Fase 2: Run - Usiamo un'immagine Docker più leggera che contiene solo Java per eseguire l'app.
FROM openjdk:17-slim

# Imposta la directory di lavoro.
WORKDIR /app

# Copia solo il file JAR creato nella fase di build.
COPY --from=build /home/gradle/project/build/libs/resil-server-0.0.1-all.jar .

# Esponi la porta 8080 (la porta di default di Ktor). Render userà questa informazione.
EXPOSE 8080

# Comando per avviare il server quando il container parte.
CMD ["java", "-jar", "resil-server-0.0.1-all.jar"]
