package com.joe.user.service;

import com.joe.album.domain.Album;
import com.joe.album.dto.AlbumDto;
import com.joe.album.service.AlbumService;
import com.joe.album.service.AlbumServiceImpl;
import com.joe.user.dao.jpa.UserDao;
import com.joe.user.dao.jpa.UserDaoImpl;
import com.joe.user.domain.User;
import com.joe.user.domain.UserAlbum;
import com.joe.user.dto.UserAlbumDto;
import com.joe.user.dto.UserDto;
import com.joe.user.util.UserUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(UserServiceImpl.BEAN_NAME)
public class UserServiceImpl implements UserService {

	public static final String BEAN_NAME = "userService";

	@Resource(name = UserDaoImpl.BEAN_NAME)
	private UserDao usersDao;

	@Resource(name = AlbumServiceImpl.BEAN_NAME)
	private AlbumService albumService;


	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<UserDto> getAllUsers() throws Exception {
		return UserUtil.convertUserDomainsToDtos(usersDao.getAllUsers());
	}

	@Transactional
	public void persistUser(UserDto userDto) throws Exception {
		usersDao.persistUser(UserUtil.convertUserDtoToDomain(userDto));
	}

	@Transactional
	public void persistUsers(List<UserDto> userDtos) throws Exception {
		usersDao.persistUsers(UserUtil.convertUserDtosToDomains(userDtos));
	}

	@Transactional
	public UserDto getUserById(Integer userId) throws Exception {
		User userById = usersDao.getUserById(userId);
		if (userById == null) {
			return null;
		} else {
			return UserUtil.convertUserDomainToDto(userById);
		}
	}

	@Transactional
	public void deleteUserById(Integer userId) {
		usersDao.deleteUserById(userId);
	}

	@Transactional
	public void deleteAllUsers() {
		usersDao.deleteAllUsers();
	}

	@Transactional
	public UserDto getUserByUsername(String userName) {
		return UserUtil.convertUserDomainToDto(usersDao.getUserByUsername(userName));
	}

	@Transactional
	public void updateUser(final UserDto userDto) {
		User user = UserUtil.convertUserDtoToDomain(userDto);
		usersDao.updateUser(user);
	}

	@Transactional
	public void persistAlbumToUser(Integer userId, Integer albumId) throws Exception {
		User existingUser = usersDao.getUserById(userId);
		AlbumDto existingAlbum = albumService.getAlbumById(albumId);
		if (existingUser != null && existingAlbum != null) {
			UserAlbum userAlbum = new UserAlbum(userId, albumId);
			usersDao.persistUserAlbum(userAlbum);
		}
	}

	@Transactional
	public List<UserAlbumDto> getUserAlbumsByUserId(Integer userId) {
		return UserUtil.convertUserAlbumDomainsToDtos(usersDao.getUserAlbumsByUserId(userId));
	}

}