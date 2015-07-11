package com.test.joe;

import com.joe.album.dto.AlbumDto;
import com.joe.album.service.AlbumService;
import com.joe.album.service.AlbumServiceImpl;
import com.joe.user.dto.UserAlbumDto;
import com.joe.user.dto.UserDto;
import com.joe.user.service.UserService;
import com.joe.user.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	UserService userService = (UserService) context.getBean("userService");
	AlbumService albumService = (AlbumService) context.getBean("albumService");
	List<UserDto> testUsers;
	Boolean keepData = false;

/*	@Resource(name = UserServiceImpl.BEAN_NAME)
	private UserService userService;*/

/*	@Resource(name = AlbumServiceImpl.BEAN_NAME)
	private AlbumService albumService;*/

	@Before
	public void setUp() throws Exception {
		testUsers = new ArrayList<>();
		UserDto testUser1 = new UserDto();
		testUser1.setUsername("TEST_USER_1");
		testUser1.setPassword("TEST_PASSWORD_1");
		UserDto testUser2 = new UserDto();
		testUser2.setUsername("TEST_USER_2");
		testUser2.setPassword("TEST_PASSWORD_2");
		UserDto testUser3 = new UserDto();
		testUser3.setUsername("TEST_USER_3");
		testUser3.setPassword("TEST_PASSWORD_3");
		testUsers.add(testUser1);
		testUsers.add(testUser2);
		testUsers.add(testUser3);
	}

	@After
	public void tearDown() throws Exception {
		if (! keepData) {
			userService.deleteAllUsers();
		}
	}

  /*@Ignore
	@Test
	public void persistParsedUsers() throws Exception {
		keepData = true;
		userService.persistUsers(testUsers);
	}*/


	/*
	*  PERSISTENCE
	*/
	@Test
	public void testPersistingSingleUserUsingHibernate() throws Exception {
		UserDto testUser = testUsers.get(0);
		try {
			userService.getUserByUsername(testUser.getUsername());
		} catch (NoResultException e) {
			System.out.println("Caught exception as expected when finding user by name, when does not exist");
		}
		userService.persistUser(testUser);
		assertNotNull(userService.getUserByUsername(testUser.getUsername()));
	}

	@Test
	public void testGetPersistedUserUsingHibernate() throws Exception {
		userService.persistUsers(testUsers);
		List<UserDto> dbReturnedUsers = userService.getAllUsers();
		assertTrue(dbReturnedUsers.size() > 0);
	}

	@Test
	public void testUpdatingPersistedUserUsingHibernate() throws Exception {
		UserDto prePersistUser = testUsers.get(0);
		userService.persistUser(prePersistUser);
		UserDto postPersistUser = userService.getUserByUsername(prePersistUser.getUsername());
		assertNotNull(postPersistUser);
		assertEquals(prePersistUser.getPassword(), postPersistUser.getPassword());
		postPersistUser.setPassword("Something new");
		userService.updateUser(postPersistUser);
		UserDto updatedUser = userService.getUserByUsername(prePersistUser.getUsername());
		assertNotNull(updatedUser);
		assertNotEquals(prePersistUser.getPassword(), postPersistUser.getPassword());
	}

	@Test
	public void testGetPersistedUserById() throws Exception {
		userService.persistUsers(testUsers);
		UserDto persistedUser = userService.getAllUsers().get(0);
		assertNotEquals(null, userService.getUserById(Integer.parseInt(persistedUser.getId())));
	}

	@Test
	public void testDeletePersistedUserById() throws Exception {
		userService.persistUsers(testUsers);
		UserDto persistedUser = userService.getAllUsers().get(0);
		Integer persistedUserId = Integer.parseInt(persistedUser.getId());
		assertNotNull(userService.getUserById(persistedUserId));
		userService.deleteUserById(persistedUserId);
		assertNull(userService.getUserById(persistedUserId));
	}

	@Test
	public void testGetAlbumsByUserId() throws Exception {
		userService.persistUsers(testUsers);
		UserDto persistedUser = userService.getAllUsers().get(0);
		AlbumDto testAlbum = new AlbumDto();
		testAlbum.setArtist("TEST_ARTIST");
		testAlbum.setTitle("TEST_TITLE");
		testAlbum.setReleaseDate(Calendar.getInstance());
		albumService.persistAlbum(testAlbum);
		List<AlbumDto> albumsByArtistName = albumService.getAlbumsByArtistName(testAlbum.getArtist());
		AlbumDto persistedAlbum = albumsByArtistName.get(0);
		userService.persistAlbumToUser(Integer.parseInt(persistedUser.getId()), Integer.parseInt(persistedAlbum.getId()));
		List<UserAlbumDto> userAlbumsByUserId = userService.getUserAlbumsByUserId(Integer.parseInt(persistedUser.getId()));
		assertTrue(userAlbumsByUserId.size() > 0);
	}

}