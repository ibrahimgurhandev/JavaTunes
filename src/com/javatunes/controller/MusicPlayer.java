package com.javatunes.controller;

import com.apps.util.Prompter;
import com.javatunes.system.*;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MusicPlayer {

    //FIELDS
    private Collection<Song> songList;
    private Map<Integer, Song> songMap = new HashMap<>();
    private String genreChoice;
    private Song songChoice;
    private Prompter prompter;
    private Catalog catalog = Catalog.get("data/song-data.csv");//load song information from csv file
    public PlayButtons controls = new PlayButtons(new Prompter(new Scanner(System.in)));
    private boolean isFinished = false;//used to check state of song play
    private boolean isRestarted = false;//keeps the music player open

    //CONSTRUCTOR
    public MusicPlayer(Prompter prompter) throws IOException {
        this.prompter = prompter;
    }

    //BUSINESS METHODS
    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        while (!isRestarted) {
            genreChoice = promptForGenre(); // will return a String of 1-7 from menu
            setSongList(findUserChoice(genreChoice));
            playSong();
        }
    }

    public void playSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            while (!isFinished) {
                printListAndCreateMap();
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
    String promptForGenre() {
        int countOfGenre = getGenreCount() + 1; //adding "1" for our "ALL" choice
        return prompter.prompt("Please enter a number to choose a genre 1. POP 2. ROCK 3. HIP-HOP 4. COUNTRY 5. TV-TUNES, 6. ALL or Q to Quit Music Player ", "[qQ1-" + countOfGenre + "]", "Error: You must enter a number between 1-" + countOfGenre + " or Q to Quit.");
    }

    //This is package private just for testing now
    Song promptForSong() {
        String pattern = getRegex();
        String choice;
        choice = prompter.prompt("Please enter a song ID to play or 'B' to go back to Genres: ", pattern, "Invalid! Please enter a song ID or 'B'");
        Song userSong = null;
        if (choice.equalsIgnoreCase("b")) {
            setFinished(true);
        }
        for (Integer id : songMap.keySet()) {
            if (String.valueOf(id).equals(choice)) {
                userSong = songMap.get(id);
            }
        }
        return userSong;
    }

    //helper methods

    //This is package private just for testing now
    Collection<Song> findUserChoice(String choiceNum) {
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
            case "Q":
            case "q":
                System.out.println("Exiting Music Player. GoodBye.");
                setRestarted(true);
                break;
            default:
                System.out.print("Error: You must enter a number between 1 - 6. ");
        }
        if (userGenre != null) {
            songList = catalog.findByGenre(userGenre);
        }
        return songList;
    }

    //This is package private just for testing now
    void printListAndCreateMap() {
        Integer id = 1;
        for (Song song : getSongList()) {
            songMap.put(id, song);
            System.out.println("Enter " + id + " to play " + song);
            id++;
        }
    }

    //This is package private just for testing now
    int getGenreCount() {
        Collection<Song> songs = catalog.getSongs();
        int countOfGenre = songs.stream() //Gets distinct Genre count
                .collect(Collectors.toCollection(
                        () -> new TreeSet<Song>((p1, p2) -> p1.getGenre().compareTo(p2.getGenre()))))
                .size();
        return countOfGenre;
    }

    //This is package private just for testing now
    String getRegex() {// regex used in prompter
        int countOfSongs = getSongList().size();
        return countOfSongs > 9 ? "0?[bB1-9]|1[bB0" + countOfSongs + "]" : "[bB1-" + countOfSongs + "]";
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

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setRestarted(boolean restarted) {
        isRestarted = restarted;
    }
}