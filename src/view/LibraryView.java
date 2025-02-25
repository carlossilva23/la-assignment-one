package view;

import java.util.Scanner;
import model.LibraryModel;

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
            
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    model.printSongByTitle(songTitle);
                    break;
                case 2:
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    model.printSongsByArtist(artist);
                    break;
                case 3:
                    System.out.print("Enter album title: ");
                    String albumTitle = scanner.nextLine();
                    model.printAlbumByTitle(albumTitle);
                    break;
                case 4:
                    System.out.print("Enter album artist: ");
                    String albumArtist = scanner.nextLine();
                    model.printAlbumsByArtist(albumArtist);
                    break;
                case 5:
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    model.printPlaylistByName(playlistName);
                    break;
                case 6:
                    System.out.print("Enter song title to add to library: ");
                    String addSong = scanner.nextLine();
                    model.addSong(addSong);
                    break;
                case 7:
                    System.out.print("Enter album title to add: ");
                    String addAlbum = scanner.nextLine();
                    model.addAlbum(addAlbum);
                    break;
                case 8:
                    model.printSongs();
                    break;
                case 9:
                    model.printArtists();
                    break;
                case 10:
                    model.printAlbums();
                    break;
                case 11:
                    model.printPlaylists();
                    break;
                case 12:
                    model.printFavorites();
                    break;
                case 13:
                    System.out.print("Enter new playlist name: ");
                    String newPlaylist = scanner.nextLine();
                    model.createPlaylist(newPlaylist);
                    break;
                case 14:
                    System.out.print("Enter song title to mark as favorite: ");
                    String favSong = scanner.nextLine();
                    model.setFavorite(favSong);
                    break;
                case 15:
                    System.out.print("Enter song title to rate: ");
                    String rateSong = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    try {
                        int rating = Integer.parseInt(scanner.nextLine());
                        model.rate(rateSong, rating);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid rating input.");
                    }
                    break;
                case 16:
                    System.out.print("Enter playlist name: ");
                    String plName = scanner.nextLine();
                    System.out.print("Enter song title to add to playlist: ");
                    String songToAdd = scanner.nextLine();
                    model.addSongToPlaylist(plName, songToAdd);
                    break;
                case 17:
                    System.out.print("Enter playlist name: ");
                    String plNameRemove = scanner.nextLine();
                    System.out.print("Enter song title to remove from playlist: ");
                    String songToRemove = scanner.nextLine();
                    model.removeSongFromPlaylist(plNameRemove, songToRemove);
                    break;
                case 18:
                    System.out.print("Enter song title to search in MusicStore: ");
                    String storeSong = scanner.nextLine();
                    model.searchStoreSongByTitle(storeSong);
                    break;
                case 19:
                    System.out.print("Enter artist name to search in MusicStore: ");
                    String storeArtist = scanner.nextLine();
                    model.searchStoreSongsByArtist(storeArtist);
                    break;
                case 20:
                    System.out.print("Enter album title to search in MusicStore: ");
                    String storeAlbum = scanner.nextLine();
                    model.searchStoreAlbumByTitle(storeAlbum);
                    break;
                case 21:
                    System.out.print("Enter album artist to search in MusicStore: ");
                    String storeAlbumArtist = scanner.nextLine();
                    model.searchStoreAlbumsByArtist(storeAlbumArtist);
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
