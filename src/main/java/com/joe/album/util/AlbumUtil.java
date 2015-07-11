package com.joe.album.util;

import com.joe.album.domain.Album;
import com.joe.album.dto.AlbumDto;

import java.util.*;

public class AlbumUtil {

    public static AlbumDto convertAlbumDomainToDto(Album album) {
        AlbumDto albumDto = new AlbumDto();
        if (album.getId() != null) {
            albumDto.setId(String.valueOf(album.getId()));
        }
        albumDto.setTitle(album.getTitle());
        albumDto.setArtist(album.getArtist());
        albumDto.setReleaseDate(album.getReleaseDate());
        return albumDto;
    }

    public static List<AlbumDto> convertAlbumDomainsToDtos(List<Album> albumDomains) {
        List<AlbumDto> albumDtos = new ArrayList<>();
        for (Album albumDomain : albumDomains) {
            albumDtos.add(convertAlbumDomainToDto(albumDomain));
        }
        return albumDtos;
    }

    public static Album convertAlbumDtoToDomain(AlbumDto albumDto) {
        Album album = new Album();
        if (albumDto.getId() != null) {
            album.setId(Integer.parseInt(albumDto.getId()));
        }
        album.setTitle(albumDto.getTitle());
        album.setArtist(albumDto.getArtist());
        album.setReleaseDate(albumDto.getReleaseDate());
        return album;
    }

    public static List<Album> convertAlbumDtosToDomains(List<AlbumDto> albumDtos) {
        List<Album> albums = new ArrayList<>();
        for (AlbumDto albumDto : albumDtos) {
            albums.add(convertAlbumDtoToDomain(albumDto));
        }
        return albums;
    }

    public static List<AlbumDto> findAlbumsForArtistInList(List<AlbumDto> upcomingAlbums, String artistName) throws Exception {
        List<AlbumDto> matchingAlbums = new ArrayList<AlbumDto>();
        for(AlbumDto albumCandidate:upcomingAlbums) {
            if (albumCandidate.getArtist().toLowerCase().indexOf(artistName.toLowerCase()) >= 0){
                matchingAlbums.add(albumCandidate);
            }
        }
        return matchingAlbums;
    }

    public static Calendar convertStringDateToSortableDate(String stringDate) {

        Map<String, Integer> months = new HashMap<>();
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);

        String[] processedDate = stringDate.split(" ");
        Integer monthNumeric = months.get(processedDate[0]);
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.set(2015, monthNumeric, Integer.parseInt(processedDate[1]));
        return releaseDate;
    }

    public static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

}
