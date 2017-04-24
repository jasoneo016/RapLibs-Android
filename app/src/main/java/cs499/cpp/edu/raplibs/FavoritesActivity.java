package cs499.cpp.edu.raplibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.*;


import java.util.List;

/**
 * Created by admin on 4/24/17.
 */

public class FavoritesActivity extends AppCompatActivity {

    private List<Favorites> favoritesList;
    private ListView listView;
    private FavoritesArrayAdapter favoritesArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_artists);

        initFavorites();

        listView = (ListView) findViewById(R.id.artistListView);

        favoritesArrayAdapter = new FavoritesArrayAdapter(
                this, R.layout.listview_favorites_item, favoritesList);

        listView.setAdapter(favoritesArrayAdapter);
    }

    private void initFavorites() {
        favoritesList = new ArrayList<Favorites>() {{
            add(new Favorites("Drake", "Hermès link, ice-blue mink", R.drawable.drakemorelife));
            add(new Favorites("Drake", "I’m blem for real, I might just say how I feel", R.drawable.drakemorelife));
            add(new Favorites("Drake", "Free smoke, free smoke, ayy!", R.drawable.drakemorelife));
            add(new Favorites("Drake", "Kendall turned 21, was up the street with 21", R.drawable.drakemorelife));
            add(new Favorites("Drake", "Passionate from miles away, Passive with the things you say", R.drawable.drakemorelife));
            add(new Favorites("Drake", "I know I said top five, but I’m top two", R.drawable.drakemorelife));
            add(new Favorites("Drake", "Batman, Da-na-na-da-na!", R.drawable.drakemorelife));
        }};
    }
}
