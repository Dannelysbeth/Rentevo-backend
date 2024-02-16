FROM openjdk:21
ADD target/rentevo.jar rentevo.jar
ENTRYPOINT ["java","--enable-preview","-jar","/rentevo.jar"]