package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.Album;
import cs499.cpp.edu.raplibs.view.AlbumViewHolder;

/**
 * Created by admin on 5/28/17.
 */

public class AlbumsFragment extends Fragment {

    private List<Album> albumList;
    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference albumsRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        albumsRef = dbRef.child("albums");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<Album,AlbumViewHolder> adapter = new FirebaseRecyclerAdapter<Album, AlbumViewHolder>(
                Album.class, R.layout.listview_album_item, AlbumViewHolder.class, albumsRef) {

            @Override
            protected void populateViewHolder(AlbumViewHolder viewHolder, Album album, int position)
            {
                viewHolder.bindImage(album);
            }

            @Override
            public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                AlbumViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new AlbumViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return recyclerView;
    }
}
