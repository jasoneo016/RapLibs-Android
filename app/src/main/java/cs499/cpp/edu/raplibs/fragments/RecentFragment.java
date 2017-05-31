package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.model.Favorites;
import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.adapters.FavoritesArrayAdapter;


public class RecentFragment extends Fragment {

    private List<Favorites> favoritesList;
    private ListView listView;
    private FavoritesArrayAdapter favoritesArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_view_test, container, false);

        initRecent();

        listView = (ListView) view.findViewById(R.id.list_view_test);
        favoritesArrayAdapter = new FavoritesArrayAdapter(
                getActivity(), R.layout.listview_favorites_item, favoritesList);
        listView.setAdapter(favoritesArrayAdapter);

        return view;
    }

    private void initRecent() {
        favoritesList = new ArrayList<Favorites>() {{
            add(new Favorites("Big Sean", "Last night took an L, but tonight I bounce back", R.drawable.bigseanidecided));
            add(new Favorites("Joey Badass", "It's just the way I feel", R.drawable.joeybadassallamerikkkanbadass));
            add(new Favorites("Drake", "Free smoke, free smoke, ayy!", R.drawable.drakemorelife));
            add(new Favorites("Drake", "Top 5 Top 5 Top 5", R.drawable.drakeviews));
            add(new Favorites("J. Cole", "I came fast like 911 in white neighborhoods", R.drawable.foresthillsdrive));
            add(new Favorites("Kendrick Lamar", "I got royalty got loyalty inside my DNA", R.drawable.kendrickdamn));
        }};
    }
}
