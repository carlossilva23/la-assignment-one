package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LibraryModel {
	private Set<Song> userSongs;
	private Map<Album, UserAlbum> userAlbums;
	private Map<String, Playlist> userPlaylists;
	private Set<Song> favoriteSongs;
	private List<Song> recentlyPlayed;
	private Map<Song, Integer> frequentPlayedSongs;
	private List<Song> topRated;

	public LibraryModel() {
		this.userSongs = new HashSet<>();
		this.userAlbums = new HashMap<>();
		this.userPlaylists = new HashMap<>();
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
				songs.incrementPlayCount();
				updateMostRecentlyPlayed();
				updateMostFrequentlyPlayed();
			}
		}
	}

	// Update 10 Most Played Songs
	public void updateMostRecentlyPlayed() {

	}

	// Retrieve Most Recent Songs
	public List<Song> getRecentlyPlayed() {
		return recentlyPlayed;
	}

	// Update Top 10 Played
	public void updateMostFrequentlyPlayed() {

	}

	// Retrieve Most Frequent Songs
	public List<Song> getFrequentPlayed() {
		return frequentPlayedSongs;
	}

	// Shuffles Songs in Library
	public ArrayList<Song> shuffleLibrary() {

	}

	// Shuffle Songs in Playlist
	public ArrayList<Song> shufflePlaylist() {

	}

	// Retrieve Favorite Songs
	public Set<Song> getFavorites() {
		return favoriteSongs;
	}

	// Get Genre Playlists
	public ArrayList<Song> getGenrePlaylist(String genre) {

	}

	// Create Top Rated Song List
	public void createTopRated() {

	}

	// Get Top Rated Songs
	public List<Song> getTopRated() {
		return topRated;
	}

}
