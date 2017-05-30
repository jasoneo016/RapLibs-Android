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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
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

    private FloatingSearchView mySearchView;
    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference artistsRef;
    private FirebaseRecyclerAdapter<Artist,ArtistViewHolder> adapter;

    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbRef = FirebaseDatabase.getInstance().getReference();
        artistsRef = dbRef.child("artists");
        return inflater.inflate(R.layout.listview_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.search_results_list);
        mySearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        recyclerView.setHasFixedSize(true);
        adapter = new FirebaseRecyclerAdapter<Artist, ArtistViewHolder>(
                Artist.class, R.layout.search_results_list_item, ArtistViewHolder.class, artistsRef) {

            @Override
            protected void populateViewHolder(ArtistViewHolder viewHolder, Artist artist, int position)
            {
                viewHolder.bindImage(artist, R.id.searchartistname, R.id.searchartistimage);
            }

            @Override
            public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ArtistViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new ArtistViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
//        setUpDrawer();
        setUpSearchBar();
    }

    private void setUpSearchBar() {

        final DataHelper dataHelper = new DataHelper();
        mySearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mySearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mySearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    dataHelper.findSuggestions(getActivity(), newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<ArtistSuggestion> results) {
                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mySearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mySearchView.hideProgress();
                                }
                            });
                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });
    }

    private void setUpDrawer() {

    }

}