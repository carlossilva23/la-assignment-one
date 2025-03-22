package model;

public class User {
	private String username;
	private String passwordHash;
	private LibraryModel userLibrary;
	
	// For creating new users
	public User(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.userLibrary = new LibraryModel();
	}
	
	// For loading users
	public User(String username, String passwordHash, LibraryModel existingLibrary) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.userLibrary = existingLibrary;
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
