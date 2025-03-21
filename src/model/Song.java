package model;

public class Song {
	private String name;
	private String artist;
	private Album album;
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected String getArtist() {
		return this.artist;
	}
	
	@Override
	public String toString() {
		return "Title: " + name + ", Album: " + album + ", Artist: " + artist;
	}
	
}