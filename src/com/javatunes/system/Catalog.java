package com.javatunes.system;


import java.util.*;

public class Catalog {
    // FIELDS
    private Collection<Song> catalog = new ArrayList<>();
    private Path dataFilePath;

    //CONSTRUCTOR
    public Catalog(String dataFilePath) throws IOException {
        setDataFilePath(Path.of(dataFilePath));
        setCatalog(load());
    }

    public Collection<Song> load() throws NullPointerException, IOException {
        Files.lines(dataFilePath).forEach(line -> {
            String[] tokens = line.split(",");

            String id =tokens[0];
            String artist = tokens[1];
            String title =tokens[2];
            Genre genre = Genre.valueOf(tokens[3]);
            String url = tokens[4];

            catalog.add(new Song(id,title, artist, genre, url));
        });
        return catalog;
    }

    /**
     * BUSINESS METHODS
     */
    public static Collection<Song> findByGenre(Genre genre) {
        //declare a return value
        Collection<Song> result = catalog.stream()
                .filter(song -> song.getGenre().equals(genre))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    /**
     * Returns entire catalog.
     */
    public static Collection<Song> getSongs() {
        return Collections.unmodifiableCollection(catalog);
    }

    //ACCESSOR METHODS
    public Collection<Song> getCatalog() {
        return catalog;
    }

    public void setCatalog(Collection<Song> catalog) {
        this.catalog = catalog;
    }

    public Path getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(Path dataFilePath) {
        this.dataFilePath = dataFilePath;
    }
}
