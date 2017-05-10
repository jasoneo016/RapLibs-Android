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

/**
 * Created by admin on 5/9/17.
 */

public class ArtistsFragment extends Fragment {

    private List<Artist> artistList;
    private ListView listView;
    private ArtistArrayAdapter artistArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        initArtists();

        listView = (ListView) view.findViewById(R.id.artistListView);
        artistArrayAdapter = new ArtistArrayAdapter(
                getActivity(), R.layout.listview_artists_item, artistList);
        listView.setAdapter(artistArrayAdapter);

        return view;
    }

    private void initArtists() {
        artistList = new ArrayList<Artist>() {{

            add(new Artist("Kendrick Lamar", R.drawable.kendricklamar));
            add(new Artist("Big Sean", R.drawable.bigsean));
//            add(new Artist("Drake", R.drawable.drake));
//            add(new Artist("Waka Flocka Flame", R.drawable.wakaflockaflame));
//            add(new Artist("Kanye West", R.drawable.kanyewest));
//            add(new Artist("Offset", R.drawable.offset));
//            add(new Artist("21 Savage", R.drawable.savage));
//            add(new Artist("Takeoff", R.drawable.takeoff));

        }};
    }

}
