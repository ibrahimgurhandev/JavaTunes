package com.javatunes.system;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public interface Catalog {
    //

    public Collection<Song> findByGenre(Genre genre);
    public Collection<Song> getSongs();


    //static factory method
    public static Catalog get() throws IOException {
        return new InMemoryCatalog("data/song-data.csv");
    }

}
