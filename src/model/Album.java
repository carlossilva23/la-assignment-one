package model;

public class Album extends Playlist {
    private String artist;
    private String genre;
    private int year;
    
    public Album(String name, String artist) {
        super(name);
        this.artist = artist;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public void print() {
        System.out.println(getName() + " by " + artist + " (" + year + ", " + genre + ")");
        System.out.println("Songs:");
        for (Song song : super.getSongs()) {
            System.out.println("  - " + song.getName());
        }
    }
    
    public String toString() {
    	return getName() + " by " + artist + " (" + year + ", " + genre + ")";
    }
}
