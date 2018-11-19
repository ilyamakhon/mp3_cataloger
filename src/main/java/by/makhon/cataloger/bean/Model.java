package by.makhon.cataloger.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Model, bean for creating html view
 * Contains full information about artists, albums, songs
 */
public class Model {

    private HashMap<String, Artist> artists = new HashMap<>();

    public HashMap<String, Artist> getArtists() {
        return artists;
    }

    public void setArtists(HashMap<String, Artist> artists) {
        this.artists = artists;
    }

    public void addArtist(String artistName, Artist artist) {
        artists.put(artistName, artist);
    }
}
