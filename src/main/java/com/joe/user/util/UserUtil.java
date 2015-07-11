package com.joe.user.util;

import com.joe.user.domain.User;
import com.joe.user.domain.UserAlbum;
import com.joe.user.dto.UserAlbumDto;
import com.joe.user.dto.UserDto;

import java.util.*;

public class UserUtil {

    public static UserDto convertUserDomainToDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getId() != null) {
            userDto.setId(String.valueOf(user.getId()));
        }
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


    public static List<UserDto> convertUserDomainsToDtos(List<User> userDomains) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User userDomain : userDomains) {
            userDtos.add(convertUserDomainToDto(userDomain));
        }
        return userDtos;
    }

    public static User convertUserDtoToDomain(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(Integer.parseInt(userDto.getId()));
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static List<User> convertUserDtosToDomains(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            users.add(convertUserDtoToDomain(userDto));
        }
        return users;
    }

    public static UserAlbumDto convertUserAlbumDomainToDto(UserAlbum userAlbum) {
        UserAlbumDto userAlbumDto = new UserAlbumDto();
        if (userAlbum.getId() != null) {
            userAlbumDto.setId(userAlbum.getId());
        }
        userAlbumDto.setUserId(userAlbum.getUserId());
        userAlbumDto.setAlbumId(userAlbum.getAlbumId());
        return userAlbumDto;
    }

    public static List<UserAlbumDto> convertUserAlbumDomainsToDtos(List<UserAlbum> userAlbumDomains) {
        List<UserAlbumDto> userAlbumDtos = new ArrayList<>();
        for (UserAlbum userAlbumDomain : userAlbumDomains) {
            userAlbumDtos.add(convertUserAlbumDomainToDto(userAlbumDomain));
        }
        return userAlbumDtos;
    }

    public static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
