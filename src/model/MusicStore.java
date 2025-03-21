package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicStore {
	private Map<String, Album> store;
	
	// Every time a MusicStore class is initialized it is loaded up with 
	// the information needed from the text file(s).
	private MusicStore() {
		store = new HashMap<>();
		loadMusicData();
	}

	private void loadMusicData() {
		File albums = new File("src/albums");
		File albumsFile = new File(albums, "albums.txt"); 
		String line;
        String[] data = {};
        
		try (BufferedReader reader = new BufferedReader(new FileReader(albumsFile))) {
	        while ((line = reader.readLine()) != null) {
	            data = line.split(",");
	            Album album = new Album(data[0], data[1]);
	            store.put(album.getName().toLowerCase(), album);
	        }
	    } catch (Exception e) {
	        System.out.println("Library file scan failed."); 
	        e.printStackTrace();
	    }
		
		for (Album album : store.values()) {
			boolean firstLine = true;
			int index = 1;
			List<Song> songs = new ArrayList<>();
			albumsFile = new File(albums, album.getName()+"_"+album.getArtist()+".txt");
			
			try (BufferedReader reader = new BufferedReader(new FileReader(albumsFile))) {
		        while ((line = reader.readLine()) != null) {
		            if (firstLine) {
		            	data = line.split(",");
		            	album.setGenre(data[2]);
		            	album.setYear(Integer.parseInt(data[3]));
		            	firstLine = false;
		            } else {
		            	songs.add(new Song(line, data[1], data[0]));
		            	index++;
		            }
		        }
		        album.setSongs(Collections.unmodifiableList(songs));
		    } catch (Exception e) {
		        System.out.println("Album scan failed.");
		        e.printStackTrace();
		    }
		}
	}

	public Album getAlbum(String name) {
		return store.get(name.toLowerCase());
	}
	
	public Song getSong(String name) {
		for (Album album : store.values()) {
			for (Song song : album.getSongs()) {
				if (song.getName().equalsIgnoreCase(name)) return song;
			}
		}
		return null;
	}
	
	public ArrayList<Song> getSongsByTitle(String title) {
        ArrayList<Song> matches = new ArrayList<>(); 
        for (Album album : store.values()) {
            for (Song song : album.getSongs()) {
                if (song.getName().equalsIgnoreCase(title)) {
                    matches.add(song);
                }
            }
        }
        return matches;
    }
    
    public ArrayList<Song> searchSongByTitle(String title) {
        return getSongsByTitle(title);
    }
    
    public ArrayList<Song> searchSongsByArtist(String artist) {
        ArrayList<Song> matches = new ArrayList<>();
        for (Album album : store.values()) {
            for (Song song : album.getSongs()) {
                if (song.getArtist().equalsIgnoreCase(artist)) {
                    matches.add(song);
                }
            }
        }
        return matches;
    }
    
    public Album searchAlbumByTitle(String albumTitle) {
        return getAlbum(albumTitle);
    }
    
    public ArrayList<Album> searchAlbumsByArtist(String artist) {
        ArrayList<Album> matches = new ArrayList<>();
        for (Album album : store.values()) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                matches.add(album);
            }
        }
        return matches;
    }
}