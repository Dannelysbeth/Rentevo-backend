FROM openjdk:21
ADD target/postgres-shop.jar postgres-shop.jar
ENTRYPOINT ["java","--enable-preview","-jar","/postgres-shop.jar"]