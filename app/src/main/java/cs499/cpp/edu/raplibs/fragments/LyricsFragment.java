package cs499.cpp.edu.raplibs.fragments;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.internal.Utils;
import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.driver.MainActivity;
import cs499.cpp.edu.raplibs.model.Lyric;
import cs499.cpp.edu.raplibs.view.LyricViewHolder;

/**
 * Created by admin on 5/28/17.
 */

public class LyricsFragment extends Fragment {

    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference lyricsRef;

    private MediaPlayer mediaPlayer;
    private List<Lyric> lyricList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        lyricsRef = dbRef.child("lyrics");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<Lyric,LyricViewHolder> adapter = new FirebaseRecyclerAdapter<Lyric, LyricViewHolder>(
                Lyric.class, R.layout.listview_lyric_item, LyricViewHolder.class, lyricsRef) {

            @Override
            protected void populateViewHolder(LyricViewHolder viewHolder, Lyric lyric, int position)
            {
                viewHolder.bindImage(lyric);
                lyricList.add(lyric);
                viewHolder.shareButton.setOnClickListener(new LyricOnClickListener(lyric) {
                    public void onClick(View v) {
                        String audioPath = lyric.getMp3().replace(" ", "+").replace("&","%26").replace(",", "%2C");
                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.putExtra(lyric.getArtist(), lyric.getLyric());
                        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(audioPath)); // url would point to mp3 file
                        sendIntent.setType("audio/mp3");
                        startActivity(sendIntent);
                        startActivity(Intent.createChooser(sendIntent, "Share Audio Clip"));
                    }
                });
            }

            @Override
            public LyricViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LyricViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new LyricViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String audioPath = lyricList.get(position).getMp3().replace(" ", "+").replace("&","%26").replace(",", "%2C");
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

class LyricOnClickListener implements View.OnClickListener
{

    Lyric lyric;
    public LyricOnClickListener(Lyric lyric) {
        this.lyric = lyric;
    }

    @Override
    public void onClick(View v)
    {
    }

};