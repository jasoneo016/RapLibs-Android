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

public class AlbumsItemViewHolder extends RecyclerView.ViewHolder {
    public View rootView;
    public ImageView albumImage;
    public TextView albumName;
    public TextView albumArtist;
    public Context context;

    public AlbumsItemViewHolder(View view) {
        super(view);
        context = view.getContext();
        rootView = view;
        albumImage = (ImageView) view.findViewById(R.id.sectionAlbumImage);
        albumName = (TextView) view.findViewById(R.id.sectionAlbumName);
        albumArtist = (TextView) view.findViewById(R.id.sectionAlbumArtistName);
    }
}
