# spaceshooterserver
Simple rest server to work with space shooter (global scoreboard)
### server-part
- To run requires the presence of mysql-server, as well as created a database with table (below is the instructions for running)  
--- Install mysql-server  
--- start mysql service: ```[win]: Win+r -> services.msc -> mysql -> start```  
--- Then use the mysql console: ```mysql -u root -p``` - > ```enter password```  
--- create users database: ```create database users;```  
--- Make users database active: ```use users;```  
--- Create a table: ```create table users_info(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, clientuuid varchar(64) unique, serveruuid varchar(64) unique, score int, username varchar(64), created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);```  
--- Grant all permissions to the table to the pre-created user: ```grant all on users.* to 'spaceshooteradm'@'%';```  
--- This completes mysql-server configuration  
- Java server configuration  
--- Edit the file ```spaceshooterserver\src\main\resources\application.properties```, so that it contains the following lines:  
```spring.jackson.serialization.indent-output=true  
spring.gson.pretty-printing=true  
spring.jpa.hibernate.ddl-auto = update  
spring.datasource.url=jdbc:mysql://localhost:3306/users  
spring.datasource.username=spaceshooteradm  
spring.datasource.password=R9siJmB6tEAVyICug0NV
```  
--- then rebuild the jar file: ``./gradlew.bat build``  
--- now the `build/libs/` will contain the built jar file  
--- run: ``java -jar build/libs/<filename>.jar``  
--- Java-server configuration is complete.  
