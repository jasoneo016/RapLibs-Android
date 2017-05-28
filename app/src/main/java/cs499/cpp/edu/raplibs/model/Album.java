package cs499.cpp.edu.raplibs.model;

/**
 * Created by admin on 5/15/17.
 */

public class Album {

    private String artist;
    private int counter;
    private String image;
    private String name;
    private String timestamp;

    public Album() {

    }

    public Album(String artist, String image, String name) {
        this.artist = artist;
        this.image = image;
        this.name = name;
    }

    public Album(String artist, int counter, String image, String name, String timestamp) {
        this.artist = artist;
        this.counter = counter;
        this.image = image;
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
