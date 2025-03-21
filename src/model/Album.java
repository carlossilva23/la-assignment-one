package model;

import java.util.Collections;
import java.util.List;

public class Album {
	private final String name;
    private final String artist;
    private String genre;
    private int year;
    private List<Song> songs;
    
    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = Collections.emptyList();
    }
    
    public void setGenre(String genre) {
    	this.genre = genre;
    }
    
    public void setYear(int year) {
    	this.year = year;
    }
    
    public void setSongs(List<Song> songs) {
    	this.songs = Collections.unmodifiableList(songs);
    }
    
    public String getName() {
    	return name;
    }
    
    public String getArtist() {
    	return artist;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public int getYear() {
    	return year;
    }
    
    public List<Song> getSongs() {
    	return songs;
    }
    
    @Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(name).append(" by ");
	    sb.append(artist).append(" (");
	    sb.append(year).append(", ");
	    sb.append(genre).append(")\nSongs:");

	    for (Song song : songs) {
	        sb.append("\n - ").append(song.getName());
	    }

	    return sb.toString();
	}
}
