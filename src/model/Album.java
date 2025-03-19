package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Album {
	private String name;
    private String artist;
    protected String genre;
    protected int year;
    protected final Map<String, Song> songs;
    
    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new LinkedHashMap<>();
    }
    
    public String getName() {
    	return name;
    }
    
    public String getArtist() {
    	return artist;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getGenre() {
    	return genre;
    }
  
    public void addSong(Song song) {
    	songs.put(song.getName(), song);
    }
    
    public Collection<Song> getSongs() {
    	return songs.values();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" by ").append(artist).append(" (").append(year).append(", ").append(genre).append(")");
        sb.append("\nSongs:");
        for (Song song : songs.values()) {
            sb.append("\n  - ").append(song.getName());
        }
        return sb.toString();
    }
}
