package cs499.cpp.edu.raplibs.view.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/30/17.
 */

public class LyricsHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView lyricsTab;

    public LyricsHeaderViewHolder(View view) {
        super(view);

        lyricsTab = (TextView) view.findViewById(R.id.lyricsSearchTab);
    }
}
