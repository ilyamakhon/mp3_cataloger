package by.makhon.cataloger.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Mp3Duplicate, bean for finding duplicates among mp3 files
 */
public class Mp3Duplicate {
    private String artist;
    private String album;
    private String song;
    private List<String> localLinks = new ArrayList<>();


    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getLocalLinks() {
        return localLinks;
    }

    public void setLocalLinks(List<String> localLinks) {
        this.localLinks = localLinks;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void addLink(String link) {
        localLinks.add(link);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mp3Duplicate that = (Mp3Duplicate) o;
        return Objects.equals(artist, that.artist) &&
                Objects.equals(album, that.album) &&
                Objects.equals(song, that.song) &&
                Objects.equals(localLinks, that.localLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, album, song, localLinks);
    }
}
