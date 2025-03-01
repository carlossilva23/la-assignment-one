package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MusicStore {
	private ArrayList<Album> library = new ArrayList<>();
	
	public MusicStore() {
		File albums = new File("src/albums");
		File albumsFile = new File(albums, "albums.txt"); 
		String line;
        String[] data = {};
		try (BufferedReader reader = new BufferedReader(new FileReader(albumsFile))) {
	        while ((line = reader.readLine()) != null) {
	            data = line.split(",");
	            library.add(new Album(data[0], data[1]));
	        }
	    } catch (Exception e) {
	        System.out.println("Library file scan failed."); 
	    }
		
		boolean firstLine;
		for (Album album : library) {
			firstLine = true;
			albumsFile = new File(albums, album.getName()+"_"+album.getArtist()+".txt");
			try (BufferedReader reader = new BufferedReader(new FileReader(albumsFile))) {
		        while ((line = reader.readLine()) != null) {
		            if (firstLine) {
		            	data = line.split(",");
		            	album.setGenre(data[2]);
		            	album.setYear(Integer.parseInt(data[3]));
		            	firstLine = false;
		            } else {
		            	album.addSong(new Song(line, data[1], data[0]));
		            }
		        }
		    } catch (Exception e) {
		        System.out.println("Album scan failed.");
		        e.printStackTrace();
		    }
		}
	}

	public Album getAlbum(String name) {
		for (Album album : library) {
			if (album.getName().equalsIgnoreCase(name)) return album;
		}
		return null;
	}
	
	public Song getSong(String name) {
		for (Album album : library) {
			for (Song song : album.getSongs()) {
				if (song.getName().equalsIgnoreCase(name)) return song;
			}
		}
		return null;
	}
	
	public ArrayList<Song> getSongsByTitle(String title) {
        ArrayList<Song> matches = new ArrayList<>(); 
        for (Album album : library) {
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
        for (Album album : library) {
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
        for (Album album : library) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                matches.add(album);
            }
        }
        return matches;
    }
}