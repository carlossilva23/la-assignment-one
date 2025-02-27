package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.LibraryModel;
import model.Playlist;
import model.Song;
import model.Album;

public class LibraryView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryModel model = new LibraryModel();
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== Music Library ===");
            System.out.println("1. Search song by title (Library)");
            System.out.println("2. Search songs by artist (Library)");
            System.out.println("3. Search album by title (Library)");
            System.out.println("4. Search albums by artist (Library)");
            System.out.println("5. Search playlist by name");
            System.out.println("6. Add song to library");
            System.out.println("7. Add album to library");
            System.out.println("8. List all songs (Library)");
            System.out.println("9. List all artists (Library)");
            System.out.println("10. List all albums (Library)");
            System.out.println("11. List all playlists");
            System.out.println("12. List favorite songs (Library)");
            System.out.println("13. Create new playlist");
            System.out.println("14. Mark song as favorite (Library)");
            System.out.println("15. Rate a song (Library)");
            System.out.println("16. Add song to a playlist");
            System.out.println("17. Remove song from a playlist");
            System.out.println("18. Search song by title (MusicStore)");
            System.out.println("19. Search songs by artist (MusicStore)");
            System.out.println("20. Search album by title (MusicStore)");
            System.out.println("21. Search albums by artist (MusicStore)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter song title (Library): ");
                    String libSongTitle = scanner.nextLine();
                    ArrayList<Song> libSongs = model.searchLibrarySongByTitle(libSongTitle);
                    if (libSongs.isEmpty()) {
                        System.out.println("Song not found in library.");
                    } else {
                        for (Song s : libSongs) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter artist name (Library): ");
                    String libArtist = scanner.nextLine();
                    ArrayList<Song> songsByArtist = model.searchLibrarySongsByArtist(libArtist);
                    if (songsByArtist.isEmpty()) {
                        System.out.println("No songs found for artist " + libArtist);
                    } else {
                        for (Song s : songsByArtist) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter album title (Library): ");
                    String libAlbumTitle = scanner.nextLine();
                    Album libAlbum = model.searchLibraryAlbumByTitle(libAlbumTitle);
                    if (libAlbum == null) {
                        System.out.println("Album not found in library.");
                    } else {
                        System.out.println(libAlbum);
                    }
                    break;
                case 4:
                    System.out.print("Enter album artist (Library): ");
                    String libAlbumArtist = scanner.nextLine();
                    ArrayList<Album> albumsByArtist = model.searchLibraryAlbumsByArtist(libAlbumArtist);
                    if (albumsByArtist.isEmpty()) {
                        System.out.println("No albums found for artist " + libAlbumArtist);
                    } else {
                        for (Album a : albumsByArtist) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    ArrayList<Playlist> allPlaylists = model.getAllPlaylists();
                    boolean foundPlaylist = false;
                    for (Playlist p : allPlaylists) {
                        if (p.getName().equalsIgnoreCase(playlistName)) {
                            System.out.println(p);
                            foundPlaylist = true;
                            break;
                        }
                    }
                    if (!foundPlaylist) {
                        System.out.println("Playlist " + playlistName + " not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter song title to add to library: ");
                    String songTitleToAdd = scanner.nextLine();
                    ArrayList<Song> storeMatches = model.getStoreSongsByTitle(songTitleToAdd);
                    if (storeMatches.isEmpty()) {
                        System.out.println("Song not found in MusicStore.");
                    } else if (storeMatches.size() == 1) {
                        model.addSong(storeMatches.get(0));
                        System.out.println("Added: " + storeMatches.get(0));
                    } else {
                        System.out.println("Multiple songs found in MusicStore:");
                        for (int i = 0; i < storeMatches.size(); i++) {
                            System.out.println(i + ": " + storeMatches.get(i));
                        }
                        System.out.print("Enter the index of the song to add: ");
                        int index = Integer.parseInt(scanner.nextLine());
                        if (index >= 0 && index < storeMatches.size()) {
                            model.addSong(storeMatches.get(index));
                            System.out.println("Added: " + storeMatches.get(index));
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 7:
                    System.out.print("Enter album title to add to library: ");
                    String albumToAdd = scanner.nextLine();
                    model.addAlbum(albumToAdd);
                    System.out.println("Album added (if found).");
                    break;
                case 8:
                    ArrayList<Song> allSongs = model.getAllSongs();
                    for (Song s : allSongs) {
                        System.out.println(s);
                    }
                    break;
                case 9:
                    ArrayList<String> allArtists = model.getAllArtists();
                    for (String a : allArtists) {
                        System.out.println(a);
                    }
                    break;
                case 10:
                    ArrayList<Album> allAlbums = model.getAllAlbums();
                    for (Album a : allAlbums) {
                        System.out.println(a);
                    }
                    break;
                case 11:
                    ArrayList<Playlist> allPlaylistsList = model.getAllPlaylists();
                    for (Playlist p : allPlaylistsList) {
                        System.out.println(p);
                    }
                    break;
                case 12:
                    ArrayList<Song> favSongs = model.getFavoriteSongs();
                    for (Song s : favSongs) {
                        System.out.println(s);
                    }
                    break;
                case 13:
                    System.out.print("Enter new playlist name: ");
                    String newPlaylist = scanner.nextLine();
                    model.createPlaylist(newPlaylist);
                    System.out.println("Playlist created.");
                    break;
                case 14:
                    System.out.print("Enter song title to mark as favorite (Library): ");
                    String songTitleToFavorite = scanner.nextLine();
                    ArrayList<Song> libraryMatchesForFav = model.getLibrarySongsByTitle(songTitleToFavorite);
                    if (libraryMatchesForFav.isEmpty()) {
                        System.out.println("Song not found in library.");
                    } else if (libraryMatchesForFav.size() == 1) {
                        model.favoriteSong(libraryMatchesForFav.get(0));
                        System.out.println("Marked as favorite: " + libraryMatchesForFav.get(0));
                    } else {
                        System.out.println("Multiple songs found in library:");
                        for (int i = 0; i < libraryMatchesForFav.size(); i++) {
                            System.out.println(i + ": " + libraryMatchesForFav.get(i));
                        }
                        System.out.print("Enter the index of the song to mark as favorite: ");
                        int favIndex = Integer.parseInt(scanner.nextLine());
                        if (favIndex >= 0 && favIndex < libraryMatchesForFav.size()) {
                            model.favoriteSong(libraryMatchesForFav.get(favIndex));
                            System.out.println("Marked as favorite: " + libraryMatchesForFav.get(favIndex));
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 15:
                    System.out.print("Enter song title to rate (Library): ");
                    String songTitleToRate = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = Integer.parseInt(scanner.nextLine());
                    ArrayList<Song> libraryMatchesForRate = model.getLibrarySongsByTitle(songTitleToRate);
                    if (libraryMatchesForRate.isEmpty()) {
                        System.out.println("Song not found in library.");
                    } else if (libraryMatchesForRate.size() == 1) {
                        model.rateSong(libraryMatchesForRate.get(0), rating);
                        System.out.println("Rated: " + libraryMatchesForRate.get(0));
                    } else {
                        System.out.println("Multiple songs found in library:");
                        for (int i = 0; i < libraryMatchesForRate.size(); i++) {
                            System.out.println(i + ": " + libraryMatchesForRate.get(i));
                        }
                        System.out.print("Enter the index of the song to rate: ");
                        int rateIndex = Integer.parseInt(scanner.nextLine());
                        if (rateIndex >= 0 && rateIndex < libraryMatchesForRate.size()) {
                            model.rateSong(libraryMatchesForRate.get(rateIndex), rating);
                            System.out.println("Rated: " + libraryMatchesForRate.get(rateIndex));
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 16:
                    System.out.print("Enter playlist name: ");
                    String plName = scanner.nextLine();
                    System.out.print("Enter song title to add to playlist: ");
                    String songToAdd = scanner.nextLine();
                    ArrayList<Song> storeMatchesForPl = model.getStoreSongsByTitle(songToAdd);
                    if (storeMatchesForPl.isEmpty()) {
                        System.out.println("Song not found in MusicStore.");
                    } else if (storeMatchesForPl.size() == 1) {
                        model.addSongToPlaylist(plName, storeMatchesForPl.get(0));
                        System.out.println("Added to playlist: " + storeMatchesForPl.get(0));
                    } else {
                        System.out.println("Multiple songs found in MusicStore:");
                        for (int i = 0; i < storeMatchesForPl.size(); i++) {
                            System.out.println(i + ": " + storeMatchesForPl.get(i));
                        }
                        System.out.print("Enter the index of the song to add: ");
                        int index = Integer.parseInt(scanner.nextLine());
                        if (index >= 0 && index < storeMatchesForPl.size()) {
                            model.addSongToPlaylist(plName, storeMatchesForPl.get(index));
                            System.out.println("Added to playlist: " + storeMatchesForPl.get(index));
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    break;
                case 17:
                    System.out.print("Enter playlist name: ");
                    String plNameRemove = scanner.nextLine();
                    System.out.print("Enter song title to remove from playlist: ");
                    String songToRemove = scanner.nextLine();
                    model.removeSongFromPlaylist(plNameRemove, songToRemove);
                    System.out.println("Removed song (if present) from playlist.");
                    break;
                case 18:
                    System.out.print("Enter song title to search in MusicStore: ");
                    String storeSong = scanner.nextLine();
                    ArrayList<Song> storeSearchSongs = model.searchStoreSongByTitle(storeSong);
                    if (storeSearchSongs.isEmpty()) {
                        System.out.println("Song not found in MusicStore.");
                    } else {
                        for (Song s : storeSearchSongs) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 19:
                    System.out.print("Enter artist name to search in MusicStore: ");
                    String storeArtist = scanner.nextLine();
                    ArrayList<Song> storeSearchByArtist = model.searchStoreSongsByArtist(storeArtist);
                    if (storeSearchByArtist.isEmpty()) {
                        System.out.println("No songs by " + storeArtist + " found in MusicStore.");
                    } else {
                        for (Song s : storeSearchByArtist) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 20:
                    System.out.print("Enter album title to search in MusicStore: ");
                    String storeAlbum = scanner.nextLine();
                    Album storeAlbumResult = model.searchStoreAlbumByTitle(storeAlbum);
                    if (storeAlbumResult == null) {
                        System.out.println("Album not found in MusicStore.");
                    } else {
                        storeAlbumResult.print();
                    }
                    break;
                case 21:
                    System.out.print("Enter album artist to search in MusicStore: ");
                    String storeAlbumArtist = scanner.nextLine();
                    ArrayList<Album> storeAlbumsByArtist = model.searchStoreAlbumsByArtist(storeAlbumArtist);
                    if (storeAlbumsByArtist.isEmpty()) {
                        System.out.println("No albums by " + storeAlbumArtist + " found in MusicStore.");
                    } else {
                        for (Album a : storeAlbumsByArtist) {
                            a.print();
                        }
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting Music Library. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }
}
