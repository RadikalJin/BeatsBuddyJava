package com.joe.album.service;

import com.joe.album.dao.jpa.AlbumDao;
import com.joe.album.dao.jpa.LocalAlbumDaoImpl;
import com.joe.album.domain.Album;
import com.joe.album.dto.AlbumDto;
import com.joe.album.util.AlbumUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component(AlbumServiceImpl.BEAN_NAME)
public class AlbumServiceImpl implements AlbumService {

	public static final String BEAN_NAME = "albumService";

	@Resource(name = LocalAlbumDaoImpl.BEAN_NAME)
	private AlbumDao albumsDao;

	/*
	* PARSING
	* */
	public List<AlbumDto> retrieveUpcomingAlbums() throws Exception {
		return AlbumUtil.convertAlbumDomainsToDtos(albumsDao.retrieveParsedUpcomingAlbums());
	}

	/*
	* HIBERNATE
	* */
	@Transactional
 	public List<AlbumDto> getAllPersistedAlbums() throws Exception {
		return AlbumUtil.convertAlbumDomainsToDtos(albumsDao.getAllPersistedAlbums());
	}

	@Transactional
	public void persistAlbum(AlbumDto albumDto) throws Exception {
		Album albumCandidate = AlbumUtil.convertAlbumDtoToDomain(albumDto);
		if (! albumIsAlreadyPersisted(albumCandidate)) {
			albumsDao.persistAlbum(albumCandidate);
		}
	}

	@Transactional
	public void persistAlbums(List<AlbumDto> albumDtos) throws Exception {
		albumsDao.persistAlbums(AlbumUtil.convertAlbumDtosToDomains(albumDtos));
	}

	@Transactional
	public AlbumDto getAlbumById(Integer albumId) throws Exception {
		Album albumById = albumsDao.getAlbumById(albumId);
		if (albumById == null) {
			return null;
		} else {
			return AlbumUtil.convertAlbumDomainToDto(albumById);
		}
	}

	@Transactional
	public void deleteAllRecords() throws Exception {
		albumsDao.deleteAllPersistedAlbums();
	}

	@Transactional
	public void deleteAlbumById(Integer albumId) {
		albumsDao.deletePersistedAlbumById(albumId);
	}

	@Transactional
	public List<AlbumDto> getAlbumsByAlbumTitle(String albumName) {
		return AlbumUtil.convertAlbumDomainsToDtos(albumsDao.getAlbumsByAlbumTitle(albumName));
	}

	@Transactional
	public List<AlbumDto> getAlbumsByArtistName(String artistName) {
		return AlbumUtil.convertAlbumDomainsToDtos(albumsDao.getAlbumsByArtistName(artistName));
	}

	@Transactional
	public void updateAlbum(final AlbumDto albumDto) {
		Album album = AlbumUtil.convertAlbumDtoToDomain(albumDto);
		albumsDao.updateAlbum(album);
	}

	public Boolean albumIsAlreadyPersisted(Album album) throws Exception {
		List<Album> allPersistedAlbums = albumsDao.getAllPersistedAlbums();
		Boolean matchFound = false;
		for (Album allPersistedAlbum : allPersistedAlbums) {
			if (album.equals(allPersistedAlbum)) {
				matchFound = true;
			}
		}
		return matchFound;
	}
}