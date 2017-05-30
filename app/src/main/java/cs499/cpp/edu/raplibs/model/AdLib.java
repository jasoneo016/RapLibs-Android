package cs499.cpp.edu.raplibs.model;

/**
 * Created by admin on 5/29/17.
 */

public class AdLib {

    private String artist;
    private int counter;
    private String image;
    private String adlib;
    private String mp3;
    private long timestamp;

    public AdLib() {

    }

    public AdLib(String artist, String image, String adlib) {
        this.artist = artist;
        this.image = image;
        this.adlib = adlib;
    }

    public AdLib(String artist, int counter, String image, String adlib, String mp3, long timestamp) {
        this.artist = artist;
        this.counter = counter;
        this.image = image;
        this.adlib = adlib;
        this.mp3 = mp3;
        this.timestamp = timestamp;
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

    public String getAdlib() {
        return adlib;
    }

    public void setAdlib(String adlib) {
        this.adlib = adlib;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
