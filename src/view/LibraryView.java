package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.Playlist;
import model.Rating;
import model.Song;
import model.User;
import model.UserAlbum;
import model.UserManager;



public class LibraryView {

	private static MusicStore store =  new MusicStore();
	private static UserManager userManager = new UserManager();
	private static Scanner scanner = new Scanner(System.in);
	private static User user;

	public static void main(String[] args) {
		boolean loggedIn = false;
		boolean running = true;
		while (running) {
			while (!loggedIn) {
				System.out.println("=== Welcome to the Music Library ===");
				System.out.println("1. Login");
				System.out.println("2. Create Account");
				System.out.println("3. Exit");
				System.out.print("Please select an option ");
				String choice = scanner.nextLine();

				switch (choice) {
				case "1":
					System.out.print("Enter your username: ");
					String username = scanner.nextLine();
					System.out.print("Enter your password: ");
					String password = scanner.nextLine();
					user = userManager.loginUser(username, password);
					if (user != null) {
						System.out.println("Login successful. Welcome, " + username + "!");
						loggedIn = true;
					} else {
						System.out.println("Login failed. Please check your username and password.");
					}
					break;
				case "2":
					System.out.print("Enter a new username: ");
					String newUsername = scanner.nextLine();
					System.out.print("Enter a new password: ");
					String newPassword = scanner.nextLine();
					user = userManager.createUser(newUsername, newPassword);
					if (user != null) {
						System.out.println("New user created. Welcome!");
						loggedIn = true;
					} else {
						System.out.println("User creation failed. Username already exists.");
					} 
					break;
				case "3":
					System.out.println("Exiting the program. Goodbye!");
					userManager.saveUsers();
					System.exit(0);
					break;
					
				default:
					System.out.println("Invalid option. Please choose 1, 2, or 3");
				}
			}
						
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
			System.out.println("30. List Top Rated Songs");
			System.out.println("31. List Genre Playlists (If Any)");
			System.out.println("32. Remove Song from Library");
			System.out.println("33. Remove Album from Library");
			System.out.println("34. Log Out");
			System.out.print("Enter your choice: ");

			LibraryModel userLib = user.getLibrary();
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 0:
				// Search Store for Song By Title
				System.out.println("Enter song title: ");
				String storeSongTitle = scanner.nextLine();
				ArrayList<Song> storeSongs = store.searchSongByTitle(storeSongTitle);
				if (storeSongs != null) {				
					for (Song song : storeSongs) {
						System.out.println(song);
					}
				} else {
					System.out.println("Song does not exist in Music Store.");
				}
				break;
			case 1:
				// Search Store for Song By Artist
				System.out.println("Enter artist name: ");
				String storeArtist = scanner.nextLine();
				ArrayList<Song> storeSongsByArtist = store.searchSongsByArtist(storeArtist);
				if (storeSongsByArtist != null) {
					for (Song song : storeSongsByArtist) {
						System.out.println(song);
					}
				} else {
					System.out.println(storeArtist + " not found in Music Store");
				}
				break;
			case 2:
				// Search Store for Album by Name
				System.out.println("Enter album title: ");
				String storeAlbumTitle = scanner.nextLine();
				ArrayList<Album> storeAlbum = store.searchAlbumByTitle(storeAlbumTitle);
				if (storeAlbum != null) {
					System.out.println(storeAlbum.get(0));
				} else {
					System.out.println("Album not found in Music Store.");
				}
				break;
			case 3:
				// Search Store for Album by Artist
				System.out.println("Enter album artist: ");
				String storeAlbumArtist = scanner.nextLine();
				ArrayList<Album> storeAlbums = store.searchAlbumsByArtist(storeAlbumArtist);
				if (storeAlbums != null) {
					for (Album album : storeAlbums) {
						System.out.println(album);
					}
				} else {
					System.out.println(storeAlbumArtist + " not found in Music Store.");
				}
				break;
			case 4:
				// List All Content in Store
				ArrayList<Album> allContent = store.listAllContent();
				for (Album album : allContent) {
					System.out.println(album);
				}
				break;
			case 5:
				// Search for Song By Title in Library
				System.out.println("Enter song title: ");
				String libSongTitle = scanner.nextLine();
				ArrayList<Song> libSongs = userLib.searchSongsByTitle(libSongTitle);
				if (!libSongs.isEmpty()) {
					for (Song song : libSongs) {
						System.out.println(song);
					}
				} else {
					System.out.println("Song not found in Library");
				}
				break;
			case 6:
				// Search for Song By Artist in Library
				System.out.println("Enter artist name: ");
				String libArtist = scanner.nextLine();
				ArrayList<Song> libSongsByArtist = userLib.searchSongByArtist(libArtist);
				if (!libSongsByArtist.isEmpty()) {
					for (Song song : libSongsByArtist) {
						System.out.println(song);
					}
				} else {
					System.out.println("No songs found by " + libArtist);
				}
				break;
			case 7:
				// Search for Song By Genre in Library
				System.out.println("Enter genre: ");
				String genre = scanner.nextLine();
				ArrayList<Song> libSongsByGenre = userLib.searchSongsByGenre(genre);
				if (libSongsByGenre != null) {
					for (Song song : libSongsByGenre) {
						System.out.println(song);
					}
				} else {
					System.out.println(genre + " not found in Library.");
				}
				break;
			case 8:
				// Search for Albums By Title in Library
				System.out.println("Enter album title: ");
				String libAlbumTitle = scanner.nextLine();
				UserAlbum libAlbumByTitle = userLib.searchAlbumByTitle(libAlbumTitle);
				if (libAlbumByTitle != null) {
					System.out.println(libAlbumByTitle);
				} else {
					System.out.println("Album not found in library");
				}
				break;
			case 9:
				// Search Albums By Artist in Library
				System.out.println("Enter album artist: ");
				String libAlbumArtist = scanner.nextLine();
				ArrayList<UserAlbum> libAlbumsByArtist = userLib.searchAlbumByArtist(libAlbumArtist);
				if (!libAlbumsByArtist.isEmpty()) {
					for (UserAlbum album : libAlbumsByArtist) {
						System.out.println(album);
					}
				} else {
					System.out.println(libAlbumArtist + " not found in Library.");
				}
				break;
			case 10:
				// Search Playlists By Name in Library
				System.out.println("Enter playlist name: ");
				String playlistName = scanner.nextLine();
				Playlist userPlaylist = userLib.searchPlaylistsByName(playlistName);
				if (userPlaylist != null) {
					System.out.println(userPlaylist);
				} else {
					System.out.println("Playlist not found in library");
				}
				break;
			case 11:
				// Add Song to Library
				System.out.println("Enter song title: ");
				String songName = scanner.nextLine();
				ArrayList<Song> storeSearchSong = store.searchSongByTitle(songName);
				if (!storeSearchSong.isEmpty()){
					if (storeSearchSong.size() == 1) {
						userLib.addSong(storeSearchSong.get(0));
					} else if (storeSearchSong.size() > 1) {
						System.out.println("Multiple songs found in store, Please choose one:");
						for (int i = 0; i <storeSearchSong.size(); i++) {
							System.out.println(i + ": " + storeSearchSong.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < storeSearchSong.size()) {
							userLib.addSong(storeSearchSong.get(index));
						} else {
							System.out.println("Invalid Selection.");
						}
					}	
				} else {
					System.out.println("Song not found in Music Store.");
				}
				break;
			case 12:
				// Add Album to Library
				System.out.println("Enter album title: ");
				String albumToAdd = scanner.nextLine();
				ArrayList<Album> album = store.searchAlbumByTitle(albumToAdd);
				if (album != null) {
					userLib.addAlbum(album.get(0));
				} else {
					System.out.println("Album not found in store.");
				}
				break;
			case 13:
				// List All Content in Library
				ArrayList<UserAlbum> allLib = userLib.listAllContent();
				for (UserAlbum userAlbum : allLib) {
					System.out.println(userAlbum);
				}
				break;
			case 14:
				// List All Artists in Library
				ArrayList<String> allArtists = userLib.listAllArtists();
				for (String artist : allArtists) {
					System.out.println(artist);
				}
				break;
			case 15:
				// List All Playlists in Library
				ArrayList<Playlist> allPlaylists = userLib.listAllPlaylists();
				for (Playlist playlist : allPlaylists) {
					System.out.println(playlist);
				}
				break;
			case 16:
				// List All Favorite Songs
				Set<Song> favoriteSongs = userLib.getFavorites();
				for (Song song : favoriteSongs) {
					System.out.println(song);
				}
				break;
			case 17:
				// List All Songs By Sorted Title
				ArrayList<Song> sortedByTitle = userLib.listSortedSongsByTitle();
				for (Song song : sortedByTitle) {
					System.out.println(song);
				}
				break;
			case 18:
				// List All Songs By Sorted Artist
				ArrayList<Song> sortedByArtist = userLib.listSortedSongsByArtist();
				for (Song song : sortedByArtist) {
					System.out.println(song);
				}
				break;
			case 19:
				// List All Songs By Sorted Rating
				ArrayList<Song> sortedByRating = userLib.listSortedSongsByRating();
				for (Song song : sortedByRating) {
					System.out.println(song);
				}
				break;
			case 20:
				// Shuffle Library
				List<Song> shuffledLib = userLib.shuffleLibrary();
				for (Song song : shuffledLib) {
					System.out.println(song);
				}
				System.out.println("Library shuffled.");
				break;
			case 21:
				// Shuffle Playlist
				System.out.print("Enter playlist name: ");
				String playlistNameToShuffle = scanner.nextLine();
				Playlist firstStepShuffle = userLib.searchPlaylistsByName(playlistNameToShuffle);
				List<Song> shuffledPlaylist = userLib.shufflePlaylist(firstStepShuffle);
				for (Song song : shuffledPlaylist) {
					System.out.println(song);
				}
				System.out.println("Playlist shuffled.");
				break;
			case 22:
				// Create Playlist
				System.out.print("Enter playlist name: ");
				String newPlaylistName = scanner.nextLine();
				userLib.createPlaylist(newPlaylistName);
				System.out.println("Playlist created.");
				break;
			case 23:
				// Add Song to Playlist
				System.out.print("Enter playlist name: ");
				String playlistToAddSong = scanner.nextLine();
				Playlist playlistFound = userLib.searchPlaylistsByName(playlistToAddSong);
				if (playlistFound != null) {
					System.out.print("Enter song title to add: ");
					String songToAddToPlaylist = scanner.nextLine();
					ArrayList<Song> songsFound = userLib.searchSongsByTitle(songToAddToPlaylist);
					if (songsFound.size() == 1) {
						userLib.addSongToPlaylist(playlistFound, songsFound.get(0));
						System.out.println("Song added to playlist.");
					} else {
						System.out.println("Multiple songs found in library, Please choose one:");
						for (int i = 0; i < songsFound.size(); i++) {
							System.out.println(i + ": " + songsFound.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < songsFound.size()) {
							userLib.addSongToPlaylist(playlistFound, songsFound.get(index));
							System.out.println("Song added to playlist.");
						} else {
							System.out.println("Invalid Selection.");
						}
					}
				} else {
					System.out.println("Playlist not found.");
				}
				break;
			case 24:
				// Remove Song from Playlist
				System.out.print("Enter playlist name: ");
				String playlistToRemoveSong = scanner.nextLine();
				Playlist foundPlaylist = userLib.searchPlaylistsByName(playlistToRemoveSong);
				if (foundPlaylist != null) {
					System.out.print("Enter song title to remove: ");
					String songToRemoveFromPlaylist = scanner.nextLine();
					ArrayList<Song> songsFound = userLib.searchSongsByTitle(songToRemoveFromPlaylist);
					if (songsFound.size() == 1) {
						userLib.removeSongFromPlaylist(foundPlaylist, songsFound.get(0));
					} else {
						System.out.println("Multiple songs found in library, Please choose one:");
						for (int i = 0; i < songsFound.size(); i++) {
							System.out.println(i + ": " + songsFound.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < songsFound.size()) {
							userLib.removeSongFromPlaylist(foundPlaylist, songsFound.get(index));
						} else {
							System.out.println("Invalid Selection.");
						}
					}
					System.out.println("Playlist not found.");
				}
				System.out.println("Song removed from playlist.");
					break;
			case 25:
				// Favorite a Song
				System.out.print("Enter song title: ");
				String songToFavorite = scanner.nextLine();
				ArrayList<Song> favedSong = userLib.searchSongsByTitle(songToFavorite);
				if (favedSong != null) {
					if (favedSong.size() == 1) {
						userLib.markSongFavorite(favedSong.get(0));
					} else {
						System.out.println("Multiple songs found in library, Please choose one:");
						for (int i = 0; i < favedSong.size(); i++) {
							System.out.println(i + ": " + favedSong.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < favedSong.size()) {
							userLib.markSongFavorite(favedSong.get(0));
						} else {
							System.out.println("Invalid Selection.");
						}
					}
					System.out.println("Song not found in Library");
				}
				System.out.println("Song marked as favorite.");
					break;
			case 26:
				// Rate a Song
				System.out.println("Enter song title: ");
				String songToRate = scanner.nextLine();
				List<Song> firstStepRate = userLib.searchSongsByTitle(songToRate);
				if (firstStepRate != null) {
					if (firstStepRate.size() == 1) {
						System.out.println("Enter Rating 1 - 5: ");
						int newRating = Integer.parseInt(scanner.nextLine());
						Rating newlyRated = Rating.fromInt(newRating);
						firstStepRate.get(0).setRating(newlyRated);
						System.out.println("Song rated.");
					} else if (firstStepRate.size() > 1) {
						System.out.println("Multiple songs found in library, Please choose one:");
						for (int i = 0; i < firstStepRate.size(); i++) {
							System.out.println(i + ": " + firstStepRate.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < firstStepRate.size()) {
							System.out.println("Enter Rating 1 - 5: ");
							int newRating = Integer.parseInt(scanner.nextLine());
							Rating newlyRated = Rating.fromInt(newRating);
							firstStepRate.get(index).setRating(newlyRated);
							System.out.println("Song rated.");
						} else {
							System.out.println("Invalid Selection.");
						}
					}
				} else {
					System.out.println("Song not found in Library.");
				}
				break;
			case 27:
				// Play a Song
				System.out.println("Enter song title: ");
				String songToRate1 = scanner.nextLine();
				List<Song> firstStepRate1 = userLib.searchSongsByTitle(songToRate1);
				if (firstStepRate1 != null) {
					if (firstStepRate1.size() == 1) {
						userLib.playSong(firstStepRate1.get(0));
						System.out.println("Song played.");
					} else if (firstStepRate1.size() > 1) {
						System.out.println("Multiple songs found in library, Please choose one:");
						for (int i = 0; i < firstStepRate1.size(); i++) {
							System.out.println(i + ": " + firstStepRate1.get(i));
						}
						int index = Integer.parseInt(scanner.nextLine());
						if (index >= 0 && index < firstStepRate1.size()) {
							userLib.playSong(firstStepRate1.get(index));
							System.out.println("Song played.");
						} else {
							System.out.println("Invalid Selection.");
						}
					}
				} else {
					System.out.println("Song not found in Library.");
				}
				break;
			case 28:
				// List 10 Most Recent Songs
				List<Song> recentSongs = userLib.getRecentlyPlayed();
				for (Song song : recentSongs) {
					System.out.println(song);
				}
				break;
			case 29:
				// List 10 Most Played Songs
				List<Song> playedSongs = userLib.getFrequentPlayed();
				for (Song song : playedSongs) {
					System.out.println(song);
				}
				break;
			case 30:
				// List Top Rated Songs
				List<Song> topRatedSongs = userLib.getTopRated();
				for (Song song : topRatedSongs) {
 					System.out.println(song);
 				}
			case 31:
				// List Genre Playlists (If Any)
				ArrayList<Playlist> genrePlaylists = userLib.getGenrePlaylists();
				if (!genrePlaylists.isEmpty()) {
					for (Playlist playlists : genrePlaylists) {
						System.out.println(playlists);
					}	
				} else {
					System.out.println("No genres have at least 10 songs in Library.");
				}
				break;
			case 32:
				// Remove Song from Library
				System.out.println("Enter song title to remove: ");
				String songToRemove = scanner.nextLine();
				ArrayList<Song> songsToRemove = userLib.searchSongsByTitle(songToRemove);
				if (songsToRemove.size() == 1) {
					userLib.removeSong(songsToRemove.get(0));
					System.out.println("Song removed from library.");
				} else if (songsToRemove.size() > 1) {
					System.out.println("Multiple songs found in library, Please choose one:");
					for (int i = 0; i < songsToRemove.size(); i++) {
						System.out.println(i + ": " + songsToRemove.get(i));
					}
					int index = Integer.parseInt(scanner.nextLine());
					if (index >= 0 && index < songsToRemove.size()) {
						userLib.removeSong(songsToRemove.get(index));
						System.out.println("Song removed from library.");
					} else {
						System.out.println("Invalid Selection.");
					}
				} else {
					System.out.println("Song not found in Library.");
				}
				break;
			case 33:
				// Remove Album from Library
				System.out.print("Enter album title to remove: ");
				String albumToRemove = scanner.nextLine();
				UserAlbum albumsToRemove = userLib.searchAlbumByTitle(albumToRemove);
				userLib.removeAlbum(albumsToRemove.getAlbum());
				System.out.println("Album removed from library.");
				break;
			case 34:
				// Log out
				System.out.println("Logging Out. Goodbye!");
				loggedIn = false;
				break;
			default:
				System.out.println("Invalid option. Try again.");
			}
		}
	}
}
