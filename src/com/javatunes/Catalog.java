package com.javatunes;


import java.util.*;

public class Catalog {
    // in-memory catalog of Televisions
    private static final Collection<Song> catalog = new ArrayList<>(2);

    // prevent direct instantiation, this is an all-static class
    private Catalog() {
    }

    /**
     * Searches catalog by brand, and returns a collection of matching Televisions.
     * A no-matches result should be an empty collection (not null).
     */
    public static Collection<Song> findByGenre(Genre genre) {
        //declare a return value
        Collection<Song> result = new ArrayList<>();
        //go through the catalog and check each tv's brand to the brand given
        for (Song currentSong : catalog) {
            if (currentSong.getGenre().equals(genre)) {
                result.add(currentSong);
            }
        }
        return result;
    }

    /**
     * Returns entire catalog.
     * NOTE: returning a direct reference to it has consequences!
     * A client can manipulate it, since it has a direct reference to it.
     * Sometimes this is okay, but not here.
     * <p>
     * DONE: change this to return a read-only view of the catalog.
     * <p>
     * You should explore the Javadoc for the java.util.Collections *class*.
     * This is an all-static utility class, not the java.util.Collection interface.
     */
    public static Collection<Song> getSongs() {
        return Collections.unmodifiableCollection(catalog);
    }

    /*
     * Loads catalog.
     * Static initializers execute when the class is loaded into the JVM.
     */
    static {

        catalog.add(new Song("0", "Toto", "Africa", Genre.POP, "https://www.ee.columbia.edu/~dpwe/sounds/music/africa-toto.wav"));
        catalog.add(new Song("1", "Madonna", "Nothing Really Matters", Genre.POP, "https://javatunes1.netlify.app/Madonna-Nothing-Really-Matters_1.wav"));
        catalog.add(new Song("2", "Christina Aguliera", "Fighter", Genre.POP, "https://javatunes4.netlify.app/Christina_Aguliera_Fighter.wav"));
        catalog.add(new Song("3", "Queen", "Bohemain Rapsody", Genre.ROCK, "https://javatunes5.netlify.app/Queen-Queen-Bohemian-Rapsody.wav"));
        catalog.add(new Song("4", "Metallica", "Enter Sandman", Genre.ROCK, "https://javatunes2.netlify.app/Metallica_Enter_Sandman.wav"));
        catalog.add(new Song("5", "Spinal Tap", "Stonehenge", Genre.ROCK, "https://javatunes5.netlify.app/Spinal_Tap_Stonehenge.wav"));
        catalog.add(new Song("6", "Willie Nelson", "On The Road Again", Genre.COUNTRY, "https://javatunes3.netlify.app/Willie_Nelson_On_The_Road_Again_1.wav"));
        catalog.add(new Song("7", "Dolly Parton", "9 to 5", Genre.COUNTRY, "https://javatunes3.netlify.app/Willie_Nelson_On_The_Road_Again_1.wav"));
        catalog.add(new Song("8", "Will Smith", "Summertime", Genre.HIP_HOP, "https://javatunes4.netlify.app/DJ_Jazzy_Jeff__The_Fresh_Prince_Summertime.wav"));
        catalog.add(new Song("9", "Will Smith", "Fresh Prince of Bel-Air", Genre.TV_TUNES, "https://javatunes1.netlify.app/The_Fresh_prince_Of_Bel_Air_Theme_Song_full.wav"));
        catalog.add(new Song("10", "The Office", "The Office", Genre.TV_TUNES, ""));
        catalog.add(new Song("11", "Star Wars", "Star Wars Theme", Genre.TV_TUNES, "https://www2.cs.uic.edu/~i101/SoundFiles/StarWars60.wav"));
    }
}
