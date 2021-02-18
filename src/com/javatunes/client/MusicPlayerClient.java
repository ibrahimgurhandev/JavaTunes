package com.javatunes.client;

import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.javatunes.controller.MusicPlayer;

import java.util.Scanner;

public class MusicPlayerClient implements SplashApp {
    // Initializes the music player here
    @Override
    public void start() {
        System.out.println("Welcome to Java Tunes");
        MusicPlayer player = null;
        try {
            player = new MusicPlayer(new Prompter(new Scanner(System.in)));
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Entry point to the application
    //and get splash screen
    public static void main(String[] args) {
        MusicPlayerClient client = new MusicPlayerClient();
        client.welcome(2000, "images/splashscreen.png", "images/credits.png");
        client.start();
    }
}