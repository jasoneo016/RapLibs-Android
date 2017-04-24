package cs499.cpp.edu.raplibs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 4/24/17.
 */

public class ArtistArrayAdapter extends ArrayAdapter<Artist> {

    private Context context;
    private int layoutResource;
    private List<Artist> artists;

    public ArtistArrayAdapter(@NonNull Context context,
                                 @LayoutRes int resource,
                                 @NonNull List<Artist> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.artists = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.artistimage);
        imageView.setImageResource(artists.get(position).getArtistImage());

        TextView textViewName = (TextView) view.findViewById(R.id.artistname);
        textViewName.setText(artists.get(position).getName());

        return view;
    }
}
