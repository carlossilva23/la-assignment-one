package model;

public class Album extends Playlist {
	private String artist;
	private String genre;
	private int year;
	
	public Album(String name, String artist) {
		super(name);
		this.artist = artist;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	public String getArtist() {
		return this.artist;
	}

	public void print() {
		System.out.println(super.getName() + ":");
		for (Song song : super.getSongs()) {
			System.out.println(song.getName());
		}
	}

}
