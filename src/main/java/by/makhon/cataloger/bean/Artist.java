package by.makhon.cataloger.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Class Artist bean for creating view model
 * Contains artist characteristics
 */
public class Artist {

    private HashMap<String, Album> albums = new HashMap<>();

    public HashMap<String, Album> getAlbums() {
        return albums;
    }

    public void setAlbums(HashMap<String, Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(String albumName, Album album) {
        albums.put(albumName, album);
    }
}
