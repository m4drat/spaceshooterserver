package com.madrat.spaceshooter.apiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * start mysql Server
 * mysql -u root -p tUWx****************
 *     create database users;
 *     use users;
 *     create table users_info(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, clientuuid varchar(64) unique, serveruuid varchar(64) unique, score int, username varchar(64), created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
 *     grant all on users.* to 'spaceshooteradm'@'%';
 *     SELECT * FROM users_info;
 *     DELETE FROM users_info;
 *
 * .\gradlew.bat build
 * java -jar .\build\libs\com.madrat.spaceshooter.apiserver.Server-0.1.0.jar
 * */

@ComponentScan(basePackages = {"com/madrat/spaceshooter/apiserver/controllers"})
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
