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

import cs499.cpp.edu.raplibs.model.Album;

/**
 * Created by admin on 5/29/17.
 */

public class AlbumHelper {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference albumsRef = dbRef.child("albums");
    private List<Album> albumsList = new ArrayList<>();

    public AlbumHelper() {
        populateAlbumList();
    }


    public interface OnFindAlbumsListener {
        void onResults(List<Album> results);
    }


    public void populateAlbumList() {
        albumsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                albumsList.clear();

                for (DataSnapshot albumSnapShot : dataSnapshot.getChildren()) {
                    Album album = albumSnapShot.getValue(Album.class);
                    albumsList.add(album);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }


    public void findAlbums(Context context, String query, final OnFindAlbumsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Album> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Album album : albumsList) {
                        if (album.getArtist().toUpperCase()
                                .contains(constraint.toString().toUpperCase())
                                ||album.getName().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            suggestionList.add(album);
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
                    listener.onResults((List<Album>) results.values);
                }
            }
        }.filter(query);

    }
}
