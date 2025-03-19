package model;

public class User {
	private String username;
	private String passwordHash;
	private LibraryModel userLibrary;
	
	public User(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.userLibrary = new LibraryModel();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public LibraryModel getLibrary() {
		return userLibrary;
	}
}
