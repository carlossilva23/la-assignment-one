package model;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;
    
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addSong(Song song) {
        if (song != null) {
            songs.add(song);
        }
    }
    
    public void removeSong(String songName) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getName().equalsIgnoreCase(songName)) {
                songs.remove(i);
                break;
            }
        }
    }
    
    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":");
        for (Song song : songs) {
            sb.append("\n  ").append(song.toString());
        }
        return sb.toString();
    }
}
