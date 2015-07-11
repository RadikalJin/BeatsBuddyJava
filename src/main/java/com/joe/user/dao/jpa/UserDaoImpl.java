package com.joe.user.dao.jpa;

import com.joe.album.domain.Album;
import com.joe.user.domain.User;
import com.joe.user.domain.UserAlbum;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component(UserDaoImpl.BEAN_NAME)
public class UserDaoImpl implements UserDao {

	public static final String BEAN_NAME = "userDao";

	@PersistenceContext
	private EntityManager em;


	public void persistUser(User user) {
		em.persist(user);
	}

	public void persistUsers(List<User> users) {
		for (User user : users) {
			em.persist(user);
		}
	}

	public void updateUser(User user) {
		User existingVersionOfUser = em.find(User.class, user.getId());
		existingVersionOfUser.setUsername(user.getUsername());
		existingVersionOfUser.setPassword(user.getPassword());
		em.persist(existingVersionOfUser);
	}

	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	public User getUserById(Integer userId) {
		return em.find(User.class, userId);
	}

	public User getUserByUsername(String username) {
		return em.createQuery("SELECT u From User u WHERE username='" + username + "'", User.class).getSingleResult();
	}

	public void deleteUserById(Integer userId) {
		User user = em.find(User.class, userId);
		if (user != null) {
			em.remove(user);
		}
	}

	public void deleteAllUsers() {
		em.createQuery("DELETE from User").executeUpdate();
	}


	public List<UserAlbum> getUserAlbumsByUserId(Integer userId) {
		return em.createQuery("SELECT ua from UserAlbum ua WHERE userId = '" + userId + "'", UserAlbum.class).getResultList();
	}

	public void persistUserAlbum(UserAlbum userAlbum) {
		em.persist(userAlbum);
	}

}
