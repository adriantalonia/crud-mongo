FROM openjdk:11-jdk
VOLUME /tmp 
COPY target/CrudMongo-0.0.1-SNAPSHOT.jar CrudMongo.jar 
ENTRYPOINT ["java","-jar","CrudMongo.jar"] 