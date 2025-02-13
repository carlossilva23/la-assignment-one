package model;

public class Song {
	private String name;
	private String artist;
	private String album;
	private int rating;
	private boolean favorite;
	
	public Song(String name, String artist, String album) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.rating = 0;
		this.favorite = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void rate(int rating) {
		this.rating = rating;
		if (rating == 5) {
			favorite = true;
		}
	}
	
	public void favorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	public String toString() {
		return "Title: " + name + ", Album: " + album + ", Artist: " + artist;
	}
	
}
