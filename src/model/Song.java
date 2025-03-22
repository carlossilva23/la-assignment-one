package model;

import java.util.Objects;

public class Song {
	private String name;
	private String artist;
	private Album album;
	private Rating rating;
	private boolean favorite;
	private int playCount;

	public Song(String name, String artist) {
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

	public Rating getRating() {
		return rating;
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
		return name + " by " + artist + ", " + album.getName();
	}
}