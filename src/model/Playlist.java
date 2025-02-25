package model;

import java.util.ArrayList;

public class Playlist {
	private String name;
	private ArrayList<Song> songs = new ArrayList<>();
	
	public Playlist(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void removeSong(String song) {
		for (Song i: songs) {
			if (i.getName().equals(song)) {
				songs.remove(i);
			}
		}
	}
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs);
	}
	
	public void print() {
		System.out.println(this.name + ":");
		for (Song song : songs) {
			System.out.println(song);
		}
	}
		
}
