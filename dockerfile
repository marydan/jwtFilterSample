FROM java:8-jre
WORKDIR usr/src
ADD ./target/FoodAppJPA-0.0.1-SNAPSHOT.jar /usr/src/FoodAppJPA-0.0.1-SNAPSHOT.jar
ENV MYSQL_MY_URL=jdbc:mysql://localhost:3306/demodb?createDatabaseIfNotExist=true&user=root&password=password
ENV MYSQL_ROOT_PASSWORD=password
ENTRYPOINT ["java","-jar","FoodAppJPA-0.0.1-SNAPSHOT.jar"]
