package model;

public class UserSong extends Song{
	private Rating rating;
	private boolean favorite;

	public UserSong(String name, String artist, String album) {
		super(name, artist, album);
		this.rating = null;
		this.favorite = false;
	}

	public boolean getFavorite() {
		return this.favorite;
	}
	
	public void favorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	@Override
	public String toString() {
		return "Title: " + super.getName() + ", Album: " + super.getAlbum() + ", Artist: " + 
				super.getArtist() + ", Rating: " + rating.getValue() + ", Favorite: " + favorite;
	}
}
