package model;

public class Song {
	String name;
	String artist;
	int rating;
	boolean favorite;
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
		this.rating = 0;
		this.favorite = false;
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
	
}
