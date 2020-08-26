FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD **this is where we had the project jar or exe or whatever the file is**
ENV JAVA_OPTS=""
ENTRYPOINT [**Probably the index.html but essentially will equate to an ng serve]
