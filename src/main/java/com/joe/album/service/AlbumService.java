package com.joe.album.service;

import com.joe.album.domain.Album;
import com.joe.album.dto.AlbumDto;

import java.util.List;

public interface AlbumService {

    public List<AlbumDto> retrieveUpcomingAlbums() throws Exception;
    public List<AlbumDto> getAllPersistedAlbums() throws Exception;
    public void persistAlbum(AlbumDto albumDto) throws Exception;
    public void persistAlbums(List<AlbumDto> albums) throws Exception;
    public void deleteAllRecords() throws Exception;

    public AlbumDto getAlbumById(Integer albumId) throws Exception;
    public void deleteAlbumById(Integer albumId);
    public List<AlbumDto> getAlbumsByAlbumTitle(String albumName);
    public List<AlbumDto> getAlbumsByArtistName(String artistName);

    public void updateAlbum(final AlbumDto albumDto);

}
