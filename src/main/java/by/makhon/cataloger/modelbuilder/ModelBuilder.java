package by.makhon.cataloger.modelbuilder;

import by.makhon.cataloger.bean.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ModelBuilder creates model for correct building HTML view
 */
public class ModelBuilder {
    private Model model = new Model();

    public Model getModel() {
        return model;
    }

    public void buildModel(List<Mp3Bean> mp3Files) {
        for (Mp3Bean mp3File : mp3Files) {
            String artistName = mp3File.getArtist();
            String albumName = mp3File.getAlbum();
            String songName = mp3File.getSong();
            String duration = mp3File.getDuration();
            String localLink = mp3File.getLocalLink();

            Artist artist = findArtist(model.getArtists(), artistName);
            if (artist == null) {
                Song song = new Song(songName, duration, localLink);
                Album album = new Album();
                Artist newArtist = new Artist();
                album.addSong(song);
                newArtist.addAlbum(albumName, album);
                model.addArtist(artistName, newArtist);
                continue;
            }
            Album album = findAlbum(artist.getAlbums(), albumName);
            if (album == null) {
                Song song = new Song(songName, duration, localLink);
                Album newAlbum = new Album();
                newAlbum.addSong(song);
                artist.addAlbum(albumName ,newAlbum);
                continue;
            }
            Song newSong = new Song(songName, duration, localLink);
            album.addSong(newSong);
        }
    }

    private Artist findArtist(HashMap<String, Artist> artists, String artistName) {
        if (artists == null || artists.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Artist> artist : artists.entrySet()) {
            if (artist.getKey().equals(artistName)) {
                return artist.getValue();
            }
        }
        return null;
    }

    private Album findAlbum(HashMap<String, Album> albums, String albumName) {
        if (albums == null || albums.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Album> album : albums.entrySet()) {
            if (album.getKey().equals(albumName)) {
                return album.getValue();
            }
        }
        return null;
    }

    private Song findSong(HashMap<String, Song> songs, String songName) {
        if (songs == null || songs.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Song> song : songs.entrySet()) {
            if (song.getKey().equals(songName)) {
                return song.getValue();
            }
        }
        return null;
    }

}
