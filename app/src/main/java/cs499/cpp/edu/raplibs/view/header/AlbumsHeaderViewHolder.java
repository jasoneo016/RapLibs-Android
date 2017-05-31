package cs499.cpp.edu.raplibs.view.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/30/17.
 */

public class AlbumsHeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView albumsTab;

    public AlbumsHeaderViewHolder(View view) {
        super(view);

        albumsTab = (TextView) view.findViewById(R.id.albumSearchTab);
    }

}
