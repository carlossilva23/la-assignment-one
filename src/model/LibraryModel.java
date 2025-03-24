package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set<Song> userSongs;
	private Map<Album, UserAlbum> userAlbums;
	private Map<String, Playlist> userPlaylists;
	private Map<String, Playlist> onlyGenrePlaylists;
	private Set<Song> favoriteSongs;
	private LinkedList<Song> recentlyPlayed;
	private Map<Song, Integer> frequentPlayedSongs;
	private List<Song> topRated;

	public LibraryModel() {
		this.userSongs = new HashSet<>();
		this.userAlbums = new HashMap<>();
		this.userPlaylists = new HashMap<>();
		this.onlyGenrePlaylists = new HashMap<>();
		this.favoriteSongs = new HashSet<>();
		this.recentlyPlayed = new LinkedList<>();
		this.frequentPlayedSongs = new HashMap<>();
		this.topRated = new ArrayList<>();
	}

	// Searches Library for Song By Title
	public ArrayList<Song> searchSongsByTitle(String title) {
		ArrayList<Song> matches = new ArrayList<>();
		for (Song songs : userSongs) {
			if (songs.getName().equalsIgnoreCase(title)) {
				matches.add(songs);
			}
		}
		return matches;
	}

	// Searches Library for Song By Artist
	public ArrayList<Song> searchSongByArtist(String artist) {
		ArrayList<Song> matches = new ArrayList<>();
		for (Song songs : userSongs) {
			if (songs.getArtist().equalsIgnoreCase(artist)) {
				matches.add(songs);
			}
		}
		return matches;
	}

	// Searches Library for Song By Genre
	public ArrayList<Song> searchSongsByGenre(String genre) {
		ArrayList<Song> matches = new ArrayList<>();
		for (Song songs : userSongs) {
			if (songs.getGenre().equalsIgnoreCase(genre)) {
				matches.add(songs);
			}
		}
		return matches;
	}

	// Searches Library for Album By Title
	public ArrayList<Album> searchAlbumByTitle(String title) {
		ArrayList<Album> matches = new ArrayList<>();
		for (Album albums : userAlbums.keySet()) {
			if (albums.getName().equalsIgnoreCase(title)) {
				matches.add(albums);
			}
		}
		return matches;
	}

	// Search Library for Album By Artist
	public ArrayList<Album> searchAlbumByArtist(String artist) {
		ArrayList<Album> matches = new ArrayList<>();
		for (Album albums : userAlbums.keySet()) {
			if (albums.getArtist().equalsIgnoreCase(artist)) {
				matches.add(albums);
			}
		}
		return matches;
	}

	// Search Playlists By Name
	public Playlist searchPlaylistsByName(String name) {
		for (Playlist playlistsNames : userPlaylists.values()) {
			if (playlistsNames.getName().equalsIgnoreCase(name)) {
				return playlistsNames;
			}
		}
		return null;
	}

	// Adds Song to Library
	public void addSong(Song song) {
		for (Map.Entry<Album, UserAlbum> entry : userAlbums.entrySet()) {
			Album album = entry.getKey();
			UserAlbum userAlbum = entry.getValue();

			if (song.getAlbum().equals(album)) {
				userAlbum.addSong(song);
				return;
			}
		}

		Album songAlbum = song.getAlbum();
		UserAlbum newUserAlbum = new UserAlbum(songAlbum);
		newUserAlbum.addSong(song);

		userAlbums.put(songAlbum, newUserAlbum);
		checkAndCreateGenrePlaylist();
	}

	// Add Album to Library
	public void addAlbum(Album album) {
		if (!userAlbums.containsKey(album)) {
			UserAlbum userAlbum = new UserAlbum(album);
			userAlbums.put(album, userAlbum);
		} else {
			UserAlbum userAlbum = userAlbums.get(album);

			if (userAlbum.getSongs().size() != album.getSongs().size()) {
				for (Song song : album.getSongs()) {
					userAlbum.addSong(song);
				}
			}
		}
		checkAndCreateGenrePlaylist();
	}

	// Remove Song from Library
	public void removeSong(Song song) {
		for (UserAlbum userAlbum : userAlbums.values()) {
			if (userAlbum.getSongs().contains(song)) {
				userAlbum.getSongs().remove(song);
			}
		}
		for (Playlist playlist : userPlaylists.values()) {
			if (playlist.getSongs().contains(song)) {
				playlist.getSongs().remove(song);
			}
		}

		if (favoriteSongs.contains(song)) {
			favoriteSongs.remove(song);
		}
		if (topRated.contains(song)) {
			topRated.remove(song);
		}
		frequentPlayedSongs.remove(song);
		checkAndCreateGenrePlaylist();
	}

	// Remove Album from Library
	public void removeAlbum(Album album) {
		if (userAlbums.containsKey(album)) {
			UserAlbum userAlbum = userAlbums.get(album);

			for (Playlist playlist : userPlaylists.values()) {
				playlist.getSongs().removeAll(userAlbum.getSongs());
			}
			for (Song song : userAlbum.getSongs()) {
				frequentPlayedSongs.remove(song);
				favoriteSongs.remove(song);
				topRated.remove(song);
			}
			userAlbums.remove(album);
		}
		checkAndCreateGenrePlaylist();
	}

	// List All Content (Songs/Albums) in Library
	public ArrayList<UserAlbum> listAllContent() {
		ArrayList<UserAlbum> allContent = new ArrayList<>();
		for (UserAlbum albums : userAlbums.values()) {
			allContent.add(albums);
		}
		return allContent;
	}

	// List All Artists in Library
	public ArrayList<String> listAllArtists() {
		ArrayList<String> allArtists = new ArrayList<>();
		for (Song songs : userSongs) {
			if (!allArtists.contains(songs.getArtist())) {
				allArtists.add(songs.getArtist());
			}
		}
		return allArtists;
	}

	// List All PLaylists in Library
	public ArrayList<Playlist> listAllPlaylists() {
		ArrayList<Playlist> allPlaylists = new ArrayList<>();
		for (Playlist playlists : userPlaylists.values()) {
			allPlaylists.add(playlists);
		}
		return allPlaylists;
	}

	// Lists All Songs Sorted By Title
	public ArrayList<Song> listSortedSongsByTitle() {
		ArrayList<Song> sortedSongs = new ArrayList<>();
		for (UserAlbum userAlbum : userAlbums.values()) {
			List<Song> userSongs = userAlbum.getSongs();
			sortedSongs.addAll(userSongs);
		}

		Collections.sort(sortedSongs, Song.sortByTitle);
		return sortedSongs;
	}

	// Lists All Songs Sorted By Artist
	public ArrayList<Song> listSortedSongsByArtist() {
		ArrayList<Song> sortedSongs = new ArrayList<>();
		for (UserAlbum userAlbum : userAlbums.values()) {
			List<Song> userSongs = userAlbum.getSongs();
			sortedSongs.addAll(userSongs);
		}

		Collections.sort(sortedSongs, Song.sortByArtist);
		return sortedSongs;
	}

	// Lists All Songs Sorted By Rating
	public ArrayList<Song> listSortedSongsByRating() {
		ArrayList<Song> sortedSongs = new ArrayList<>();
		for (UserAlbum userAlbum : userAlbums.values()) {
			List<Song> userSongs = userAlbum.getSongs();
			for (Song songs : userSongs) {
				if (songs.getRating() != Rating.noRating) {
					sortedSongs.add(songs);
				}
			}
		}
		Collections.sort(sortedSongs, Song.sortByRating);
		return sortedSongs;
	}

	// Create Playlist
	public void createPlaylist(String name) {
		Playlist newPlaylist = new Playlist(name);
		userPlaylists.put(name, newPlaylist);
	}

	// Add Song to Playlist
	public void addSongToPlaylist(Playlist playlist, Song song) {
		for (Playlist playlists : userPlaylists.values()) {
			if (playlists.equals(playlist)) {
				playlists.addSong(song);
			}
		}
	}

	// Remove Song from Playlist
	public void removeSongFromPlaylist(Playlist playlist, Song song) {
		for (Playlist playlists : userPlaylists.values()) {
			if (playlists.equals(playlist)) {
				playlists.removeSong(song);
			}
		}
	}

	// Mark a Song as Favorite
	public void markSongFavorite(Song song) {
		for (Song songs : userSongs) {
			if (songs.equals(song)) {
				songs.setFavorite(true);
				favoriteSongs.add(songs);
				songs.setRating(Rating.five);
			}
		}
	}

	// Rate a Song
	public void rateSong(Song song, Rating rating) {
		for (Song songs : userSongs) {
			if (songs.equals(song)) {
				songs.setRating(rating);
			}
		}
	}

	// Simulate Playing a Song
	public void playSong(Song song) {
		for (Song songs : userSongs) {
			if (songs.equals(song)) {
				song.incrementPlayCount();
				recentlyPlayed.addFirst(song);
				if (recentlyPlayed.size() > 10) {
					recentlyPlayed.removeLast();
				}
				// getOrDefault is a method in Map's Java interface that if the songs exists in
				// the mostFrequent Map it returns the Song object, else it returns 0 by default
				frequentPlayedSongs.put(song, frequentPlayedSongs.getOrDefault(song, 0) + 1);
			}
		}
	}

	// Retrieve Most Recent Songs
	public List<Song> getRecentlyPlayed() {
		return List.copyOf(recentlyPlayed);
	}

	// Retrieve Most Frequent Songs
	public List<Song> getFrequentPlayed() {
		return frequentPlayedSongs.keySet()
				// have to stream data in order to sort it
				.stream()
				// reversed to get highest first
				.sorted(Song.sortByPlayCount.reversed())
				// only want the top 10 songs
				.limit(10)
				// group them into a list
				.collect(Collectors.toList());
	}

	// Shuffles Songs in Library
	public List<Song> shuffleLibrary() {
		List<Song> allSongs = new ArrayList<>();
		for (UserAlbum userAlbum : userAlbums.values()) {
			allSongs.addAll(userAlbum.getSongs());
		}

		List<Song> shuffledSongs = new ArrayList<>();
		Collections.shuffle(shuffledSongs);
		return shuffledSongs;
	}

	// Shuffle Songs in Playlist
	public List<Song> shufflePlaylist(Playlist playlist) {
		List<Song> shuffledSongs = new ArrayList<>();
		userPlaylists.get(playlist.getName());
		for (Song songs : playlist.getSongs()) {
			shuffledSongs.add(songs);
		}
		Collections.shuffle(shuffledSongs);
		return shuffledSongs;

	}

	// Retrieve Favorite Songs
	public Set<Song> getFavorites() {
		return favoriteSongs;
	}

	// Create All Genre Playlists and Update Lists
	public void checkAndCreateGenrePlaylist() {
		Map<String, List<Song>> genreToSongs = new HashMap<>();
		for (UserAlbum userAlbum : userAlbums.values()) {
			for (Song song : userAlbum.getSongs()) {
				String genre = song.getGenre();
				genreToSongs.putIfAbsent(genre, new ArrayList<>());
				genreToSongs.get(genre).add(song);
			}
		}

		for (Map.Entry<String, List<Song>> entry : genreToSongs.entrySet()) {
			String genre = entry.getKey();
			List<Song> songsOfGenre = entry.getValue();
			if (songsOfGenre.size() >= 10) {
				Playlist genrePlaylist = onlyGenrePlaylists.get(genre + "Playlist");
				if (genrePlaylist == null) {
					genrePlaylist = new Playlist(genre + "Playlist:");
					onlyGenrePlaylists.put(genre + "Playlist", genrePlaylist);
				}
				genrePlaylist.getSongs().addAll(songsOfGenre);
			}
		}
	}

	// Create Top Rated Song List
	public void createTopRated() {
		for (UserAlbum userAlbum : userAlbums.values()) {
			for (Song song : userAlbum.getSongs()) {
				if (song.getRating() == Rating.four || song.getRating() == Rating.five) {
					topRated.add(song);
				}
			}
		}
	}

	// Get Top Rated Songs
	public List<Song> getTopRated() {
		return topRated;
	}

}
