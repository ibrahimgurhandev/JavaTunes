package com.javatunes;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MusicPlayer {
    //FIELDS
    private Collection<Song> songList;
    private PlayerControls controls = new PlayerControls();
    private String genreChoice;
    private Song songChoice;
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in); //create a scanner to accept user inputs
    public static boolean isFinished = false;

    //CONSTRUCTOR
    public MusicPlayer(){
    }

    //BUSINESS METHOD

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        genreChoice = controller.promptForGenre(); // will return a genre type
        setSongList(controller.findUserChoice(genreChoice));

        while(!isFinished) {
            printList();
            setSongChoice(controller.promptForSong(getSongList()));
            System.out.println("Downloading song................");
            System.out.println("Playing " + getSongChoice().getTitle());
            playSong();
        }
    }


    public void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
                controls.createClip(getSongChoice().url);
                controls.run();
    }

    public void printList(){
        Collection<Song> songs = getSongList();
        for(Song song: songs){
            System.out.println(song);
        }
    }

    //ACCESSOR METHODS
    public Collection<Song> getSongList() {
        return songList;
    }

    public void setSongList(Collection<Song> songList) {
        this.songList = songList;
    }

    public Song getSongChoice(){
        return songChoice;
    }

    public void setSongChoice(Song songChoice){
            this.songChoice = songChoice;
    }

    /*
     * INNER-CLASS
     */



}