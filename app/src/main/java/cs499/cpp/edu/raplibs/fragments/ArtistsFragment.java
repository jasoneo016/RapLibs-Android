package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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

    private List<Artist> artistList;
    private RecyclerView recyclerView;
//    private ArtistArrayAdapter artistArrayAdapter;

    private DatabaseReference dbRef;
    private DatabaseReference artistsRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        artistsRef = dbRef.child("artists");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<Artist,ArtistViewHolder> adapter = new FirebaseRecyclerAdapter<Artist, ArtistViewHolder>(
                Artist.class, R.layout.listview_artists_item, ArtistViewHolder.class, artistsRef) {
            @Override
            protected void populateViewHolder(ArtistViewHolder viewHolder, Artist artist, int position)
            {
                viewHolder.bindImage(artist);
//                viewHolder.setName(artist.getName());
//                viewHolder.setImage(getApplicationContext(), artist.getImage());
            }
        };

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        recyclerView.setLayoutManager (new LinearLayoutManager(this));

        return recyclerView;
    }

    private void initArtists() {

        artistList = new ArrayList<>();

        artistsRef = dbRef.child("artists");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    artistList.add(ds.getValue(Artist.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        artistsRef.addValueEventListener(valueEventListener);
    }

}
