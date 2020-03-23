package com.ubs.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class TesteApplication {
    @Value("${import.path}")
    private String path;

    @Value("${import.readPath}")
    private String readPath;

    @Value("${import.errorPath}")
    private String errorPath;

    public static void main(String[] args) {
        SpringApplication.run(TesteApplication.class, args);
    }


    @Bean
    @Autowired
    public CommandLineRunner startCommandLineRunner() {
        File fileDir = new File(File.listRoots()[0].getAbsolutePath() + path);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        File readDir = new File(fileDir.getAbsolutePath() + readPath);
        if (!readDir.exists()) {
            readDir.mkdir();
        }

        File errorDir = new File(fileDir.getAbsolutePath() + errorPath);
        if (!errorDir.exists()) {
            errorDir.mkdir();
        }
        return args -> System.out.println("started");
    }
}
