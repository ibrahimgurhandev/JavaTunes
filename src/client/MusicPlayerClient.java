package client;

import com.javatunes.MusicPlayer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MusicPlayerClient {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //create an instance of music player
        MusicPlayer player = new MusicPlayer();

        //run it
        player.start();

    }
}