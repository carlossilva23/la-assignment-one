package model;

public class Song {
	private String name;
	private String artist;
	private String album;
	
	public Song(String name, String artist, String album) {
		this.name = name;
		this.artist = artist;
		this.album = album;
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected String getAlbum() {
		return this.album;
	}
	
	protected String getArtist() {
		return this.artist;
	}
	
	@Override
	public String toString() {
		return "Title: " + name + ", Album: " + album + ", Artist: " + artist;
	}
	
}