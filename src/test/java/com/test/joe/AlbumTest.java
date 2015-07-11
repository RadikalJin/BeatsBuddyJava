package com.test.joe;

import com.joe.album.dto.AlbumDto;
import com.joe.album.service.AlbumService;
import com.joe.album.util.AlbumUtil;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.*;

public class AlbumTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	AlbumService albumService = (AlbumService) context.getBean("albumService");
	List<AlbumDto> parsedAlbums;
	Boolean keepData = false;

	@Before
	public void setUp() throws Exception {
		parsedAlbums = albumService.retrieveUpcomingAlbums();
	}

	@After
	public void tearDown() throws Exception {
		if (! keepData) {
			albumService.deleteAllRecords();
		}
	}

    //@Ignore
	@Test
	public void persistParsedAlbums() throws Exception {
		keepData = true;
		albumService.persistAlbums(parsedAlbums);
	}


	/*
	* PARSING
	*/
	@Test
	public void testGetListOfAllUpcomingAlbums() throws Exception {
		assertNotEquals(0, parsedAlbums.size());
	}

	@Test
	public void testFindingNameThatIsNotPresent() throws Exception {
		assertEquals(0, AlbumUtil.findAlbumsForArtistInList(parsedAlbums, "Prince").size());
	}

	@Test
	public void testFindingNameThatIsPresent() throws Exception {
		assertEquals(1, AlbumUtil.findAlbumsForArtistInList(parsedAlbums, "DMX").size());
	}


	/*
	*  PERSISTENCE
	*/
	@Test
	public void testPersistingSingleAlbum() throws Exception {
		AlbumDto testAlbum = parsedAlbums.get(0);
		albumService.persistAlbum(testAlbum);
		List<AlbumDto> allPersistedAlbums = albumService.getAllPersistedAlbums();
		List<AlbumDto> albumsForArtistInList = AlbumUtil.findAlbumsForArtistInList(allPersistedAlbums, testAlbum.getArtist());
		assertFalse(CollectionUtils.isEmpty(albumsForArtistInList));
	}

	@Test
	public void testGetPersistedAlbum() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		List<AlbumDto> dbReturnedAlbums = albumService.getAllPersistedAlbums();
		assertTrue(dbReturnedAlbums.size() > 0);
	}

	@Test
	public void testUpdatingPersistedAlbum() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		List<AlbumDto> dbReturnedAlbums = albumService.getAllPersistedAlbums();
		assertTrue(dbReturnedAlbums.size() > 0);
		AlbumDto persistedAlbumDto = dbReturnedAlbums.get(10);
		List<AlbumDto> albumsByArtistName = albumService.getAlbumsByArtistName(persistedAlbumDto.getArtist());
		assertEquals(albumsByArtistName.size(), 1);
		persistedAlbumDto.setTitle("Something new");
		albumService.updateAlbum(persistedAlbumDto);
		assertEquals(albumService.getAlbumsByArtistName(persistedAlbumDto.getArtist()).size(), 1);
	}

	@Test
	public void testGetPersistedAlbumById() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		AlbumDto persistedAlbum = albumService.getAllPersistedAlbums().get(0);
		assertNotEquals(null, albumService.getAlbumById(Integer.parseInt(persistedAlbum.getId())));
	}

	@Test
	public void testDeletePersistedAlbumById() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		AlbumDto persistedAlbum = albumService.getAllPersistedAlbums().get(0);
		Integer persistedAlbumId = Integer.parseInt(persistedAlbum.getId());
		assertNotNull(albumService.getAlbumById(persistedAlbumId));
		albumService.deleteAlbumById(persistedAlbumId);
		assertNull(albumService.getAlbumById(persistedAlbumId));
	}

	@Test
	public void testGetAlbumByArtistName() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		String artistName = parsedAlbums.get(0).getArtist();
		List<AlbumDto> albumsForArtist = albumService.getAlbumsByArtistName(artistName);
		assertTrue(albumsForArtist.size() > 0);
	}

	@Test
	public void testSameAlbumDoesNotSaveTwice() throws Exception {
		albumService.persistAlbums(parsedAlbums);
		List<AlbumDto> dbReturnedAlbums = albumService.getAllPersistedAlbums();
		AlbumDto persistedAlbumDto = dbReturnedAlbums.get(10);
		AlbumDto newAlbum = new AlbumDto();
		newAlbum.setArtist(persistedAlbumDto.getArtist());
		newAlbum.setTitle(persistedAlbumDto.getTitle());
		newAlbum.setReleaseDate(persistedAlbumDto.getReleaseDate());
		albumService.persistAlbum(newAlbum);
		System.out.println(albumService.getAlbumsByArtistName(persistedAlbumDto.getArtist()).size());
		assertEquals(albumService.getAlbumsByArtistName(persistedAlbumDto.getArtist()).size(), 1);
	}

}