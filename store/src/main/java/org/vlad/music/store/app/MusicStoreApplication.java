package org.vlad.music.store.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;;

@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients(
        basePackages = "org.vlad"
)
public class MusicStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicStoreApplication.class, args);
    }
}