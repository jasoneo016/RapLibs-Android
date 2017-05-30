package cs499.cpp.edu.raplibs.fragments;

import android.content.Intent;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        lyricsRef = dbRef.child("lyrics");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<Lyric,LyricViewHolder> adapter = new FirebaseRecyclerAdapter<Lyric, LyricViewHolder>(
                Lyric.class, R.layout.listview_lyric_item, LyricViewHolder.class, lyricsRef) {

            @Override
            protected void populateViewHolder(LyricViewHolder viewHolder, Lyric lyric, int position)
            {
                viewHolder.bindImage(lyric);
            }

            @Override
            public LyricViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LyricViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new LyricViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
//                        String mp3Path = "https://s3-us-west-1.amazonaws.com/raplibsbucket/RapLibs/Drake/Albums/More Life/KMT (feat. Giggs)/Batman.mp3";
//                        mp3Path.replace(" ", "%20");
//                        Uri uri = Uri.parse(mp3Path);
//                        Intent share = new Intent(Intent.ACTION_SEND);
//                        share.setType("audio/mp3");
//                        share.putExtra(Intent.EXTRA_STREAM, uri);
//                        startActivity(Intent.createChooser(share, "Share Sound File"));

                        String audioClipFilePath = "https://s3-us-west-1.amazonaws.com/raplibsbucket/RapLibs/Drake/Albums/More Life/Gyalchester/Hermes link, ice-blue mink.mp3".replace(" ", "%20");
                        String[] path = audioClipFilePath.split("/");
                        String songName = path[9];
                        String SDCardRoot = Environment.getExternalStorageDirectory()
                                .toString();
                        downloadFile(audioClipFilePath, path[9],
                                SDCardRoot+"/RapLibs");

                        final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                        shareIntent.setType("audio/mp3");
                        shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse(SDCardRoot + "/RapLibs/" + path[9]));
                        shareIntent.setPackage("vnd.android-dir/mms-sms");
                        startActivity(shareIntent);
//                        startActivity(Intent.createChooser(shareIntent, "Share Audio Clip"));


//                        File imageFile = ...;
//                        Uri uriToImage = FileProvider.getUriForFile(
//                                context, FILES_AUTHORITY, imageFile);
//                        Intent shareIntent = ShareCompat.IntentBuilder.from(activity)
//                                .setStream(uriToImage)
//                                .getIntent();
//                        shareIntent.setData(uriToImage);
//                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
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

    static void downloadFile(String mp3URL, String fileName,
                             String pathToSave) {
        int downloadedSize = 0;
        int totalSize = 0;

        try {
            URL url = new URL(mp3URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();

            File myDir;
            myDir = new File(pathToSave);
            myDir.mkdirs();

            // create a new file, to save the downloaded file

            String mFileName = fileName;
            File file = new File(myDir, mFileName);

            FileOutputStream fileOutput = new FileOutputStream(file);

            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are downloading
            totalSize = urlConnection.getContentLength();

            // create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                // update the progressbar //
                // runOnUiThread(new Runnable() {
                // public void run() {
                // pb.setProgress(downloadedSize);
                // float per = ((float)downloadedSize/totalSize) * 100;
                // cur_val.setText("Downloaded " + downloadedSize + "KB / " +
                // totalSize + "KB (" + (int)per + "%)" );
                // }
                // });
            }
            // close the output stream when complete //
            fileOutput.close();
            // runOnUiThread(new Runnable() {
            // public void run() {
            // // pb.dismiss(); // if you want close it..
            // }
            // });

        } catch (final MalformedURLException e) {
            // showError("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            // showError("Error : IOException " + e);
            e.printStackTrace();
        } catch (final Exception e) {
            // showError("Error : Please check your internet connection " + e);
        }
    }
}