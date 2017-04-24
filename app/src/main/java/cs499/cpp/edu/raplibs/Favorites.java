package cs499.cpp.edu.raplibs;

/**
 * Created by admin on 4/24/17.
 */

public class Favorites {

    private String name;
    private String lyrics;
    private int albumImage;

    public Favorites(String name, String lyrics, int albumImage) {
        this.name = name;
        this.lyrics = lyrics;
        this.albumImage = albumImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }
}
