package com.javatunes;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private Scanner scanner = new Scanner(System.in); //create a scanner to accept user inputs

    public String promptForGenre() {
        System.out.print("Please enter a number to choose a genre 1. POP 2.ROCK 3. HIP-HOP 4. COUNTRY 5. TV-TUNES, 6. ALL: or 7. ");
        String choice = scanner.next();
        List<String> correctChoices = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        while (!correctChoices.contains(choice)) {
            System.out.print("Error: You must enter a number between 1-6: \n");
            choice = scanner.next();
        }
        return choice;
    }

    public Song promptForSong(Collection<Song> songList) {
        String choice;
        Song userSong = null;
        while (userSong == null) {
            System.out.print("Please enter a valid song ID to play or 'B' to go back to Genres: ");
            choice =  scanner.next();
            if(choice.equalsIgnoreCase("b")){
                MusicPlayer.isFinished = true;
                break;
            }
            for (Song song : songList) {
                if (song.getId().equals(choice)) {
                    userSong = song;
                }
            }
        }
        return userSong;
    }

    //helper methods
    public Collection<Song> findUserChoice(String choiceNum) {
        Collection<Song> songList = null;
        switch (choiceNum) {
            case "1":
                songList = Catalog.findByGenre(Genre.POP);
                break;
            case "2":
                songList = Catalog.findByGenre(Genre.ROCK);
                break;
            case "3":
                songList = Catalog.findByGenre(Genre.HIP_HOP);
                break;
            case "4":
                songList = Catalog.findByGenre(Genre.COUNTRY);
                break;
            case "5":
                songList = Catalog.findByGenre(Genre.TV_TUNES);
                break;
            case "6":
                songList = Catalog.getSongs();
                break;
            default:
                System.out.print("Error: You must enter a number between 1 - 6. ");
        }
        return songList;
    }


}