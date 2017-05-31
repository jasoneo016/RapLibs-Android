package cs499.cpp.edu.raplibs.view.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cs499.cpp.edu.raplibs.R;

/**
 * Created by admin on 5/30/17.
 */

public class AdLibsHeaderViewHolder extends RecyclerView.ViewHolder {

    public TextView adLibsTab;

    public AdLibsHeaderViewHolder(View view) {
        super(view);

        adLibsTab = (TextView) view.findViewById(R.id.adLibsSearchTab);
    }

}
