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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cs499.cpp.edu.raplibs.model.Artist;

import static android.content.ContentValues.TAG;

/**
 * Created by admin on 5/29/17.
 */

public class DataHelper {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference artistsRef = dbRef.child("artists");
    private List<Artist> artistList = new ArrayList<>();

    private List<ArtistSuggestion> artistSuggestions = new ArrayList<>();

    public DataHelper() {
        populateArtistList();
    }

    public interface OnFindArtistsListener {
        void onResults(List<Artist> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<ArtistSuggestion> results);
    }


    public void populateArtistList() {
        artistsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for (DataSnapshot artistSnapShot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapShot.getValue(Artist.class);
                    artistList.add(artist);
                    artistSuggestions.add(new ArtistSuggestion(artist.getName()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public List<ArtistSuggestion> getHistory(Context context, int count) {

        List<ArtistSuggestion> suggestionList = new ArrayList<>();
        ArtistSuggestion artistSuggestion;
        for (int i = 0; i < artistSuggestions.size(); i++) {
            artistSuggestion = artistSuggestions.get(i);
            artistSuggestion.setIsHistory(true);
            suggestionList.add(artistSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public void resetSuggestionsHistory() {
        for (ArtistSuggestion artistSuggestion : artistSuggestions) {
            artistSuggestion.setIsHistory(false);
        }
    }

    public void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                resetSuggestionsHistory();
                List<ArtistSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (ArtistSuggestion suggestion : artistSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<ArtistSuggestion>() {
                    @Override
                    public int compare(ArtistSuggestion lhs, ArtistSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<ArtistSuggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public void findArtists(Context context, String query, final OnFindArtistsListener listener) {

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
                    listener.onResults((List<Artist>) results.values);
                }
            }
        }.filter(query);

    }
}
