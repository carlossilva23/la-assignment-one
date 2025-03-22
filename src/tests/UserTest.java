package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.LibraryModel;
import model.User;

class UserTest {

	@Test
	void testCreateNewUser() {
		User user = new User("jeff", "greg");
		assertTrue(user.getLibrary() instanceof LibraryModel);
	}
	
	@Test
	void testCreateUserExistingLibrary() {
		LibraryModel library = new LibraryModel();
		User user = new User("jeff", "greg", library);
		assertTrue(user.getLibrary() == library);
	}

	@Test
	void testCreateUserGetUsername() {
		User user = new User("jeff", "greg");
		assertEquals(user.getUsername(), "jeff");
	}
	
	@Test
	void testCreateUserGetPasswordHash() {
		User user = new User("jeff", "greg");
		assertEquals(user.getPasswordHash(), "greg");
	}
}
