package com.example.onecar.notifications;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PushNotificationService {

    @Value("${firebase.config.file}")
    private String firebaseConfigFile;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

//    @PostConstruct
//    public void init() {
//        try {
//            ClassLoader classLoader = getClass().getClassLoader();
//            InputStream serviceAccount = classLoader.getResourceAsStream("cloud-message/" + firebaseConfigFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
