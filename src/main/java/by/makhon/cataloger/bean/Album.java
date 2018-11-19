package by.makhon.cataloger.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Album, bean for creating view model
 * Contains album characteristics
 */
public class Album {

    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
