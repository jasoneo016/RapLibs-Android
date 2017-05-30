package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.helper.RoundTransform;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.model.Lyric;

/**
 * Created by admin on 5/30/17.
 */

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    //Artist View
    public  TextView artistsSearchTab;
    public  ImageView searchArtistImage;
    public  TextView searchArtistName;
    public  View searchArtistContainer;

    //Lyric View
    public TextView lyricsSearchTab;
    public ImageView searchLyricImage;
    public TextView searchLyric;
    public TextView searchLyricSongName;
    public TextView searchLyricArtistName;
    public TextView searchLyricAlbumName;
    public View searchLyricContainer;

    //Album View
    public TextView albumsSearchTab;
    public ImageView searchAlbumImage;
    public TextView searchAlbumArtistName;
    public TextView searchAlbumName;
    public View searchAlbumContainer;

    //Ad Libs View
    public TextView adLibsSearchTab;
    public ImageView searchAdLibImage;
    public TextView searchAdLib;
    public TextView searchAdLibArtistName;
    public View searchAdLibContainer;


    public Context context;

    public SearchResultsViewHolder(View view) {
        super(view);
        context = view.getContext();

        artistsSearchTab = (TextView) view.findViewById(R.id.artistSearchTab);
        searchArtistImage = (ImageView) view.findViewById(R.id.searchartistimage);
        searchArtistName = (TextView) view.findViewById(R.id.searchartistname);
        searchArtistContainer = view.findViewById(R.id.artist_container);

        lyricsSearchTab = (TextView) view.findViewById(R.id.lyricsSearchTab);
        searchLyricImage = (ImageView) view.findViewById(R.id.searchLyricImage);
        searchLyric = (TextView) view.findViewById(R.id.searchLyricName);
        searchLyricSongName = (TextView) view.findViewById(R.id.searchLyricSongName);
        searchLyricArtistName = (TextView) view.findViewById(R.id.searchLyricArtistName);
        searchLyricAlbumName = (TextView)  view.findViewById(R.id.searchLyricAlbumName);
        searchLyricContainer = view.findViewById(R.id.lyric_container);

        albumsSearchTab = (TextView) view.findViewById(R.id.albumSearchTab);
        searchAlbumImage = (ImageView) view.findViewById(R.id.searchAlbumImage);
        searchAlbumArtistName = (TextView) view.findViewById(R.id.searchAlbumArtistName);
        searchAlbumName = (TextView)  view.findViewById(R.id.searchAlbumName);
        searchAlbumContainer = view.findViewById(R.id.album_container);

        adLibsSearchTab = (TextView) view.findViewById(R.id.adLibsSearchTab);
        searchAdLibImage = (ImageView) view.findViewById(R.id.searchAdLibsImage);
        searchAdLib = (TextView) view.findViewById(R.id.searchAdLibName);
        searchAdLibArtistName = (TextView) view.findViewById(R.id.searchAdLibArtistName);
        searchAdLibContainer = view.findViewById(R.id.adlibs_container);
    }
}