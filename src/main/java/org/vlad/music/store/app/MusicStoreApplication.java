package org.vlad.music.store.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class MusicStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicStoreApplication.class, args);
    }
}