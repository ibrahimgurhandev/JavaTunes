package com.javatunes.system;

import com.apps.util.Prompter;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class PlayButtons {
    //FIELDS
    private Prompter prompter = new Prompter(new Scanner(System.in));
    private Clip clip;

    //BUSINESS METHODS
    public void run(Song song) {
        //this loop will accept user input and manipulate clip depending on response
        String buttonPressed = "";
        while (!buttonPressed.equalsIgnoreCase("B")) {
            System.out.println("Playing '" + song.getTitle() + "' by: " + song.getArtist());
            buttonPressed = prompter.prompt("<<Play Buttons>> P = Play, S= Stop(Pause), R= Reset(Play from beginning), B = Go Back ", "[bpsrBPSR]", "Invalid response. ");
            switch (buttonPressed.toUpperCase()) {
                case "P":
                    clip.start();
                    break;
                case "S":
                    clip.stop();
                    break;
                case "R":
                    clip.setMicrosecondPosition(0L);
                    clip.start();
                    break;
                case "B":
                    System.out.println("Returning to Song List");
                    clip.close();
                    break;
            }
        }
    }

    public void createClip(String url) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        System.out.println("Downloading song................");
        clip = AudioSystem.getClip();
        clip.open(createAudioStream(url));
    }

    //Helper Methods
    private AudioInputStream createAudioStream(String url) throws IOException, UnsupportedAudioFileException {
        URL songURL = new URL(url);
        return AudioSystem.getAudioInputStream(songURL);
    }
}