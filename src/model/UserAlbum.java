package model;

import java.util.ArrayList;
import java.util.List;

public class UserAlbum {
	private final Album album;
	private final List<Song> userSongs;
	
	public UserAlbum(Album album) {
		this.album = album;
		this.userSongs = new ArrayList<>();
	}
	
	public void addSong(Song song) {
		int index = album.getSongs().indexOf(song);
		if (index != -1 && !userSongs.contains(song)) { 
			userSongs.add(index, song);
		}
	}
	
	public List<Song> getSongs() {
		return List.copyOf(userSongs);
	}
	  
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(album.getName()).append(" by ");
	    sb.append(album.getArtist()).append(" (");
	    sb.append(album.getYear()).append(", ");
	    sb.append(album.getGenre()).append(")\nSongs:");

	    for (Song song : userSongs) {
	        sb.append("\n - ").append(song.getName());
	    }

	    return sb.toString();
	}

}
