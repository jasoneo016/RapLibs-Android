package cs499.cpp.edu.raplibs;

/**
 * Created by admin on 4/24/17.
 */

public class Artist {

    private String name;
    private int artistImage;

    public Artist(String name, int artistImage) {
        this.name = name;
        this.artistImage = artistImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(int artistImage) {
        this.artistImage = artistImage;
    }
}
