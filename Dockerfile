FROM azul/zulu-openjdk-alpine:11-jre
WORKDIR /
ADD build/libs/*[0-9].jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar" ]