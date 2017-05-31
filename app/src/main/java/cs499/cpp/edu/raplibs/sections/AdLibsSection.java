package cs499.cpp.edu.raplibs.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.helper.RoundTransform;
import cs499.cpp.edu.raplibs.model.AdLib;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.model.Lyric;
import cs499.cpp.edu.raplibs.view.AdLibsItemViewHolder;
import cs499.cpp.edu.raplibs.view.LyricsItemViewHolder;
import cs499.cpp.edu.raplibs.view.header.AdLibsHeaderViewHolder;
import cs499.cpp.edu.raplibs.view.header.LyricsHeaderViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by admin on 5/30/17.
 */

public class AdLibsSection extends StatelessSection {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference adLibsRef = dbRef.child("adlibs");

    private List<AdLib> adLibList = new ArrayList<>();

    public AdLibsSection() {
        super(R.layout.section_adlib_header, R.layout.section_adlib_item);
        populateAdLibsList();
    }


    @Override
    public int getContentItemsTotal() {
        return adLibList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new AdLibsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AdLibsItemViewHolder itemHolder = (AdLibsItemViewHolder) holder;

        AdLib adLib = adLibList.get(position);
        itemHolder.adLib.setText(adLib.getAdlib());
        itemHolder.adLibArtist.setText(adLib.getArtist());
        Picasso.with(itemHolder.context)
                .load(adLibList.get(position).getImage().replace(" ", "%20"))
                .transform(new RoundTransform(100, 1))
                .resize(200, 200)
                .centerCrop()
                .into(((AdLibsItemViewHolder) holder).adLibImage);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemHolder.context, String.format("Clicked on position #s", "im gay"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new AdLibsHeaderViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        AdLibsHeaderViewHolder headerHolder = (AdLibsHeaderViewHolder) holder;

//        headerHolder.artistsTab.setText(title);
    }

    public void swapAdLibs(List<AdLib> newAdLibList) {
        adLibList = newAdLibList;
    }

    public interface OnFindAdLibsListener {
        void onResults(List<AdLib> results);
    }

    public void populateAdLibsList() {
        adLibsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                adLibList.clear();

                for (DataSnapshot adLibSnapShot : dataSnapshot.getChildren()) {
                    AdLib adLib = adLibSnapShot.getValue(AdLib.class);
                    adLibList.add(adLib);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void findAdLibs(Context context, String query, final AdLibsSection.OnFindAdLibsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<AdLib> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (AdLib adLib : adLibList) {
                        if (adLib.getArtist().toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                                ||adLib.getAdlib().toLowerCase()
                                .contains(constraint.toString().toLowerCase())) {
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