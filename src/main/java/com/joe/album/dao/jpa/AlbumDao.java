package com.joe.album.dao.jpa;

import java.util.List;

import com.joe.album.domain.Album;

public interface AlbumDao {

	public void persistAlbum(Album album);
	public void persistAlbums(List<Album> albums);
	public void updateAlbum(Album album);
	public List<Album> getAllPersistedAlbums() throws Exception;
	public Album getAlbumById(Integer albumId) throws Exception;
	public void deletePersistedAlbumById(Integer albumId);
	public void deleteAllPersistedAlbums() throws Exception;
	public List<Album> getAlbumsByAlbumTitle(String albumName);
	public List<Album> getAlbumsByArtistName(String artistName);

	public List<Album> retrieveParsedUpcomingAlbums() throws Exception;

}
