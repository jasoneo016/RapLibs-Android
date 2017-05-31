package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.helper.RoundTransform;
import cs499.cpp.edu.raplibs.model.Artist;

/**
 * Created by admin on 5/28/17.
 */

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    View viewHolder;
    Context context;

    private ArtistViewHolder.ClickListener myClickListener;

    public ArtistViewHolder(View v) {
        super(v);
        viewHolder = v;
        context = v.getContext();
    }

    public void bindImage(Artist artist, int artistName, int artistImage) {
        TextView textViewName = (TextView) viewHolder.findViewById(artistName);
        textViewName.setText(artist.getArtist());
        ImageView imageView = (ImageView) viewHolder.findViewById(artistImage);
        Picasso.with(context)
                .load(artist.getImage().replace(" ", "%20"))
                .transform(new RoundTransform(100,1))
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

    public void setOnClickListener(ArtistViewHolder.ClickListener clickListener){
        myClickListener = clickListener;
    }
}
