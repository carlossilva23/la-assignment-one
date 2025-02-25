package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Playlist> Library = new ArrayList<>();
	
	public LibraryModel() {
		Library = new ArrayList<>();
		Library.add(new Playlist("Singles"));
	}
	
	public void addSong(Song song) {

	}
	
	public void addAlbum(Album album) {

	}
		
	public Song searchSongByTitle(String title) {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (title.equals(s.getName())) {
					return s;
				} 
			}
		}
		return null;
	}
	
	public Song searchSongByArtist(String artist) {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (artist.equals(s.getArtist())) {
					return s;
				}
			}
		}
		return null;
	}
	
	public Playlist searchAlbumByTitle(String title) {
		for (Playlist p : Library) {
			if (p instanceof Album && p.getName() .equals(title)) {
				return p;
			}
		}
		return null;
	}
	
	public Playlist searchAlbumByArtist(String artist) {
		for (Playlist p : Library) {
			if (p instanceof Album) {
				Album album = (Album) p;
				if (album.getArtist().equals(artist)) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Playlist searchPlaylist(String playlist) {
		for (Playlist p : Library) {
			if (p.getName().equals(playlist)) {
				return p;
			}
		}
		return null;
	}
	
	public void getSongs() {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				
			}
		}
	}
	
	public void getArtists() {
		
	}
	
	public Playlist getAlbums() {
		for (Playlist p : Library) {
			if (p instanceof Album) {
				return p;
			}
		}
		return null;
	}
	
	public Playlist getPlaylists() {
		for (Playlist p : Library) {
			if (!(p instanceof Album)) {
				return p;
			}
		}
		return null;
	}
	
	public Song getFavorites() {
		for (Playlist p : Library) {
			for (Song s : p.getSongs()) {
				if (s.getFavorite() == true) {
					return s;
				}
			}
		}
		return null;
	}
	
	public void createPlaylist(String name) {
		Library.add(new Playlist(name));
	}
	
	public void setFavorite(String song) {
		Song s = searchSongByTitle(song);
		s.favorite(true);
	}
	
	public void rate(String song, int rating) {
		Song s = searchSongByTitle(song);
		s.rate(rating);
	}
		
}
