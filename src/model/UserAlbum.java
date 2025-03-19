package model;

import java.util.Collection;

public class UserAlbum extends Album {
	public UserAlbum(String name, String artist) {
		super(name, artist);
	}
	
	@Override
	public Collection<Song> getSongs() {
		return songs.values();
	}
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" by ").append(getArtist()).append(" (").append(year).append(", ").append(genre).append(")");
        sb.append("\nSongs:");
        
        if (songs.isEmpty()) {
            sb.append("\n  (No songs added yet)");
        } else {
            for (Song song : songs.values()) {
                sb.append("\n  - ").append(song.getName());
            }
        }
        return sb.toString();
    }
}
