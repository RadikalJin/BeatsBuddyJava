package com.joe.album.dao.jpa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.joe.album.domain.Album;
import com.joe.album.util.AlbumUtil;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component(LocalAlbumDaoImpl.BEAN_NAME)
public class LocalAlbumDaoImpl implements AlbumDao {

	public static final String BEAN_NAME = "albumsDao";

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	final Path fFilePath = Paths.get("C:\\Users\\Joseph\\workspace\\BeatsBuddyJava\\BeatsBuddyJava\\src\\main\\resources\\albumsByDate");

	@PersistenceContext
	private EntityManager em;

	/*
	* PARSING
	* */
	public List<Album> retrieveParsedUpcomingAlbums() throws Exception {
		return processLineByLine();
	}

	private List<Album> processLineByLine() throws IOException {
		List<Album> upcomingAlbums = new ArrayList<Album>();
		try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				Album record = processLine(scanner.nextLine());
				upcomingAlbums.add(record);
			}
		}
		AlbumUtil.log("List of albums is " + upcomingAlbums.size() + " entries long");
		return upcomingAlbums;
	}

	public Album processLine(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("@");
		if (scanner.hasNext()) {
			Calendar releaseDate = AlbumUtil.convertStringDateToSortableDate(scanner.next().trim());
			scanner.useDelimiter("\\|");
			String artist = scanner.next().trim().substring(2);
			String album = scanner.next().trim();
			Album record = new Album(artist, album, releaseDate);
			return record;
		} else {
			AlbumUtil.log("Empty or invalid line. Unable to process.");
			return new Album("", "", Calendar.getInstance());
		}
	}


	/*
	* HIBERNATE
	* */
	public void persistAlbum(Album album) {
		em.persist(album);
	}

	public void persistAlbums(List<Album> albums) {
		for (Album album : albums) {
			em.persist(album);
		}
	}

	public void updateAlbum(Album album) {
		Album existingVersionOfAlbum = em.find(Album.class, album.getId());
		existingVersionOfAlbum.setArtist(album.getArtist());
		existingVersionOfAlbum.setTitle(album.getTitle());
		existingVersionOfAlbum.setReleaseDate(album.getReleaseDate());
		em.persist(existingVersionOfAlbum);
	}

	public List<Album> getAllPersistedAlbums() {
		return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
	}

	public Album getAlbumById(Integer albumId) {
		return em.find(Album.class, albumId);
	}

	public List<Album> getAlbumsByArtistName(String artistName) {
		return em.createQuery("SELECT a FROM Album a WHERE artist = '" + artistName + "'", Album.class).getResultList();
	}

	public void deletePersistedAlbumById(Integer albumId) {
		Album album = em.find(Album.class, albumId);
		if (album != null) {
			em.remove(album);
		}
	}

	public void deleteAllPersistedAlbums() {
		em.createQuery("DELETE from Album").executeUpdate();
	}

	public List<Album> getAlbumsByAlbumTitle(String albumName) {
		return em.createQuery("SELECT a FROM Album a WHERE title = '" + albumName + "'", Album.class).getResultList();
	}

}

