package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	private Map<String, User> users;
	private static final String USER_FILE = "users.json";
	
	public UserManager() {
		this.users = new HashMap<>();
		loadUsers();
	}
	
	public boolean createUser(String username, String password) {
		if (users.containsKey(username)) {
			return false;
		}
		String hashedPassword = hashPassword(password);
		User newUser = new User(username, hashedPassword);
		users.put(username,  newUser);
		saveUsers();
		return true;
	}
	
	public User loginUser(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (verifyPassword(password, user.getPasswordHash())) {
			return user;
		}
		return null;
	}
	
	private String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] salt = new byte[16];
			new SecureRandom().nextBytes(salt);
			md.update(salt);
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
		} catch (Exception e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}
	
	private boolean verifyPassword(String password, String storedHash) {
		try {
			String[] parts = storedHash.split(":");
			byte[] salt = Base64.getDecoder().decode(parts[0]);
			byte[] storedHashedPassword = Base64.getDecoder().decode(parts[1]);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			return Arrays.equals(storedHashedPassword, hashedPassword);
		} catch (Exception e) {
			return false;
		}
	}
	
	private void loadUsers() {
		File file = new File(USER_FILE);
		if (!file.exists()) return;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					users.put(parts[0], new User(parts[0], parts[1]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveUsers() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
			for (User user : users.values()) {
				writer.write(user.getUsername() + "," + user.getPasswordHash());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
