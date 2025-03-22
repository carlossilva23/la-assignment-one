package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
	private String name;
	private List<Song> songs;

	public Playlist(String name) {
		this.name = name;
		this.songs = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addSong(Song song) {
		if (songs.contains(song)) {
			songs.add(song);
		}
	}

	public void removeSong(Song song) {
		songs.remove(song);
	}

	public List<Song> getSongs() {
		return new ArrayList<>(songs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Playlist playlist = (Playlist) obj;
		return name.equals(playlist.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(":\n");
		for (Song song : songs) {
			sb.append(" - ").append(song.toString());
		}
		return sb.toString();
	}
}
