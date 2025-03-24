package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	private Map<String, User> users;

	public UserManager() {
		this.users = new HashMap<>();
		loadUsers();
	}

	public User createUser(String username, String password) {
		if (users.containsKey(username)) {
			return null;
		}
		String hashedPassword = hashPassword(password);
		User user = new User(username, hashedPassword);
		users.put(username, user);
		return user;
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
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			md.update(password.getBytes(StandardCharsets.UTF_8));
			byte[] hashBytes = md.digest();

			String saltBase64 = Base64.getEncoder().encodeToString(salt);
			String hashBase64 = Base64.getEncoder().encodeToString(hashBytes);

			return saltBase64 + ":" + hashBase64;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to load SHA-256", e);
		}
	}

	private boolean verifyPassword(String password, String storedHash) {
		try {
			String[] parts = storedHash.split(":");
			byte[] salt = Base64.getDecoder().decode(parts[0]);
			String originalHash = parts[1];

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			md.update(password.getBytes(StandardCharsets.UTF_8));
			byte[] hashBytes = md.digest();
			String providedHashBase64 = Base64.getEncoder().encodeToString(hashBytes);

			return providedHashBase64.equals(originalHash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to load SHA-256", e);
		}
	}

	private void loadUsers() {
		File file = new File("userdatabase.txt");
		if (!file.exists()) {
			return;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String username = parts[0];
				String passwordHash = parts[1];

				LibraryModel library = null;
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username))) {
					library = (LibraryModel) ois.readObject();
				} catch (IOException | ClassNotFoundException e) {
					System.err.println("Error loading LibraryModel");
					e.printStackTrace();
				}

				User user = new User(username, passwordHash, library);
				users.put(username, user);
			}
		} catch (IOException e) {
			System.err.println("Error loading users");
			e.printStackTrace();
		}
	}

	public void saveUsers() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("userdatabase.txt"))) {
			for (User user : users.values()) {
				writer.write(user.getUsername() + "," + user.getPasswordHash());
				writer.newLine();

				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user.getUsername()))) {
					oos.writeObject(user.getLibrary());
				} catch (IOException e) {
					System.err.println("Error saving LibraryModel");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.err.println("Error saving users");
			e.printStackTrace();
		}
	}
}
