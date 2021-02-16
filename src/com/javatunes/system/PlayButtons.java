package com.javatunes.system;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class PlayButtons {
    private Scanner scanner = new Scanner(System.in); //create a scanner to accept user inputs

    //FIELDS
    private Clip clip;

    //BUSINESS METHODS
    public void run(Song song) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //this loop will accept user input and manipulate clip depending on response
        String buttonPressed = "";

        while (!buttonPressed.equalsIgnoreCase("Q")) {
            System.out.println("Playing '" + song.getTitle()+ "' by: " + song.getArtist());
            System.out.println("P = Play, S= Stop(Pause), R= Reset(Play from beginning), Q = Quit");
            System.out.print("Please enter your choice: ");
            buttonPressed = scanner.next();
            buttonPressed = buttonPressed.toUpperCase();

            switch (buttonPressed) {
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
                case "Q":
                    System.out.println("Quitting Player, Returning to Song List");
                    clip.close();
                    break;
                default:
                    System.out.print("Invalid response. Please enter one of the following:" +
                            " P = Play, S= Stop, R= Reset, Q = Quit ");
                    buttonPressed = scanner.next();
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