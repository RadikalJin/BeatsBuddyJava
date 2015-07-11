package com.joe.album.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "ALBUM")
public class Album implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ARTIST_NAME", nullable = false)
	private String artist;

	@Column(name = "TITLE", nullable = true)
	private String title;

	@Column(name = "RELEASE_DATE", nullable = true)
	private Calendar releaseDate;

	public Album() {
		super();
	}

	public Album(String artist, String albumTitle, Calendar releaseDate){
		this.artist = artist;
		this.title = albumTitle;
		this.releaseDate = releaseDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Album details: {" +
				"Id = " + id +
				", Artist = '" + artist + '\'' +
				", Title = '" + title + '\'' +
				", Release Date = " + releaseDate.getTimeInMillis() +
				'}';
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Album album = (Album) o;

		if (!artist.equals(album.artist)) return false;
		if (!releaseDate.equals(album.releaseDate)) return false;
		if (!title.equals(album.title)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = artist.hashCode();
		result = 31 * result + title.hashCode();
		result = 31 * result + releaseDate.hashCode();
		return result;
	}
}
