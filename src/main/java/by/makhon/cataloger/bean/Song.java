package by.makhon.cataloger.bean;

/**
 * Song bean for creating view model
 * Contains song characteristics
 */
public class Song {

    private String name;
    private String duration;
    private String localLink;

    public Song(String name, String duration, String localLink) {
        this.name = name;
        this.duration = duration;
        this.localLink = localLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocalLink() {
        return localLink;
    }

    public void setLocalLink(String localLink) {
        this.localLink = localLink;
    }
}
