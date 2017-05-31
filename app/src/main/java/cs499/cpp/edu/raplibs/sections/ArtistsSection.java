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
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.view.ArtistsItemViewHolder;
import cs499.cpp.edu.raplibs.view.header.ArtistsHeaderViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by admin on 5/30/17.
 */

public class ArtistsSection extends StatelessSection {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference artistsRef = dbRef.child("artists");
    private List<Artist> artistList = new ArrayList<>();

    public ArtistsSection() {
        super(R.layout.section_artist_header, R.layout.section_artist_item);
        populateArtistList();
    }

    public void swapArtists(List<Artist> newArtistList) {
        artistList = newArtistList;
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


    @Override
    public int getContentItemsTotal() {
        return artistList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ArtistsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ArtistsItemViewHolder itemHolder = (ArtistsItemViewHolder) holder;

        Artist artist = artistList.get(position);
        itemHolder.artistName.setText(artist.getArtist());
        Picasso.with(itemHolder.context)
                .load(artistList.get(position).getImage().replace(" ", "%20"))
                .transform(new RoundTransform(100, 1))
                .resize(200, 200)
                .centerCrop()
                .into(((ArtistsItemViewHolder) holder).artistImage);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemHolder.context, String.format("Clicked on position #s", "im gay"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new ArtistsHeaderViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ArtistsHeaderViewHolder headerHolder = (ArtistsHeaderViewHolder) holder;
//        headerHolder.artistsTab.setText(title);
    }

    public void findArtists(Context context, String query, final ArtistsSection.OnFindArtistsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Artist> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Artist artist : artistList) {
                        if (artist.getArtist().toLowerCase()
                                .contains(constraint.toString().toLowerCase())) {
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