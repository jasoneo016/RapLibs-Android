package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.view.ArtistViewHolder;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/9/17.
 */

public class ArtistsFragment extends Fragment {

    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference artistsRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        artistsRef = dbRef.child("artists");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<Artist,ArtistViewHolder> adapter = new FirebaseRecyclerAdapter<Artist, ArtistViewHolder>(
                Artist.class, R.layout.listview_artist_item, ArtistViewHolder.class, artistsRef) {

            @Override
            protected void populateViewHolder(ArtistViewHolder viewHolder, Artist artist, int position)
            {
                viewHolder.bindImage(artist, R.id.artistName, R.id.artistImage);
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return recyclerView;
    }
}
