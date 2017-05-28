package cs499.cpp.edu.raplibs.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import cs499.cpp.edu.raplibs.model.Lyric;

/**
 * Created by admin on 4/12/17.
 */

public class LyricsArrayAdapter extends ArrayAdapter<Lyric> {

    private Context context;
    private int layoutResource;
    private List<Lyric> rapLyrics;

    public LyricsArrayAdapter(@NonNull Context context,
                              @LayoutRes int resource,
                              @NonNull List<Lyric> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.rapLyrics = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);

        return super.getView(position, convertView, parent);
    }
}
