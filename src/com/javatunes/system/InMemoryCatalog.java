package com.javatunes.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCatalog implements Catalog {
    // FIELDS
    private Collection<Song> catalog = new ArrayList<>();
    private Path dataFilePath;// path to csv file

    //CONSTRUCTOR
    public InMemoryCatalog(String dataFilePath) throws IOException {
        this.dataFilePath = Path.of(dataFilePath);
        load();
    }

    private void load() throws IOException { //This method loads data from csv file and creates song catalog
        Files.lines(dataFilePath).forEach(line -> {
            String[] tokens = line.split(",");
            String id = tokens[0];
            String artist = tokens[1];
            String title = tokens[2];
            Genre genre = Genre.valueOf(tokens[3]);
            String url = tokens[4];
            catalog.add(new Song(id, title, artist, genre, url));
        });
    }

    //BUSINESS METHODS
    @Override
    public Collection<Song> findByGenre(Genre genre) {
        //declare a return value
        Collection<Song> result = catalog.stream()
                .filter(song -> song.getGenre().equals(genre))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Collection<Song> getSongs() { //Returns entire catalog.
        return Collections.unmodifiableCollection(catalog);
    }

    //ACCESSOR METHODS
    public Collection<Song> getCatalog() { //THIS IS FOR TESTING
        return catalog;
    }
}
