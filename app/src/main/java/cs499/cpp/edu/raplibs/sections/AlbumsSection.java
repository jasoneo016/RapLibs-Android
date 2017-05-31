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
import cs499.cpp.edu.raplibs.model.Album;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.view.AlbumsItemViewHolder;
import cs499.cpp.edu.raplibs.view.header.AlbumsHeaderViewHolder;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by admin on 5/30/17.
 */

public class AlbumsSection extends StatelessSection {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference albumsRef = dbRef.child("albums");

    private List<Album> albumList = new ArrayList<>();

    public AlbumsSection() {
        super(R.layout.section_album_header, R.layout.section_album_item);
        populateAlbumsList();
    }


    @Override
    public int getContentItemsTotal() {
        return albumList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new AlbumsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AlbumsItemViewHolder itemHolder = (AlbumsItemViewHolder) holder;

        Album album = albumList.get(position);
        itemHolder.albumName.setText(album.getName());
        itemHolder.albumArtist.setText(album.getArtist());
        Picasso.with(itemHolder.context)
                .load(albumList.get(position).getImage().replace(" ", "%20"))
//                .transform(new RoundTransform(100, 1))
                .resize(200, 200)
                .centerCrop()
                .into(((AlbumsItemViewHolder) holder).albumImage);

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemHolder.context, String.format("Clicked on position #s", "im gay"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new AlbumsHeaderViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        AlbumsHeaderViewHolder headerHolder = (AlbumsHeaderViewHolder) holder;

//        headerHolder.artistsTab.setText(title);
    }

    public void swapAlbums(List<Album> newAlbumList) {
        albumList = newAlbumList;
    }

    public void populateAlbumsList() {
        albumsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                albumList.clear();

                for (DataSnapshot albumSnapShot : dataSnapshot.getChildren()) {
                    Album album = albumSnapShot.getValue(Album.class);
                    albumList.add(album);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });
    }

    public void findAlbum(Context context, String query, final AlbumsSection.OnFindAlbumListener listener) {

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                List<Album> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Album album : albumList) {
                        if (album.getArtist().toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                                ||album.getName().toLowerCase()
                                .contains(constraint.toString().toLowerCase())) {
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

    public interface OnFindAlbumListener {
        void onResults(List<Album> results);
    }
}
