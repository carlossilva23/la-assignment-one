package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserAlbum {
	private final Album album;
	private final List<Song> userSongs;

	public UserAlbum(Album album) {
		this.album = album;
		this.userSongs = new ArrayList<>();
		for (int i = 0; i < album.getSongs().size(); i++) {
			userSongs.add(null);
		} 
	}

	public Album getAlbum() {
		return album;
	}

	public void addSong(Song song) {
		int index = album.getSongs().indexOf(song);
		if (index != -1 && userSongs.get(index) == null) {
			userSongs.set(index, song);
		}
	}

	public List<Song> getSongs() {
		return userSongs.stream()
				.filter(Objects::nonNull)
				.toList();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(album.getName()).append(" by ");
		sb.append(album.getArtist()).append(" (");
		sb.append(album.getYear()).append(", ");
		sb.append(album.getGenre()).append(")\nSongs:");

		for (Song song : userSongs) {
			if (song != null) {
				sb.append("\n - ").append(song.getName());
			} else {
				continue;
			}
		}

		return sb.toString();
	}

}
