package com.joe.user.dao.jpa;

import com.joe.user.domain.User;
import com.joe.user.domain.UserAlbum;

import java.util.List;

public interface UserDao {

	public void persistUser(User user);
	public void persistUsers(List<User> users);
	public void updateUser(User user);
	public List<User> getAllUsers() throws Exception;
	public User getUserById(Integer userId) throws Exception;
	public User getUserByUsername(String username);
	public void deleteUserById(Integer userId);
	public void deleteAllUsers();

	public void persistUserAlbum(UserAlbum userAlbum);
	public List<UserAlbum> getUserAlbumsByUserId(Integer userId);

}
