package com.joe.user.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER_ALBUM")
public class UserAlbum implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "USER_ID", nullable = false)
	private Integer userId;

	@Column(name = "ALBUM_ID", nullable = false)
	private Integer albumId;

	public UserAlbum() {
		super();
	}

	public UserAlbum(Integer userId, Integer albumId){
		this.userId = userId;
		this.albumId = albumId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAlbum userAlbum = (UserAlbum) o;

		if (!albumId.equals(userAlbum.albumId)) return false;
		if (!userId.equals(userAlbum.userId)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userId.hashCode();
		result = 31 * result + albumId.hashCode();
		return result;
	}
}
