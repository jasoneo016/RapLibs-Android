package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.Album;

import static android.content.ContentValues.TAG;

/**
 * Created by admin on 5/28/17.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    View viewHolder;
    Context context;

    private AlbumViewHolder.ClickListener myClickListener;

    public AlbumViewHolder(View v) {
        super(v);
        viewHolder = v;
        context = v.getContext();
    }

    public void bindImage(Album album) {
        TextView textViewAlbumName = (TextView) viewHolder.findViewById(R.id.albumName);
        Log.i(TAG, album.getName());
        textViewAlbumName.setText(album.getName());
        TextView textViewArtistName = (TextView) viewHolder.findViewById(R.id.artistAlbumName);
        textViewArtistName.setText(album.getArtist());
        ImageView imageView = (ImageView) viewHolder.findViewById(R.id.albumImage);
        Picasso.with(context)
                .load(album.getImage().replace(" ", "%20"))
                .resize(200,200)
                .centerCrop()
                .into(imageView);

        viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onItemClick(v, getAdapterPosition());
            }
        });

    }

    public interface ClickListener {

        public void onItemClick(View view, int position);
    }

    public void setOnClickListener(AlbumViewHolder.ClickListener clickListener){
        myClickListener = clickListener;
    }
}