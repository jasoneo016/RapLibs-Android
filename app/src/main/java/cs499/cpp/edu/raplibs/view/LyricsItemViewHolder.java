package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/30/17.
 */

public class LyricsItemViewHolder extends RecyclerView.ViewHolder {
    public View rootView;
    public ImageView lyricImage;
    public TextView lyricName;
    public TextView lyricSongName;
    public TextView lyricArtistName;
    public TextView lyricAlbumName;
    public Context context;

    public LyricsItemViewHolder(View view) {
        super(view);
        context = view.getContext();
        rootView = view;
        lyricImage = (ImageView) view.findViewById(R.id.sectionLyricImage);
        lyricName = (TextView) view.findViewById(R.id.sectionLyricName);
        lyricSongName = (TextView) view.findViewById(R.id.sectionLyricSongName);
        lyricArtistName = (TextView) view.findViewById(R.id.sectionLyricArtistName);
        lyricAlbumName = (TextView) view.findViewById(R.id.sectionLyricAlbumName);
    }
}
