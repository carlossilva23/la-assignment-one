package model;

import java.util.Comparator;
import java.util.Objects;

public class Song {
	private String name;
	private String artist;
	private Album album;
	private Rating rating;
	private boolean favorite;
	private int playCount;

	public Song(String name, String artist, Album album) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.rating = Rating.noRating;
		this.favorite = false;
		this.playCount = 0;
	}

	public String getName() {
		return this.name;
	}

	public String getArtist() {
		return this.artist;
	}

	public Album getAlbum() {
		return album;
	}

	public Rating getRating() {
		return rating;
	}

	public String getGenre() {
		return album.getGenre();
	}

	public int getPlayCount() {
		return playCount;
	}
	
	// Helper Method for Tests
	public boolean getFavorite() {
		return favorite;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public void incrementPlayCount() {
		this.playCount++;
	}

	public static final Comparator<Song> sortByTitle = new Comparator<Song>() {
		@Override
		public int compare(Song song1, Song song2) {
			return song1.getName().compareTo(song2.getName());
		}
	};

	public static final Comparator<Song> sortByArtist = new Comparator<Song>() {
		@Override
		public int compare(Song song1, Song song2) {
			return song1.getArtist().compareTo(song2.getArtist());
		}
	};

	public static final Comparator<Song> sortByRating = new Comparator<Song>() {
		@Override
		public int compare(Song song1, Song song2) {
			return song1.getRating().compareTo(song2.getRating());
		}
	};

	public static final Comparator<Song> sortByPlayCount = new Comparator<Song>() {
		@Override
		public int compare(Song song1, Song song2) {
			return Integer.compare(song1.getPlayCount(), song2.getPlayCount());
		}
	};

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Song otherSong = (Song) obj;
		return this.name.equals(otherSong.name) && this.artist.equals(otherSong.artist);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, artist);
	}

	@Override
	public String toString() {
		return name + " by " + artist + ", " + album.getName() + "\nRating: " + rating + "Number of Times Played: " + playCount;
	}
}