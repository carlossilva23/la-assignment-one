package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Playlist> Library = new ArrayList<>();
	private MusicStore store;
	
	public LibraryModel() {
		Library = new ArrayList<>();
		Library.add(new Playlist("Singles"));
	}
	
	public void addSong(String song) {
		getPlaylist("Singles").addSong(store.getSong(song));
	}
	
	private Playlist getPlaylist(String playlist) {
		for (Playlist p : Library) {
			if (p instanceof Playlist && p.getName().equals(playlist)) {
				return p;
			}
		}
		return null;
	}
	
	public void addAlbum(String album) {
		Library.add(store.getAlbum(album));
	}
		
	public void printSongByTitle(String title) {
		Song song = getSongByTitle(title);
		if (song != null) {
			System.out.println(song);
		}
		else {
			System.out.println("Song not found.");
		}
	}
	
	public Song getSongByTitle(String title) {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (title.equals(s.getName())) {
					return s;
				} 
			}
		}
		return null;
	}
	
	public void printSongsByArtist(String artist) {
		boolean found = false;
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (artist.equals(s.getArtist())) {
					System.out.println(s);
					found = true;
				}
			}
		}
		if (!found) System.out.println("Artist not found.");
	}
	
	public void printAlbumByTitle(String title) {
		boolean found = false;
		for (Playlist p : Library) {
			if (p instanceof Album && p.getName().equals(title)) {
				p.print();
				found = true;
			}
		}
		if (!found) System.out.println("Album not found.");
	}
	
	public void printAlbumsByArtist(String artist) {
		boolean found = false;
		for (Playlist p : Library) {
			if (p instanceof Album && ((Album) p).getArtist().equals(artist)) {
				p.print();
				found = true;
			}
		}
		if (!found) System.out.println("Artist not found.");
	}
	
	public void printPlaylistByName(String playlist) {
		boolean found = false;
		for (Playlist p : Library) {
			if (p instanceof Playlist && p.getName().equals(playlist)) {
				p.print();
				found = true;
			}
		}
		if (!found) System.out.println("Playlist not found.");
	}
	
	public void printSongs() {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				System.out.println(s);
			}
		}
	}
	
	public void printArtists() {
		ArrayList<String> artists = new ArrayList<>();
		for (Playlist p : Library) {
			if (p instanceof Album) {
				if (!artists.contains(((Album) p).getArtist())) {
					artists.add(((Album) p).getArtist());
				}
			}
			if (p instanceof Playlist) {
				for (Song s : p.getSongs()) {
					if (!artists.contains(s.getArtist())) {
						artists.add(s.getArtist());
					}
				}
			}
		}
	}
	
	public void printAlbums() {
		for (Playlist p : Library) {
			if (p instanceof Album) {
				System.out.println(p.getName());
			}
		}
	}
	
	public void printPlaylists() {
		for (Playlist p : Library) {
			if (!(p instanceof Album)) {
				System.out.println(p.getName());
			}
		}
	}
	
	public void printFavorites() {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (s.getFavorite() == true) {
					System.out.println(s);
				}
			}
		}
	}
	
	public void createPlaylist(String name) {
		Library.add(new Playlist(name));
	}
	
	public void setFavorite(String song) {
		getSongByTitle(song).favorite(true);
	}
	
	public void rate(String song, int rating) {
		getSongByTitle(song).rate(rating);
	}
		
}
