package model;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    private String name;
    private List<Song> songs;
    
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addSong(Song song) {
        if (songs.contains(song)) {
            songs.add(song);
        }
    }
    
    public void removeSong(Song song) {
        songs.remove(song);
    }
    
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }
    
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        for (Song song : songs) {
            sb.append(" - ").append(song.toString());
        }
        return sb.toString();
    }
}
