# Базовый образ с JDK
FROM openjdk:17-jdk-slim
LABEL authors="acer"

# Папка внутри контейнера, куда скопируем jar
WORKDIR /app

# Копируем jar-файл из папки target
COPY target/*.jar onecar.jar

# Указываем команду запуска приложения
ENTRYPOINT ["java", "-jar", "onecar.jar"]
