package cs499.cpp.edu.raplibs.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.helper.RoundTransform;
import cs499.cpp.edu.raplibs.model.AdLib;

/**
 * Created by admin on 5/29/17.
 */

public class AdLibViewHolder extends RecyclerView.ViewHolder {

    View viewHolder;
    Context context;

    public ImageView shareButton;
    public ImageView favoriteButton;

    private AdLibViewHolder.ClickListener myClickListener;

    public AdLibViewHolder(View v) {
        super(v);
        viewHolder = v;
        context = v.getContext();
    }

    public void bindImage(AdLib adLib) {
        TextView textViewLyricName = (TextView) viewHolder.findViewById(R.id.adlib);
        textViewLyricName.setText(adLib.getAdlib());
        TextView textViewArtistName = (TextView) viewHolder.findViewById(R.id.adlibArtistName);
        textViewArtistName.setText(adLib.getArtist());
        shareButton = (ImageView) viewHolder.findViewById(R.id.shareAdLibSong);
        favoriteButton = (ImageView) viewHolder.findViewById(R.id.adLibFavorite);
        ImageView imageView = (ImageView) viewHolder.findViewById(R.id.adLibImage);
        Picasso.with(context)
                .load(adLib.getImage().replace(" ", "%20"))
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

    public void setOnClickListener(AdLibViewHolder.ClickListener clickListener){
        myClickListener = clickListener;
    }
}