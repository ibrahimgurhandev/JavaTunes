package com.javatunes.system;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog {
    // FIELDS
    private Collection<Song> catalog = new ArrayList<>();
    private Path dataFilePath;

    //CONSTRUCTOR
    public Catalog(String dataFilePath) throws IOException {
        this.dataFilePath = Path.of(dataFilePath);
        load();
    }

    public void load() throws NullPointerException, IOException {
        Files.lines(dataFilePath).forEach(line -> {
            String[] tokens = line.split(",");
            String id =tokens[0];
            String artist = tokens[1];
            String title =tokens[2];
            Genre genre = Genre.valueOf(tokens[3]);
            String url = tokens[4];
            catalog.add(new Song(id,title, artist, genre, url));
        });
    }

    /**
     * BUSINESS METHODS
     */

    public Collection<Song> findByGenre(Genre genre) {
        //declare a return value
        Collection<Song> result = catalog.stream()
                .filter(song -> song.getGenre().equals(genre))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
    /**
     * Returns entire catalog.
     */
    public Collection<Song> getSongs() {
        return Collections.unmodifiableCollection(catalog);
    }
}
