package cs499.cpp.edu.raplibs.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.helper.RoundTransform;
import cs499.cpp.edu.raplibs.model.AdLib;
import cs499.cpp.edu.raplibs.model.Album;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.model.Lyric;

import static cs499.cpp.edu.raplibs.R.id.imageView;

/**
 * Created by admin on 5/29/17.
 */


public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {

    private List<Artist> artistList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();
    private List<Lyric> lyricList = new ArrayList<>();
    private List<AdLib> adLibList = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;

    public interface OnArtistClickListener{
        void onClick(Artist artist);
    }

    public interface OnAlbumClickListener{
        void onClick(Album album);
    }

    public interface OnLyricClickListener{
        void onClick(Lyric lyric);
    }

    public interface OnAdLibClickListener{
        void onClick(AdLib adLib);
    }

    private OnArtistClickListener artistOnClickListener;
    private OnAlbumClickListener albumOnClickListener;
    private OnLyricClickListener lyricOnClickListener;
    private OnAdLibClickListener adLibOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        //Artist View
        public final ImageView searchArtistImage;
        public final TextView searchArtistName;
        public final View searchArtistContainer;

        //Song View
        public final ImageView searchLyricImage;
        public final TextView searchLyric;
        public final TextView searchLyricArtistName;
        public final TextView searchLyricAlbumName;
        public final View searchLyricContainer;

        //Album View
        public final ImageView searchAlbumImage;
        public final TextView searchAlbumArtistName;
        public final TextView searchalbumName;
        public final View searchAlbumContainer;

        //Ad Libs View
        public final ImageView searchAdLibImage;
        public final TextView searchAdLib;
        public final TextView searchAdLibArtistName;
        public final View searchAdLibContainer;

        Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();

            searchArtistImage = (ImageView) view.findViewById(R.id.searchartistimage);
            searchArtistName = (TextView) view.findViewById(R.id.searchartistname);
            searchArtistContainer = view.findViewById(R.id.artist_container);

            searchLyricImage = (ImageView) view.findViewById(R.id.searchLyricImage);
            searchLyric = (TextView) view.findViewById(R.id.searchLyricName);
            searchLyricArtistName = (TextView) view.findViewById(R.id.searchLyricArtistName);
            searchLyricAlbumName = (TextView)  view.findViewById(R.id.searchLyricAlbumName);
            searchLyricContainer = view.findViewById(R.id.lyric_container);

            searchAlbumImage = (ImageView) view.findViewById(R.id.searchAlbumImage);
            searchAlbumArtistName = (TextView) view.findViewById(R.id.searchAlbumArtistName);
            searchalbumName = (TextView)  view.findViewById(R.id.searchAlbumName);
            searchAlbumContainer = view.findViewById(R.id.album_container);

            searchAdLibImage = (ImageView) view.findViewById(R.id.searchAdLibsImage);
            searchAdLib = (TextView) view.findViewById(R.id.searchAdLibName);
            searchAdLibArtistName = (TextView) view.findViewById(R.id.searchAdLibArtistName);
            searchAdLibContainer = view.findViewById(R.id.adlibs_container);
        }
    }

    public void swapData(List<Artist> newArtistList) {
        artistList = newArtistList;
        notifyDataSetChanged();
    }

    public void setArtistsOnClickListener(OnArtistClickListener artistOnClickListener){
        this.artistOnClickListener = artistOnClickListener;
    }

    @Override
    public SearchResultsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_results_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsListAdapter.ViewHolder holder, final int position) {

        Artist artist = artistList.get(position);
        holder.searchArtistName.setText(artist.getName());
        Picasso.with(holder.context)
                .load(artistList.get(position).getImage().replace(" ", "%20"))
                .transform(new RoundTransform(100,1))
                .resize(200,200)
                .centerCrop()
                .into(holder.searchArtistImage);

        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

        if(artistOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    artistOnClickListener.onClick(artistList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }
}
