package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.UserManager;

class UserManagerTest {
	UserManager manager;
	
	@BeforeEach
	void setup() {
		File dbFile = new File("userdatabase.txt");
        if (dbFile.exists()) {
            dbFile.delete();
        }
		manager = new UserManager();
	}

	@Test
	void testCreateUserAndLogin() {
		assertNotNull(manager.createUser("jeff", "greg123"));
		assertNotNull(manager.loginUser("jeff", "greg123"));
	}
	
	@Test
	void testCreateUserAndLoginFailWrongUser() {
		assertNotNull(manager.createUser("jeff", "greg123"));
		assertNull(manager.loginUser("geff", "greg123"));
	}
	
	@Test
	void testCreateUserAndLoginFailWrongPassword() {
		assertNotNull(manager.createUser("jeff", "greg123"));
		assertNull(manager.loginUser("jeff", "greg124"));
	}
	
	@Test
	void testCreateDuplicateUser() {
		assertNotNull(manager.createUser("jeff", "greg123"));
		assertNull(manager.createUser("jeff", "greg123"));
	}
	
	@Test
	void testSaveUserLoadAndLogin() {
		assertNotNull(manager.createUser("jeff", "greg123"));
		manager.saveUsers();
		manager = new UserManager();
		assertNotNull(manager.loginUser("jeff", "greg123"));
	}
}
