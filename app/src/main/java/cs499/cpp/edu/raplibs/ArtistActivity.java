package cs499.cpp.edu.raplibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArtistActivity extends AppCompatActivity {

    private List<Artist> artistList;
    private ListView listView;
    private ArtistArrayAdapter artistArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        initArtists();

        listView = (ListView) findViewById(R.id.artistListView);

        artistArrayAdapter = new ArtistArrayAdapter(
                this, R.layout.listview_artists_item, artistList);

        listView.setAdapter(artistArrayAdapter);
    }

    private void initArtists() {
        artistList = new ArrayList<Artist>() {{

            add(new Artist("Kendrick Lamar", R.drawable.kendricklamar));
            add(new Artist("Big Sean", R.drawable.bigsean));
            add(new Artist("Drake", R.drawable.drake));
            add(new Artist("Waka Flocka Flame", R.drawable.wakaflockaflame));
            add(new Artist("Kanye West", R.drawable.kanyewest));
            add(new Artist("Offset", R.drawable.offset));
            add(new Artist("21 Savage", R.drawable.savage));
            add(new Artist("Takeoff", R.drawable.takeoff));

        }};
    }
}
