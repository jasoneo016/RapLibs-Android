package cs499.cpp.edu.raplibs.model;

import java.util.List;

/**
 * Created by admin on 4/24/17.
 */

public class Artist {

    private String name;
    private String image;
    private int counter;
    private long timestamp;
    private List<String> albumUUIDs;
    private List<String> adLibUUIDs;
    private List<String> lyricUUIDs;

    public Artist() {

    }

    public Artist(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Artist(String name, String image, int counter, long timestamp, List<String> albumUUIDs, List<String> adLibUUIDs, List<String> lyricUUIDs) {
        this.name = name;
        this.image = image;
        this.counter = counter;
        this.timestamp = timestamp;
        this.albumUUIDs = albumUUIDs;
        this.adLibUUIDs = adLibUUIDs;
        this.lyricUUIDs = lyricUUIDs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getAlbumUUIDs() {
        return albumUUIDs;
    }

    public void setAlbumUUIDs(List<String> albumUUIDs) {
        this.albumUUIDs = albumUUIDs;
    }

    public List<String> getAdLibUUIDs() {
        return adLibUUIDs;
    }

    public void setAdLibUUIDs(List<String> adLibUUIDs) {
        this.adLibUUIDs = adLibUUIDs;
    }

    public List<String> getLyricUUIDs() {
        return lyricUUIDs;
    }

    public void setLyricUUIDs(List<String> lyricUUIDs) {
        this.lyricUUIDs = lyricUUIDs;
    }
}
