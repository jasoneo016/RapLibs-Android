package cs499.cpp.edu.raplibs.data;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.model.Artist;

public class ArtistHelper {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference artistsRef = dbRef.child("artists");
    private List<Artist> artistList = new ArrayList<>();

    public ArtistHelper() {
        populateArtistList();
    }

    public interface OnFindArtistsListener {
        void onResults(List<Artist> results);
    }


    public void populateArtistList() {
        artistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for (DataSnapshot artistSnapShot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapShot.getValue(Artist.class);
                    artistList.add(artist);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }


    public void findArtists(Context context, String query, final OnFindArtistsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Artist> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Artist artist : artistList) {
                        if (artist.getArtist().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            suggestionList.add(artist);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<Artist>) results.values);
                }
            }
        }.filter(query);
    }
}