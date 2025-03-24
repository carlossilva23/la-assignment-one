package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Album;
import model.LibraryModel;
import model.Playlist;
import model.Song;

public class LibraryView {

	private static LibraryModel model = new LibraryModel();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean loggedIn = false;
		String username = "";

		// Login or create user
		while (!loggedIn) {
			System.out.println("=== Welcome to the Music Library ===");
			System.out.print("Enter your username: ");
			username = scanner.nextLine();

			System.out.print("Enter your password: ");
			String password = scanner.nextLine();

			loggedIn = model.login(username, password);
			if (!loggedIn) {
				System.out.println("Invalid login. Would you like to create a new account? (yes/no)");
				String createAccount = scanner.nextLine();
				if (createAccount.equalsIgnoreCase("yes")) {
					System.out.print("Enter a new password: ");
					String newPassword = scanner.nextLine();
					model.createAccount(username, newPassword);
					loggedIn = true;
				}
			} else {
				System.out.println("Logged in successfully as " + username);
			}
		}

		// Main menu
		boolean running = true;
		while (running) {
			System.out.println("\n=== Music Library ===");
			System.out.println("0. Search Store for Song By Title");
			System.out.println("1. Search Store for Song By Artist");
			System.out.println("2. Search Store for Album by Name");
			System.out.println("3. Search Store for Album by Artist");
			System.out.println("4. List All Content in Store");
			System.out.println("5. Search for Song By Title in Library");
			System.out.println("6. Search for Song By Artist in Library");
			System.out.println("7. Search for Song By Genre in Library");
			System.out.println("8. Search for Albums By Title in Library");
			System.out.println("9. Search Albums By Artist in Library");
			System.out.println("10. Search Playlists By Name in Library");
			System.out.println("11. Add Song to Library");
			System.out.println("12. Add Album to Library");
			System.out.println("13. List All Content in Library");
			System.out.println("14. List All Artists in Library");
			System.out.println("15. List All Playlists in Library");
			System.out.println("16. List All Favorite Songs");
			System.out.println("17. List All Songs By Sorted Title");
			System.out.println("18. List All Songs By Sorted Artist");
			System.out.println("19. List All Songs By Sorted Rating");
			System.out.println("20. Shuffle Library");
			System.out.println("21. Shuffle Playlist");
			System.out.println("22. Create Playlist");
			System.out.println("23. Add Song to Playlist");
			System.out.println("24. Remove Song From Playlist");
			System.out.println("25. Favorite a Song");
			System.out.println("26. Rate a Song");
			System.out.println("27. Play a Song");
			System.out.println("28. List 10 Most Recent Songs");
			System.out.println("29. List 10 Most Played Songs");
			System.out.println("30. List Genre Playlists (If Any)");
			System.out.println("31. Remove Song from Library");
			System.out.println("32. Remove Album from Library");
			System.out.println("33. Exit Program (Save Data)");
			System.out.print("Enter your choice: ");

			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 0:
				// Search Store for Song By Title
				System.out.print("Enter song title: ");
				String storeSongTitle = scanner.nextLine();
				ArrayList<Song> storeSongs = model.searchStoreSongByTitle(storeSongTitle);
				displaySongs(storeSongs);
				break;
			case 1:
				// Search Store for Song By Artist
				System.out.print("Enter artist name: ");
				String storeArtist = scanner.nextLine();
				ArrayList<Song> storeSongsByArtist = model.searchStoreSongsByArtist(storeArtist);
				displaySongs(storeSongsByArtist);
				break;
			case 2:
				// Search Store for Album by Name
				System.out.print("Enter album title: ");
				String storeAlbumTitle = scanner.nextLine();
				Album storeAlbum = model.searchStoreAlbumByTitle(storeAlbumTitle);
				if (storeAlbum != null) {
					System.out.println(storeAlbum);
				} else {
					System.out.println("Album not found.");
				}
				break;
			case 3:
				// Search Store for Album by Artist
				System.out.print("Enter album artist: ");
				String storeAlbumArtist = scanner.nextLine();
				ArrayList<Album> storeAlbums = model.searchStoreAlbumsByArtist(storeAlbumArtist);
				displayAlbums(storeAlbums);
				break;
			case 4:
				// List All Content in Store
				ArrayList<Song> allStoreSongs = model.getAllStoreSongs();
				ArrayList<Album> allStoreAlbums = model.getAllStoreAlbums();
				displaySongs(allStoreSongs);
				displayAlbums(allStoreAlbums);
				break;
			case 5:
				// Search for Song By Title in Library
				System.out.print("Enter song title: ");
				String libSongTitle = scanner.nextLine();
				ArrayList<Song> libSongs = model.searchLibrarySongByTitle(libSongTitle);
				displaySongs(libSongs);
				break;
			case 6:
				// Search for Song By Artist in Library
				System.out.print("Enter artist name: ");
				String libArtist = scanner.nextLine();
				ArrayList<Song> libSongsByArtist = model.searchLibrarySongsByArtist(libArtist);
				displaySongs(libSongsByArtist);
				break;
			case 7:
				// Search for Song By Genre in Library
				System.out.print("Enter genre: ");
				String genre = scanner.nextLine();
				ArrayList<Song> libSongsByGenre = model.searchLibrarySongsByGenre(genre);
				displaySongs(libSongsByGenre);
				break;
			case 8:
				// Search for Albums By Title in Library
				System.out.print("Enter album title: ");
				String libAlbumTitle = scanner.nextLine();
				ArrayList<Album> libAlbumsByTitle = model.searchLibraryAlbumByTitle(libAlbumTitle);
				displayAlbums(libAlbumsByTitle);
				break;
			case 9:
				// Search Albums By Artist in Library
				System.out.print("Enter album artist: ");
				String libAlbumArtist = scanner.nextLine();
				ArrayList<Album> libAlbumsByArtist = model.searchLibraryAlbumsByArtist(libAlbumArtist);
				displayAlbums(libAlbumsByArtist);
				break;
			case 10:
				// Search Playlists By Name in Library
				System.out.print("Enter playlist name: ");
				String playlistName = scanner.nextLine();
				ArrayList<Playlist> playlists = model.searchLibraryPlaylistsByName(playlistName);
				displayPlaylists(playlists);
				break;
			case 11:
				// Add Song to Library
				System.out.print("Enter song title: ");
				String songToAdd = scanner.nextLine();
				model.addSongToLibrary(songToAdd);
				System.out.println("Song added to library.");
				break;
			case 12:
				// Add Album to Library
				System.out.print("Enter album title: ");
				String albumToAdd = scanner.nextLine();
				model.addAlbumToLibrary(albumToAdd);
				System.out.println("Album added to library.");
				break;
			case 13:
				// List All Content in Library
				ArrayList<Song> allLibrarySongs = model.getAllLibrarySongs();
				ArrayList<Album> allLibraryAlbums = model.getAllLibraryAlbums();
				displaySongs(allLibrarySongs);
				displayAlbums(allLibraryAlbums);
				break;
			case 14:
				// List All Artists in Library
				ArrayList<String> allArtists = model.getAllLibraryArtists();
				for (String artist : allArtists) {
					System.out.println(artist);
				}
				break;
			case 15:
				// List All Playlists in Library
				ArrayList<Playlist> allPlaylists = model.getAllPlaylists();
				displayPlaylists(allPlaylists);
				break;
			case 16:
				// List All Favorite Songs
				ArrayList<Song> favoriteSongs = model.getFavoriteSongs();
				displaySongs(favoriteSongs);
				break;
			case 17:
				// List All Songs By Sorted Title
				ArrayList<Song> sortedByTitle = model.getAllSongsSortedByTitle();
				displaySongs(sortedByTitle);
				break;
			case 18:
				// List All Songs By Sorted Artist
				ArrayList<Song> sortedByArtist = model.getAllSongsSortedByArtist();
				displaySongs(sortedByArtist);
				break;
			case 19:
				// List All Songs By Sorted Rating
				ArrayList<Song> sortedByRating = model.getAllSongsSortedByRating();
				displaySongs(sortedByRating);
				break;
			case 20:
				// Shuffle Library
				model.shuffleLibrary();
				System.out.println("Library shuffled.");
				break;
			case 21:
				// Shuffle Playlist
				System.out.print("Enter playlist name: ");
				String playlistNameToShuffle = scanner.nextLine();
				model.shufflePlaylist(playlistNameToShuffle);
				System.out.println("Playlist shuffled.");
				break;
			case 22:
				// Create Playlist
				System.out.print("Enter playlist name: ");
				String newPlaylistName = scanner.nextLine();
				model.createPlaylist(newPlaylistName);
				System.out.println("Playlist created.");
				break;
			case 23:
				// Add Song to Playlist
				System.out.print("Enter playlist name: ");
				String playlistToAddSong = scanner.nextLine();
				System.out.print("Enter song title to add: ");
				String songToAddToPlaylist = scanner.nextLine();
				model.addSongToPlaylist(playlistToAddSong, songToAddToPlaylist);
				System.out.println("Song added to playlist.");
				break;
			case 24:
				// Remove Song from Playlist
				System.out.print("Enter playlist name: ");
				String playlistToRemoveSong = scanner.nextLine();
				System.out.print("Enter song title to remove: ");
				String songToRemoveFromPlaylist = scanner.nextLine();
				model.removeSongFromPlaylist(playlistToRemoveSong, songToRemoveFromPlaylist);
				System.out.println("Song removed from playlist.");
				break;
			case 25:
				// Favorite a Song
				System.out.print("Enter song title: ");
				String songToFavorite = scanner.nextLine();
				model.favoriteSong(songToFavorite);
				System.out.println("Song marked as favorite.");
				break;
			case 26:
				// Rate a Song
				System.out.print("Enter song title: ");
				String songToRate = scanner.nextLine();
				System.out.print("Enter rating (1-5): ");
				int rating = Integer.parseInt(scanner.nextLine());
				model.rateSong(songToRate, rating);
				System.out.println("Song rated.");
				break;
			case 27:
				// Play a Song
				System.out.print("Enter song title: ");
				String songToPlay = scanner.nextLine();
				model.playSong(songToPlay);
				break;
			case 28:
				// List 10 Most Recent Songs
				ArrayList<Song> recentSongs = model.getRecentSongs(10);
				displaySongs(recentSongs);
				break;
			case 29:
				// List 10 Most Played Songs
				ArrayList<Song> playedSongs = model.getMostPlayedSongs(10);
				displaySongs(playedSongs);
				break;
			case 30:
				// List Genre Playlists (If Any)
				ArrayList<String> genrePlaylists = model.getGenrePlaylists();
				for (String genrePlaylist : genrePlaylists) {
					System.out.println(genrePlaylist);
				}
				break;
			case 31:
				// Remove Song from Library
				System.out.print("Enter song title to remove: ");
				String songToRemove = scanner.nextLine();
				model.removeSongFromLibrary(songToRemove);
				System.out.println("Song removed from library.");
				break;
			case 32:
				// Remove Album from Library
				System.out.print("Enter album title to remove: ");
				String albumToRemove = scanner.nextLine();
				model.removeAlbumFromLibrary(albumToRemove);
				System.out.println("Album removed from library.");
				break;
			case 33:
				// Exit Program (Save Data)
				model.saveData();
				System.out.println("Goodbye!");
				running = false;
				break;
			default:
				System.out.println("Invalid option. Try again.");
			}
		}
	}

	private static void displaySongs(ArrayList<Song> songs) {
		if (songs.isEmpty()) {
			System.out.println("No songs found.");
		} else {
			for (Song song : songs) {
				System.out.println(song);
			}
		}
	}

	private static void displayAlbums(ArrayList<Album> albums) {
		if (albums.isEmpty()) {
			System.out.println("No albums found.");
		} else {
			for (Album album : albums) {
				System.out.println(album);
			}
		}
	}

	private static void displayPlaylists(ArrayList<Playlist> playlists) {
		if (playlists.isEmpty()) {
			System.out.println("No playlists found.");
		} else {
			for (Playlist playlist : playlists) {
				System.out.println(playlist);
			}
		}
	}
}
