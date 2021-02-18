package com.javatunes.client;

import com.apps.util.Prompter;
import com.javatunes.controller.MusicPlayer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;
public class MusicPlayerClient{


//    @Override
//    public void start() {
//        System.out.println("Welcome to Java Tunes");
//        MusicPlayer player = null;
//        try {
//            player = new MusicPlayer(new Prompter(new Scanner(System.in)));
//            player.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        MusicPlayer player = new MusicPlayer(new Prompter(new Scanner(System.in)));
        player.start();
        //create an instance of music player
        //MusicPlayerClient client = new MusicPlayerClient();
//        client.welcome(1,"images/splashscreen.png");
//        client.start();

    }

}