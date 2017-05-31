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

public class ArtistsItemViewHolder extends RecyclerView.ViewHolder {

    public View rootView;
    public ImageView artistImage;
    public TextView artistName;
    public Context context;

    public ArtistsItemViewHolder(View view) {
        super(view);
        context = view.getContext();
        rootView = view;
        artistImage = (ImageView) view.findViewById(R.id.artistSectionImage);
        artistName = (TextView) view.findViewById(R.id.artistSectionName);
    }
}