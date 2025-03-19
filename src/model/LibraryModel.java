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
        store = MusicStore.getInstance();  
    }
    
    public ArrayList<Song> getStoreSongsByTitle(String title) {
        return store.getSongsByTitle(title); 
    }
    
    public ArrayList<Song> getLibrarySongsByTitle(String title) {
        ArrayList<Song> matches = new ArrayList<>();
        for (Song s : singles.getSongs()) {
            if (s.getName().equalsIgnoreCase(title))
                matches.add(s);
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getName().equalsIgnoreCase(title))
                    matches.add(s);
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getName().equalsIgnoreCase(title))
                    matches.add(s);
            }
        }
        return matches;
    }
        
    public void addSong(Song song) {
        if (song != null) {
            singles.addSong(song);
        }
    }
    
    public void favoriteSong(Song song) {
        if (song != null) {
            song.favorite(true);
        }
    }
    
    public void rateSong(Song song, int rating) {
        if (song != null) {
            song.rate(rating);
        }
    }
    
    public void addAlbum(String albumTitle) {
        Album album = store.getAlbum(albumTitle);
        if (album != null) {
            albumLibrary.add(album);
        }
    }
    
    public void createPlaylist(String playlistName) {
        userPlaylists.add(new Playlist(playlistName));
    }
    
    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = getUserPlaylist(playlistName);
        if (playlist != null && song != null) {  
            playlist.addSong(song);
        }
    }
    
    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = getUserPlaylist(playlistName);
        if (playlist != null) {
            playlist.removeSong(songTitle);
        }
    }
    
    public Playlist getUserPlaylist(String playlistName) {
        for (Playlist p : userPlaylists) {
            if (p.getName().equalsIgnoreCase(playlistName)) {
                return p;
            }
        }
        return null;
    }
        
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> all = new ArrayList<>();
        all.addAll(singles.getSongs());
        for (Playlist p : userPlaylists) {
            all.addAll(p.getSongs());
        }
        for (Album album : albumLibrary) {
            all.addAll(album.getSongs());
        }
        return all;
    }
    
    public ArrayList<String> getAllArtists() {
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
        return artists;
    }
    
    public ArrayList<Album> getAllAlbums() {
        return new ArrayList<>(albumLibrary);
    }
    
    public ArrayList<Playlist> getAllPlaylists() {
        return new ArrayList<>(userPlaylists);
    }
    
    public ArrayList<Song> getFavoriteSongs() {
        ArrayList<Song> fav = new ArrayList<>();
        for (Song s : singles.getSongs()) {
            if (s.getFavorite())
                fav.add(s);
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getFavorite())
                    fav.add(s);
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getFavorite())
                    fav.add(s);
            }
        }
        return fav;
    }
        
    public ArrayList<Song> searchLibrarySongByTitle(String title) {
        return getLibrarySongsByTitle(title);
    }
    
    public ArrayList<Song> searchLibrarySongsByArtist(String artist) {
        ArrayList<Song> matches = new ArrayList<>();
        for (Song s : singles.getSongs()) {
            if (s.getArtist().equalsIgnoreCase(artist))
                matches.add(s);
        }
        for (Playlist p : userPlaylists) {
            for (Song s : p.getSongs()) {
                if (s.getArtist().equalsIgnoreCase(artist))
                    matches.add(s);
            }
        }
        for (Album album : albumLibrary) {
            for (Song s : album.getSongs()) {
                if (s.getArtist().equalsIgnoreCase(artist))
                    matches.add(s);
            }
        }
        return matches;
    }
    
    public Album searchLibraryAlbumByTitle(String albumTitle) {
        for (Album album : albumLibrary) {
            if (album.getName().equalsIgnoreCase(albumTitle))
                return album;
        }
        return null;
    }
    
    public ArrayList<Album> searchLibraryAlbumsByArtist(String artist) {
        ArrayList<Album> matches = new ArrayList<>();
        for (Album album : albumLibrary) {
            if (album.getArtist().equalsIgnoreCase(artist))
                matches.add(album);
        }
        return matches;
    }
        
    public ArrayList<Song> searchStoreSongByTitle(String title) {
        return store.searchSongByTitle(title);
    }
    
    public ArrayList<Song> searchStoreSongsByArtist(String artist) {
        return store.searchSongsByArtist(artist);
    }
    
    public Album searchStoreAlbumByTitle(String albumTitle) {
        return store.searchAlbumByTitle(albumTitle);
    }
    
    public ArrayList<Album> searchStoreAlbumsByArtist(String artist) {
        return store.searchAlbumsByArtist(artist);
    }
}
