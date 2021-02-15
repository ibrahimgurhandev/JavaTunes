package com.javatunes;

public class Song {
    //FIELDS
    String id;
    String artist;
    String title;
    Genre genre;
    String url;

    //CONSTRUCTOR
    public Song(String id, String artist, String title, Genre genre, String url){
        setId(id);
        setArtist(artist);
        setTitle(title);
        setGenre(genre);
        setUrl(url);
    }

    //ACCESSOR METHODS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
//        return "Song [ ID= " + getId() + ", artist= "+ getArtist() + ", title= '" + getTitle() + "', genre= " + getGenre() + "] ";
        return " Enter " + getId() + " to play " + "'"+ getTitle()+ "'"+  " by " + "'"+getArtist()+ "'";
    }
}