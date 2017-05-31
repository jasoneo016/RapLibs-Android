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

public class AdLibsItemViewHolder extends RecyclerView.ViewHolder {
    public View rootView;
    public ImageView adLibImage;
    public TextView adLib;
    public TextView adLibArtist;
    public Context context;

    public AdLibsItemViewHolder(View view) {
        super(view);
        context = view.getContext();
        rootView = view;
        adLibImage = (ImageView) view.findViewById(R.id.sectionAdLibImage);
        adLib = (TextView) view.findViewById(R.id.sectionAdLibName);
        adLibArtist = (TextView) view.findViewById(R.id.sectionAdLibArtistName);
    }
}
