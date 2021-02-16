package com.javatunes.system;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
   /*
public class CatalogTest {

     * TEST FOR CATALOG METHODS
     */
    /*
    @Test
    public void findByGenre_shouldReturnACollection_whenMatchFound() {
        Collection<Song> songs = Catalog.findByGenre(Genre.POP);
        assertNotNull(songs); //verify is not null
        assertEquals(1, songs.size()); //should only have one Song
        for(Song song: songs){
            assertEquals(Genre.POP, song.getGenre());
        }
    }
    @Test
    public void getSongs_shouldReturnAllSongs() {
        Collection<Song> songs = Catalog.getSongs();
        assertNotNull(songs); //verify is not null
        assertEquals(2, songs.size()); //should only have one Song
    }

    @Test(expected = UnsupportedOperationException.class)
    public void geSong_shouldThrowUnsupportedOperationException_whenTryingToModify() {
        Collection<Song> tvs = Catalog.getSongs();
        //try to modify the collection
        tvs.add(new Song("-1", "NOT AN ARTIST", "NOT A TITLE", Genre.POP, "NOT A REAL URL"));
    }
}
*/
