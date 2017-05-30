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

import cs499.cpp.edu.raplibs.model.Lyric;

public class LyricHelper {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference lyricsRef = dbRef.child("lyrics");
    private List<Lyric> lyricsList = new ArrayList<>();

    public LyricHelper() {
        populateLyricList();
    }


    public interface OnFindLyricsListener {
        void onResults(List<Lyric> results);
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


    public void findLyrics(Context context, String query, final OnFindLyricsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Lyric> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Lyric lyric : lyricsList) {
                        if (lyric.getArtist().toUpperCase()
                                .contains(constraint.toString().toUpperCase())
                                ||lyric.getLyric().toUpperCase()
                                .contains(constraint.toString().toUpperCase())
                                || lyric.getAlbum().toUpperCase()
                                .contains(constraint.toString().toUpperCase())
                                || lyric.getSong().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
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
                    listener.onResults((List<Lyric>) results.values);
                }
            }
        }.filter(query);

    }
}
