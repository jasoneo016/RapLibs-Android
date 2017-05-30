package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.Lyric;

/**
 * Created by admin on 5/28/17.
 */

public class LyricViewHolder  extends RecyclerView.ViewHolder {

    View viewHolder;
    Context context;

    public ImageView shareButton;

    private LyricViewHolder.ClickListener myClickListener;

    public LyricViewHolder(View v) {
        super(v);
        viewHolder = v;
        context = v.getContext();
    }

    public void bindImage(Lyric lyric) {
        TextView textViewLyricName = (TextView) viewHolder.findViewById(R.id.lyric);
        textViewLyricName.setText(lyric.getLyric());
        TextView textViewLyricSongName = (TextView) viewHolder.findViewById(R.id.lyricSongName);
        textViewLyricSongName.setText(lyric.getSong());
        TextView textViewArtistName = (TextView) viewHolder.findViewById(R.id.lyricArtistName);
        textViewArtistName.setText(lyric.getArtist());
        TextView textViewLyricAlbumName = (TextView) viewHolder.findViewById(R.id.lyricAlbumName);
        shareButton = (ImageView) viewHolder.findViewById(R.id.sharesong);
        textViewLyricAlbumName.setText(lyric.getAlbum());
        ImageView imageView = (ImageView) viewHolder.findViewById(R.id.lyricImage);
        Picasso.with(context)
                .load(lyric.getImage().replace(" ", "%20"))
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

    public void setOnClickListener(LyricViewHolder.ClickListener clickListener){
        myClickListener = clickListener;
    }
}