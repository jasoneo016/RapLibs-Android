package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.adapters.SearchResultsListAdapter;
import cs499.cpp.edu.raplibs.data.ArtistSuggestion;
import cs499.cpp.edu.raplibs.data.DataHelper;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.view.ArtistViewHolder;

/**
 * Created by admin on 5/28/17.
 */

public class SearchFragment extends Fragment {

    private final String TAG = "BlankFragment";
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private String lastQuery = "";

    private FloatingSearchView mySearchView;
    private RecyclerView recyclerView;

    private FirebaseRecyclerAdapter<Artist,ArtistViewHolder> adapter;
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
//        recyclerView.setHasFixedSize(true);
//        setUpDrawer();
        setUpSearchBar();
        setupResultsList();
    }

    private void setUpSearchBar() {

        final DataHelper dataHelper = new DataHelper();
        mySearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {


                } else {
                    dataHelper.findArtists(getActivity(), newQuery,
                            new DataHelper.OnFindArtistsListener() {

                                @Override
                                public void onResults(List<Artist> results) {
                                    mySearchResultsAdapter.swapData(results);
                                }

                            });

                    lastQuery = newQuery;

                }
                Log.d(TAG, "onSearchTextChanged()");
            }
        });
    }

    private void setUpDrawer() {

    }

    private void setupResultsList() {
        mySearchResultsAdapter = new SearchResultsListAdapter();
        recyclerView.setAdapter(mySearchResultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}