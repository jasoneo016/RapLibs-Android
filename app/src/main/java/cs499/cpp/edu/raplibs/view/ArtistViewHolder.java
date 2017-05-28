package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.Artist;

/**
 * Created by admin on 5/28/17.
 */

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    View viewHolder;
    Context context;
    private String name;

    public ArtistViewHolder(View v) {
        super(v);
        viewHolder = v;
        context = v.getContext();
    }

    public void bindImage(Artist artist) {
        TextView textViewName = (TextView) viewHolder.findViewById(R.id.artistname);
        textViewName.setText(artist.getName());
        ImageView imageView = (ImageView) viewHolder.findViewById(R.id.artistimage);
        Picasso.with(context).load(artist.getImage().replace(" ", "%20")).fit().into(imageView);
    }

    public void setName(String name) {
        this.name = name;
    }
}
