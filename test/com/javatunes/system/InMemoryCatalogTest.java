package com.javatunes.system;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
   
public class InMemoryCatalogTest {
    //FIXTURES
    Catalog catalog;
    
    @Before
    public void setUp() throws IOException {
        catalog = Catalog.get();
    }

    @Test
    public void load_shouldReturnCorrectSize_whenInstantiated()throws IOException{
        InMemoryCatalog catalog2 = new InMemoryCatalog("data/song-data.csv");
        assertEquals(12, catalog2.getCatalog().size());
    }

    @Test
    public void findByGenre_shouldReturnACollection_whenMatchFound() {
        Collection<Song> songs = catalog.findByGenre(Genre.POP);
        assertNotNull(songs); //verify is not null
        assertEquals(2, songs.size()); //should only have two songs -- madonna &christina
        for(Song song: songs){
            assertEquals(Genre.POP, song.getGenre());
        }
    }
    @Test
    public void getSongs_shouldReturnAllSongs() {
        Collection<Song> songs = catalog.getSongs();
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
