package com.javatunes.controller;


import com.apps.util.Prompter;
import com.javatunes.system.Catalog;
import com.javatunes.system.Genre;
import com.javatunes.system.InMemoryCatalog;
import com.javatunes.system.Song;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.*;


public class MusicPlayerTest {

    MusicPlayer player;
    private Scanner scanner;
    private Prompter prompter;

    @After
    public void tearDown() {
        scanner.close();
    }
    @Before
    public void setUp() throws IOException {
        player = new MusicPlayer(new Prompter(new Scanner(System.in)));
    }

    @Test
    public void findUserChoice_shouldReturnAllSongs_whenCategoryIsValid() {
        assertEquals(2,player.findUserChoice("1").size()); // 1 is pop which has 2 songs
        assertEquals(3,player.findUserChoice("2").size()); // 2 is rock which has 3 songs
        assertEquals(2,player.findUserChoice("3").size()); // 3 is hip hop which has 2 songs
        assertEquals(2,player.findUserChoice("4").size()); // 4 is country which has 2 songs
        assertEquals(3,player.findUserChoice("5").size()); //5 is tv tunes which has 3 songs
        assertEquals(12,player.findUserChoice("6").size()); // 6 is all, which has 12 songs
    }

    @Test
    public void findUserChoice_shouldReturnSameGenreSongs_whenCategoryIsAGenre() {
        Collection<Song> songs = player.findUserChoice("1"); //this is pop which has 2 songs
        assertEquals(2, songs.size());
        for(Song song: songs){
            assertEquals(Genre.POP, song.getGenre()); // this is checking that all songs returned have a genre of pop
        }
    }

    @Test
    public void findUserChoice_shouldReturnNull_whenCategoryIsInvalid() {
        assertEquals(null,player.findUserChoice("NOT A CATEGORY"));
    }

    @Test
    public void getGenreCount_shouldReturnCorrectGenreCount(){
        assertEquals(5,player.getGenreCount());
    }

    @Test
    public void getRegex_shouldReturnDoubleDigitPattern_whenSongListGreaterThanTen() {
        player.setSongList(player.findUserChoice("6")); //this is setting songList to have a collection of 12 songs
        assertEquals("0?[bB1-9]|1[bB012]", player.getRegex()); //regex pattern returned should allow numbers 1-12
    }

    @Test
    public void getRegex_shouldReturn_singleDigitPattern_whenSongListLessThanTen() throws IOException {
        player.setSongList(player.findUserChoice("2"));//findUserChoice with option "2" will set the songList to the rock genre
        assertEquals("[bB1-3]", player.getRegex());// and the regex pattern returned should be single digit
    }

    @Test
    public void run_shouldReturnIsOpenTrue_whenSongIsPlaying() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        scanner = new Scanner(new File("data-tests/promptForSong_shouldReturnSetFinishedTrue_whenInputIsB.txt"));
        prompter = new Prompter(scanner);
        MusicPlayer musicplayer = new MusicPlayer(prompter);
//        musicplayer.setSongList(player.findUserChoice("2"));
        musicplayer.start();
        Clip clip = musicplayer.getControls().getClip();
//        musicplayer.promptForSong(new Scanner(new File("data-tests/promptForSong_shouldReturnSetFinishedTrue_whenInputIsB.txt")));
    }
}