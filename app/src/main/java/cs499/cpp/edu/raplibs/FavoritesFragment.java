package cs499.cpp.edu.raplibs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    private List<Favorites> favoritesList;
    private ListView listView;
    private FavoritesArrayAdapter favoritesArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);

        initFavorites();

        listView = (ListView) view.findViewById(R.id.listView);
        favoritesArrayAdapter = new FavoritesArrayAdapter(
                getActivity(), R.layout.listview_favorites_item, favoritesList);
        listView.setAdapter(favoritesArrayAdapter);

        return view;
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
