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

import cs499.cpp.edu.raplibs.model.AdLib;

public class AdLibHelper {
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference adLibsRef = dbRef.child("adlibs");
    private List<AdLib> adlibsList = new ArrayList<>();

    public AdLibHelper() {
        populateAdLibList();
    }


    public interface OnFindAdLibsListener {
        void onResults(List<AdLib> results);
    }


    public void populateAdLibList() {
        adLibsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adlibsList.clear();

                for (DataSnapshot adLibSnapShot : dataSnapshot.getChildren()) {
                    AdLib adLib = adLibSnapShot.getValue(AdLib.class);
                    adlibsList.add(adLib);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }


    public void findAdLibs(Context context, String query, final OnFindAdLibsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<AdLib> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (AdLib adLib : adlibsList) {
                        if (adLib.getArtist().toUpperCase()
                                .contains(constraint.toString().toUpperCase())
                                ||adLib.getAdlib().toUpperCase()
                                .contains(constraint.toString().toUpperCase())) {
                            suggestionList.add(adLib);
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
                    listener.onResults((List<AdLib>) results.values);
                }
            }
        }.filter(query);

    }
}
