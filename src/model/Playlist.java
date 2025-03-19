package model;

import java.util.HashMap;
import java.util.Map;

public class Playlist {
    private String name;
    private Map<String, Song> songs;
    
    public Playlist(String name) {
        this.name = name;
        this.songs = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addSong(Song song) {
        if (song != null) {
            songs.put(song.getName(), song);
        }
    }
    
    public void removeSong(String songName) {
        songs.remove(songName);
    }
    
    public Map<String, Song> getSongs() {
        return songs;
    }
    
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":");
        for (Song song : songs.values()) {
            sb.append("\n  ").append(song.toString());
        }
        return sb.toString();
    }
}
