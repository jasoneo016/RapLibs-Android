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
import cs499.cpp.edu.raplibs.model.Lyric;

/**
 * Created by admin on 5/30/17.
 */

public class SearchHelper {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference artistsRef = dbRef.child("artists");
    private DatabaseReference lyricsRef = dbRef.child("lyrics");
    private List<Artist> artistList = new ArrayList<>();
    private List<Lyric> lyricsList = new ArrayList<>();

    public SearchHelper() {
        populateArtistList();
        populateLyricList();
    }


    public interface OnFindListener {
        void onResults(List<Artist> artistResults, List<Lyric> lyricResults);
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

    public void populateLyricList() {
        lyricsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lyricsList.clear();

                for (DataSnapshot lyricSnapShot : dataSnapshot.getChildren()) {
                    Lyric lyric = lyricSnapShot.getValue(Lyric.class);
                    lyricsList.add(lyric);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void findArtists(Context context, String query, final SearchHelper.OnFindListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Artist> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Artist artist : artistList) {
                        if (artist.getName().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {
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
//                    listener.onResults((List<Artist>) results.values);
                }
            }
        }.filter(query);

    }


    public void FindLyrics(Context context, String query, final SearchHelper.OnFindListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Lyric> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Lyric lyric : lyricsList) {
                        if (lyric.getLyric().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {
                            suggestionList.add(lyric);
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
//                    listener.onResults((List<Lyric>) results.values);
                }
            }
        }.filter(query);

    }
}
