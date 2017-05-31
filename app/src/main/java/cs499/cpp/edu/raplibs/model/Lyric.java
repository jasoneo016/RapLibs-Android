package cs499.cpp.edu.raplibs.model;

/**
 * Created by admin on 4/12/17.
 */

public class Lyric implements Music {

    private String album;
    private String artist;
    private int counter;
    private String image;
    private String lyric;
    private String mp3;
    private String song;
    private long timestamp;

    public Lyric() {

    }

    public Lyric(String artist, String image, String lyric) {
        this.artist = artist;
        this.image = image;
        this.lyric = lyric;
    }

    public Lyric(String album, String artist, int counter, String image, String lyric, String mp3, String song, long timestamp) {
        this.album = album;
        this.artist = artist;
        this.counter = counter;
        this.image = image;
        this.lyric = lyric;
        this.mp3 = mp3;
        this.song = song;
        this.timestamp = timestamp;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
