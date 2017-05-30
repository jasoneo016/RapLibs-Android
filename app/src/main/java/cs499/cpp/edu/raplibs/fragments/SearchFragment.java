package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.adapters.SearchResultsListAdapter;
import cs499.cpp.edu.raplibs.data.ArtistHelper;
import cs499.cpp.edu.raplibs.data.LyricHelper;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.model.Lyric;

/**
 * Created by admin on 5/28/17.
 */

public class SearchFragment extends Fragment {

    private final String TAG = "BlankFragment";
    private String lastQuery = "";

    private FloatingSearchView mySearchView;
    private RecyclerView recyclerView;

    private SearchResultsListAdapter mySearchResultsAdapter;
    private boolean mIsDarkSearchTheme = false;

    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listview_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.search_results_list);
        mySearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        setUpSearchBar();
        setupResultsList();
    }

    private void setUpSearchBar() {

        final ArtistHelper artistHelper = new ArtistHelper();
        final LyricHelper lyricHelper = new LyricHelper();
//        final AlbumHelper artistHelper = new ArtistHelper();
//        final AdLibHelper artistHelper = new ArtistHelper();

        mySearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    setupResultsList();
                } else {
                    artistHelper.findArtists(getActivity(), newQuery,
                            new ArtistHelper.OnFindArtistsListener() {

                                @Override
                                public void onResults(List<Artist> artistResults) {
                                    mySearchResultsAdapter.swapArtists(artistResults);
                                    if (!artistResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });
                    lyricHelper.findLyrics(getActivity(), newQuery,
                            new LyricHelper.OnFindLyricsListener() {

                                @Override
                                public void onResults(List<Lyric> lyricResults) {
                                    mySearchResultsAdapter.swapLyrics(lyricResults);
                                    if (!lyricResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });


                    lastQuery = newQuery;

                }
            }
        });
    }

    private void setupResultsList() {
        mySearchResultsAdapter = new SearchResultsListAdapter();
        recyclerView.setAdapter(mySearchResultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}