package com.javatunes.system;

import java.io.IOException;
import java.util.Collection;

public interface Catalog {
    public Collection<Song> findByGenre(Genre genre);

    public Collection<Song> getSongs();

    //static factory method
    //var args allow us to input different catalogs in the future
    public static Catalog get(String... url) throws IOException {
        return new InMemoryCatalog(url[0]);
    }
}
