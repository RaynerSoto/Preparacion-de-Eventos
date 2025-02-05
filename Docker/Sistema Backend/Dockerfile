FROM openjdk:latest
LABEL authors="Rayner Alejandro Soto Martínez"
WORKDIR proyecto
ADD ../../target/ProyectoEvento-0.0.1-SNAPSHOT.war /proyecto
RUN apt-get update && apt-get install -y tomcat9
COPY /proyecto/ProyectoEvento-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps/
EXPOSE 8080
ENV DB_USER: postgres
ENV DB_PASSWORD: 0000
CMD ["catalina.sh", "run"]
