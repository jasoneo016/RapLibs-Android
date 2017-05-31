package cs499.cpp.edu.raplibs.fragments;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.AdLib;
import cs499.cpp.edu.raplibs.view.AdLibViewHolder;
import io.paperdb.Paper;


public class AdLibsFragment extends Fragment {

    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference adLibsRef;

    private MediaPlayer mediaPlayer;
    private List<AdLib> adLibList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        adLibsRef = dbRef.child("adlibs");

        Paper.init(getActivity());

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<AdLib,AdLibViewHolder> adapter = new FirebaseRecyclerAdapter<AdLib,AdLibViewHolder>(
                AdLib.class, R.layout.listview_adlib_item, AdLibViewHolder.class, adLibsRef) {

            @Override
            protected void populateViewHolder(AdLibViewHolder viewHolder, AdLib adLib, int position)
            {
                viewHolder.bindImage(adLib);
                adLibList.add(adLib);

                viewHolder.favoriteButton.setOnClickListener(new AdLibOnClickListener(adLib) {
                    public void onClick(View v) {
//                        Paper.book().write("")
                    }
                });
                viewHolder.shareButton.setOnClickListener(new AdLibOnClickListener(adLib) {
                    public void onClick(View v) {
                        String audioPath = adLib.getMp3().replace(" ", "+").replace("&","%26").replace(",", "%2C");
                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.putExtra(adLib.getArtist(), adLib.getAdlib());
                        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(audioPath)); // url would point to mp3 file
                        sendIntent.setType("audio/mp3");
                        startActivity(sendIntent);
                        startActivity(Intent.createChooser(sendIntent, "Share Audio Clip"));
                    }
                });
            }

            @Override
            public AdLibViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                AdLibViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new AdLibViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String audioPath = adLibList.get(position).getMp3().replace(" ", "+").replace("&","%26").replace(",", "%2C");
                        mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(audioPath));
                        mediaPlayer.start();
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

class AdLibOnClickListener implements View.OnClickListener
{

    AdLib adLib;
    public AdLibOnClickListener(AdLib adLib) {
        this.adLib = adLib;
    }

    @Override
    public void onClick(View v)
    {
    }

};