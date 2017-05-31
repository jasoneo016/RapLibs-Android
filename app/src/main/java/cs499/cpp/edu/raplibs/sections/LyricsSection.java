package cs499.cpp.edu.raplibs.sections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import cs499.cpp.edu.raplibs.model.Lyric;
import cs499.cpp.edu.raplibs.view.LyricsItemViewHolder;
import cs499.cpp.edu.raplibs.view.header.LyricsHeaderViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by admin on 5/30/17.
 */

public class LyricsSection extends StatelessSection {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference lyricsRef = dbRef.child("lyrics");

    private List<Lyric> lyricList = new ArrayList<>();

    public LyricsSection() {
        super(R.layout.section_lyric_header, R.layout.section_lyric_item);
        populateLyricsList();
    }


    @Override
    public int getContentItemsTotal() {
        return lyricList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new LyricsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final LyricsItemViewHolder itemHolder = (LyricsItemViewHolder) holder;

        Lyric lyric = lyricList.get(position);
        itemHolder.lyricName.setText(lyric.getLyric());
        itemHolder.lyricSongName.setText(lyric.getSong());
        itemHolder.lyricArtistName.setText(lyric.getArtist());
        itemHolder.lyricAlbumName.setText(lyric.getAlbum());
        Picasso.with(itemHolder.context)
                .load(lyricList.get(position).getImage().replace(" ", "%20"))
//                .transform(new RoundTransform(100, 1))
                .resize(200, 200)
                .centerCrop()
                .into(((LyricsItemViewHolder) holder).lyricImage);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemHolder.context, String.format("Clicked on position #s", "im gay"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new LyricsHeaderViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        LyricsHeaderViewHolder headerHolder = (LyricsHeaderViewHolder) holder;

//        headerHolder.artistsTab.setText(title);
    }

    public void swapLyrics(List<Lyric> newLyricList) {
        lyricList = newLyricList;
    }

    public interface OnFindArtistsListener {
        void onResults(List<Artist> results);
    }

    public void populateLyricsList() {
        lyricsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lyricList.clear();

                for (DataSnapshot lyricSnapShot : dataSnapshot.getChildren()) {
                    Lyric lyric = lyricSnapShot.getValue(Lyric.class);
                    lyricList.add(lyric);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void findLyrics(Context context, String query, final LyricsSection.OnFindLyricsListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Lyric> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Lyric lyric : lyricList) {
                        if (lyric.getArtist().toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                                ||lyric.getLyric().toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                                || lyric.getAlbum().toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                                || lyric.getSong().toLowerCase()
                                .contains(constraint.toString().toLowerCase())) {
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

    public interface OnFindLyricsListener {
        void onResults(List<Lyric> results);
    }
}
