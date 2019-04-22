package com.madrat.spaceshooter.apiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@ComponentScan(basePackages = {"com/madrat/spaceshooter/apiserver/controllers"})
@SpringBootApplication
public class Server {

    /**
     * mysqld --initialize
     * mysql -u root -p tUWx****************
     *   create database users;
     *   create user 'spaceshooteradm'@'%' identified by 'R9si****************';
     *   grant all on users.* to 'spaceshooteradm'@'%';
     *    create table users_info(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, clientuuid varchar(64) unique, serveruuid varchar(64) unique, score int, username varchar(64), created_at DATETIME, updated_at DATETIME);
     * */

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @EnableJpaAuditing
    @Configuration
    public class CustomWebMvcConfiguration extends WebMvcConfigurationSupport {

        @Override
        protected void extendMessageConverters( List<HttpMessageConverter<?>> converters ) {
            for ( HttpMessageConverter<?> converter : converters ) {
                if ( converter instanceof MappingJackson2HttpMessageConverter) {
                    MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                    jacksonConverter.setPrettyPrint( true );
                }
            }
        }
    }
}
