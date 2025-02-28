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
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName() + " by " + artist + " (" + year + ", " + genre + ")\nSongs:");
        for (Song song : super.getSongs()) {
            sb.append("\n  - ").append(song.getName());
        }
        return sb.toString();
    }
}
