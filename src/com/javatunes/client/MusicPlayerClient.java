package com.javatunes.client;

import com.apps.util.Prompter;
import com.javatunes.controller.MusicPlayer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayerClient {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //create an instance of music player
        MusicPlayer player = new MusicPlayer(new Prompter(new Scanner(System.in)));

        //run it
        player.start();

    }
}