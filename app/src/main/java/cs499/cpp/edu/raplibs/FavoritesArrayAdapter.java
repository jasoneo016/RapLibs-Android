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

public class FavoritesArrayAdapter extends ArrayAdapter<Favorites> {

    private Context context;
    private int layoutResource;
    private List<Favorites> favorites;

    public FavoritesArrayAdapter(@NonNull Context context,
                              @LayoutRes int resource,
                              @NonNull List<Favorites> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.favorites = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.albumart);
        imageView.setImageResource(favorites.get(position).getAlbumImage());

        TextView textViewName = (TextView) view.findViewById(R.id.artist);
        textViewName.setText(favorites.get(position).getName());

        TextView textViewLyrics = (TextView) view.findViewById(R.id.lyrics);
        textViewLyrics.setText(favorites.get(position).getLyrics());

        return view;
    }
}
