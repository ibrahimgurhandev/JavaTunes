package com.javatunes.controller;

import com.javatunes.system.Catalog;
import com.javatunes.system.Genre;
import com.javatunes.system.PlayButtons;
import com.javatunes.system.Song;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.*;


public class MusicPlayer {
    //FIELDS
    private Collection<Song> songList;
    private Map<Integer, Song> songMap = new HashMap<>();
    private String genreChoice;
    private Song songChoice;
    private Catalog catalog = new Catalog("data/song-data.csv");
    private PlayButtons controls = new PlayButtons();
    private Scanner scanner = new Scanner(System.in); //create a scanner to accept user inputs
    private boolean isFinished = false;
    private boolean isRestarted = false;

    //CONSTRUCTOR
    public MusicPlayer() throws IOException {
    }

    //BUSINESS METHODS

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        while (!isRestarted) {
            genreChoice = promptForGenre(); // will return a genre type
            setSongList(findUserChoice(genreChoice));
            playSong();
        }
    }

    public void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            while (!isFinished) {
                printList();
                setSongChoice(promptForSong());
                controls.createClip(getSongChoice().getUrl());
                controls.run(getSongChoice());
            }
        } catch (Exception e) {
            setFinished(false);
            start();
        }
    }

    //prompting methods
    private String promptForGenre() {
        System.out.print("Please enter a number to choose a genre 1. POP 2.ROCK 3. HIP-HOP 4. COUNTRY 5. TV-TUNES, 6. ALL: or 7. To Exit ");
        String choice = scanner.next();
        List<String> correctChoices = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
        while (!correctChoices.contains(choice)) {
            System.out.print("Error: You must enter a number between 1-7: \n");
            choice = scanner.next();
        }
        return choice;
    }

    private Song promptForSong() {
        String choice;
        Song userSong = null;
        while (userSong == null) {
            System.out.print("Please enter a valid song ID to play or 'B' to go back to Genres: ");
            choice =  scanner.next();
            if(choice.equalsIgnoreCase("b")){
                setFinished(true);
                break;
            }
            for(Integer id: songMap.keySet()){
                if(String.valueOf(id).equals(choice)){
                    userSong = songMap.get(id);
                }
            }
        }
        return userSong;
    }

    //helper methods
    private Collection<Song> findUserChoice(String choiceNum) {
        Collection<Song> songList = null;
        Genre userGenre = null;
        switch (choiceNum) {
            case "1":
                userGenre = Genre.POP;
                break;
            case "2":
                userGenre = Genre.ROCK;
                break;
            case "3":
                userGenre = Genre.HIP_HOP;
                break;
            case "4":
                userGenre = Genre.COUNTRY;
                break;
            case "5":
                userGenre = Genre.TV_TUNES;
                break;
            case "6":
                songList = catalog.getSongs();
                break;
            case "7":
                System.out.println("Exiting Music Player. GoodBye.");
                setRestarted(true);
                break;
            default:
                System.out.print("Error: You must enter a number between 1 - 6. ");
        }
        if(userGenre != null){
            songList = catalog.findByGenre(userGenre);
        }
        return songList;
    }

    private void printList() {
        Integer id = 1;
        for (Song song : getSongList()) {
            songMap.put(id,song);
            System.out.println("Enter  ID= "+ id + song);
            id++;
        }
    }

    //ACCESSOR METHODS
    public Collection<Song> getSongList() {
        return songList;
    }

    public void setSongList(Collection<Song> songList) {
        this.songList = songList;
    }

    public Song getSongChoice() {
        return songChoice;
    }

    public void setSongChoice(Song songChoice) {
        this.songChoice = songChoice;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isRestarted() {
        return isRestarted;
    }

    public void setRestarted(boolean restarted) {
        isRestarted = restarted;
    }
}