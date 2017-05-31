package cs499.cpp.edu.raplibs.view.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/30/17.
 */

public class ArtistsHeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView artistsTab;

    public ArtistsHeaderViewHolder(View view) {
        super(view);

        artistsTab = (TextView) view.findViewById(R.id.artistSearchTab);
    }
}