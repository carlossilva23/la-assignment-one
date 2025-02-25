package model;

import java.util.ArrayList;

public class LibraryModel {
    private Playlist singles;
    private ArrayList<Playlist> userPlaylists;
    private ArrayList<Album> albumLibrary;
    private MusicStore store;
    
    public LibraryModel() {
        singles = new Playlist("Singles");
        userPlaylists = new ArrayList<>();
        albumLibrary = new ArrayList<>();
        store = new MusicStore();
    }
    
    // Add a song from MusicStore into the internal "Singles" playlist
    public void addSong(String songTitle) {
        Song song = store.getSong(songTitle);
        if (song != null) {
            singles.addSong(song);
            System.out.println("Added song to library: " + song);
        } else {
            System.out.println("Song not found in MusicStore.");
        }
    }
    
    // Add an album from MusicStore into the album library
    public void addAlbum(String albumTitle) {
        Album album = store.getAlbum(albumTitle);
        if (album != null) {
            albumLibrary.add(album);
            System.out.println("Added album: " + album.getName());
        } else {
            System.out.println("Album not found in MusicStore.");
        }
    }
    
    // Create a new user playlist
    public void createPlaylist(String playlistName) {
        userPlaylists.add(new Playlist(playlistName));
        System.out.println("Created playlist: " + playlistName);
    }
    
    // Add a song (from MusicStore) to a specified user playlist
    public void addSongToPlaylist(String playlistName, String songTitle) {
        Playlist playlist = getUserPlaylist(playlistName);
        if (playlist == null) {
            System.out.println("Playlist " + playlistName + " not found.");
            return;
        }
        Song song = store.getSong(songTitle);
        if (song != null) {
            playlist.addSong(song);
            System.out.println("Added song " + songTitle + " to playlist " + playlistName);
        } else {
            System.out.println("Song " + songTitle + " not found in MusicStore.");
        }
    }
    
    // Remove a song from a specified user playlist
    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = getUserPlaylist(playlistName);
        if (playlist == null) {
            System.out.println("Playlist " + playlistName + " not found.");
            return;
        }
        playlist.removeSong(songTitle);
        System.out.println("Removed song " + songTitle + " from playlist " + playlistName);
    }
    
    // Helper method to get a user-created playlist
    private Playlist getUserPlaylist(String playlistName) {
        for (Playlist p : userPlaylists) {
            if (p.getName().equalsIgnoreCase(playlistName)) {
                return p;
            }
        }
        return null;
    }
    
    // Search for a song by title across singles, user playlists, albums
    public Song getSongByTitle(String title) {
        for (Song s : singles.getSongs()) {
            if (s.getName().equalsIgnoreCase(title))
                return s;
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getName().equalsIgnoreCase(title))
                    return s;
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getName().equalsIgnoreCase(title))
                    return s;
            }
        }
        return null;
    }
    
    public void printSongByTitle(String title) {
        Song song = getSongByTitle(title);
        if (song != null) {
            System.out.println(song);
        } else {
            System.out.println("Song not found in library.");
        }
    }
    
    // Print songs by artist from all collections
    public void printSongsByArtist(String artist) {
        boolean found = false;
        for (Song s : singles.getSongs()) {
            if (s.getArtist().equalsIgnoreCase(artist)) {
                System.out.println(s);
                found = true;
            }
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getArtist().equalsIgnoreCase(artist)) {
                    System.out.println(s);
                    found = true;
                }
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getArtist().equalsIgnoreCase(artist)) {
                    System.out.println(s);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No songs found for artist " + artist);
        }
    }
    
    // Print album details by title
    public void printAlbumByTitle(String albumTitle) {
        boolean found = false;
        for (Album album : albumLibrary) {
            if (album.getName().equalsIgnoreCase(albumTitle)) {
                album.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Album " + albumTitle + " not found in library.");
        }
    }
    
    // Print albums by artist
    public void printAlbumsByArtist(String artist) {
        boolean found = false;
        for (Album album : albumLibrary) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                album.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No albums found for artist " + artist);
        }
    }
    
    // Print all user-created playlists
    public void printPlaylists() {
        System.out.println("User Playlists:");
        if (userPlaylists.isEmpty()) {
            System.out.println("No playlists found.");
        } else {
            for (Playlist p : userPlaylists) {
                System.out.println(p.getName());
            }
        }
    }
    
    // Print a user playlist by name
    public void printPlaylistByName(String playlistName) {
        Playlist playlist = getUserPlaylist(playlistName);
        if (playlist != null) {
            playlist.print();
        } else {
            System.out.println("Playlist " + playlistName + " not found.");
        }
    }
    
    // Print songs from all collections
    public void printSongs() {
        System.out.println("All Songs in Library:");
        for (Song s : singles.getSongs()) {
            System.out.println(s);
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                System.out.println(s);
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                System.out.println(s);
            }
        }
    }
    
    // Print all unique artists from all collections
    public void printArtists() {
        ArrayList<String> artists = new ArrayList<>();
        for (Song s : singles.getSongs()) {
            if (!artists.contains(s.getArtist()))
                artists.add(s.getArtist());
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (!artists.contains(s.getArtist()))
                    artists.add(s.getArtist());
            }
        }
        for (Album album : albumLibrary) {
            if (!artists.contains(album.getArtist()))
                artists.add(album.getArtist());
            for (Song s : album.getSongs()) {
                if (!artists.contains(s.getArtist()))
                    artists.add(s.getArtist());
            }
        }
        System.out.println("Artists:");
        for (String a : artists) {
            System.out.println(a);
        }
    }
    
    // Print album titles
    public void printAlbums() {
        System.out.println("Albums in Library:");
        if (albumLibrary.isEmpty()) {
            System.out.println("No albums found.");
        } else {
            for (Album album : albumLibrary) {
                System.out.println(album.getName());
            }
        }
    }
    
    // Print favorite songs from all collections
    public void printFavorites() {
        System.out.println("Favorite Songs:");
        boolean found = false;
        for (Song s : singles.getSongs()) {
            if (s.getFavorite()) {
                System.out.println(s);
                found = true;
            }
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getFavorite()) {
                    System.out.println(s);
                    found = true;
                }
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getFavorite()) {
                    System.out.println(s);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No favorite songs found.");
        }
    }
    
    // Mark a song as favorite
    public void setFavorite(String songTitle) {
        Song song = getSongByTitle(songTitle);
        if (song != null) {
            song.favorite(true);
            System.out.println("Song marked as favorite: " + song);
        } else {
            System.out.println("Song not found in library.");
        }
    }
    
    // Rate a song
    public void rate(String songTitle, int rating) {
        Song song = getSongByTitle(songTitle);
        if (song != null) {
            song.rate(rating);
            System.out.println("Rated song: " + song);
        } else {
            System.out.println("Song not found in library.");
        }
    }
        
    // Search for a song by title in MusicStore
    public void searchStoreSongByTitle(String title) {
        Song song = store.getSong(title);
        if (song != null) {
            System.out.println("MusicStore: " + song);
        } else {
            System.out.println("Song not found in MusicStore.");
        }
    }
    
    // Search for songs by artist in MusicStore
    public void searchStoreSongsByArtist(String artist) {
        boolean found = false;
        for (Album album : store.getLibrary()) {
            for (Song song : album.getSongs()) {
                if (song.getArtist().equalsIgnoreCase(artist)) {
                    System.out.println("MusicStore: " + song);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No songs by " + artist + " found in MusicStore.");
        }
    }
    
    // Search for an album by title in MusicStore
    public void searchStoreAlbumByTitle(String albumTitle) {
        Album album = store.getAlbum(albumTitle);
        if (album != null) {
            album.print();
        } else {
            System.out.println("Album not found in MusicStore.");
        }
    }
    
    // Search for albums by artist in MusicStore
    public void searchStoreAlbumsByArtist(String artist) {
        boolean found = false;
        for (Album album : store.getLibrary()) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                album.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No albums by " + artist + " found in MusicStore.");
        }
    }
}
