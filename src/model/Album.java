package model;

public class Album extends Playlist {
	private String artist;
	private String genre;
	private int year;
	
	public Album(String name, String artist, String genre, int year) {
		super(name);
		this.artist = artist;
		this.genre = genre;
		this.year = year;
	}

	public String getArtist() {
		return artist;
	}

	public void printAlbum() {
		System.out.println(super.getName() + ":");
		for (Song song : super.getSongs()) {
			System.out.println(song.getName());
		}
	}

}
