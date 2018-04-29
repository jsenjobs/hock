package com.jsen.test.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/3
 */
@SpringBootApplication
@EnableConfigServer
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
