package cs499.cpp.edu.raplibs.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

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
import cs499.cpp.edu.raplibs.view.SearchResultsViewHolder;


/**
 * Created by admin on 5/29/17.
 */


public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsViewHolder> {

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


    public void swapArtists(List<Artist> newArtistList) {
        artistList = newArtistList;
        notifyDataSetChanged();
    }

    public void swapLyrics(List<Lyric> newLyricList) {
        lyricList = newLyricList;
        notifyDataSetChanged();
    }

    public void swapAlbums(List<Album> newAlbumList) {
        albumList = newAlbumList;
        notifyDataSetChanged();
    }

    public void swapAdLibs(List<AdLib> newAdLibList) {
        adLibList = newAdLibList;
        notifyDataSetChanged();
    }

    public void setArtistsOnClickListener(OnArtistClickListener artistOnClickListener){
        this.artistOnClickListener = artistOnClickListener;
    }

    public void setLyricsOnClickListener(OnLyricClickListener lyricOnClickListener){
        this.lyricOnClickListener = lyricOnClickListener;
    }

    public void setAlbumsOnClickListener(OnAlbumClickListener albumOnClickListener){
        this.albumOnClickListener = albumOnClickListener;
    }

    public void setAdLibsOnClickListener(OnAdLibClickListener adLibOnClickListener){
        this.adLibOnClickListener = adLibOnClickListener;
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_results_list_item, parent, false);
        return new SearchResultsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SearchResultsViewHolder holder, final int position) {

        if (artistList.size() >  position) {
            Artist artist = artistList.get(position);
            holder.searchArtistName.setText(artist.getArtist());
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
        } else {
            holder.artistsSearchTab.setVisibility(View.GONE);
            holder.searchArtistImage.setVisibility(View.GONE);
            holder.searchArtistName.setVisibility(View.GONE);
            holder.searchArtistContainer.setVisibility(View.GONE);
        }


        if (lyricList.size() > 2) {
            holder.lyricsSearchTab.setVisibility(View.GONE);
        }
        if (lyricList.size() > position) {
            Lyric lyric = lyricList.get(position);
            holder.searchLyric.setText(lyric.getLyric());
            holder.searchLyricSongName.setText(lyric.getSong());
            holder.searchLyricArtistName.setText(lyric.getArtist());
            holder.searchLyricAlbumName.setText(lyric.getAlbum());
            Picasso.with(holder.context)
                    .load(lyricList.get(position).getImage().replace(" ", "%20"))
                    .resize(200,200)
                    .centerCrop()
                    .into(holder.searchLyricImage);

            if(mLastAnimatedItemPosition < position){
                animateItem(holder.itemView);
                mLastAnimatedItemPosition = position;
            }

            if(lyricOnClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lyricOnClickListener.onClick(lyricList.get(position));
                    }
                });
            }
        } else {
            holder.lyricsSearchTab.setVisibility(View.GONE);
            holder.searchLyricImage.setVisibility(View.GONE);
            holder.searchLyric.setVisibility(View.GONE);
            holder.searchLyricArtistName.setVisibility(View.GONE);
            holder.searchLyricAlbumName.setVisibility(View.GONE);
            holder.searchLyricContainer.setVisibility(View.GONE);
        }


        if (albumList.size() > position) {
            Album album = albumList.get(position);
            holder.searchAlbumName.setText(album.getName());
            holder.searchAlbumArtistName.setText(album.getArtist());
            Picasso.with(holder.context)
                    .load(albumList.get(position).getImage().replace(" ", "%20"))
                    .transform(new RoundTransform(100, 1))
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.searchAlbumImage);

            if (mLastAnimatedItemPosition < position) {
                animateItem(holder.itemView);
                mLastAnimatedItemPosition = position;
            }

            if (albumOnClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        albumOnClickListener.onClick(albumList.get(position));
                    }
                });
            }
        } else {
            holder.albumsSearchTab.setVisibility(View.GONE);
            holder.searchAlbumImage.setVisibility(View.GONE);
            holder.searchAlbumArtistName.setVisibility(View.GONE);
            holder.searchAlbumName.setVisibility(View.GONE);
            holder.searchAlbumContainer.setVisibility(View.GONE);
        }


        if (adLibList.size() > position) {
            AdLib adLib = adLibList.get(position);
            holder.searchAdLib.setText(adLib.getAdlib());
            holder.searchAdLibArtistName.setText(adLib.getArtist());
            Picasso.with(holder.context)
                    .load(adLibList.get(position).getImage().replace(" ", "%20"))
                    .transform(new RoundTransform(100, 1))
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.searchAdLibImage);

            if (mLastAnimatedItemPosition < position) {
                animateItem(holder.itemView);
                mLastAnimatedItemPosition = position;
            }

            if (adLibOnClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adLibOnClickListener.onClick(adLibList.get(position));
                    }
                });
            }
        } else {
            holder.adLibsSearchTab.setVisibility(View.GONE);
            holder.searchAdLibImage.setVisibility(View.GONE);
            holder.searchAdLib.setVisibility(View.GONE);
            holder.searchAdLibArtistName.setVisibility(View.GONE);
            holder.searchAdLibContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return artistList.size() + lyricList.size() + adLibList.size() + albumList.size();
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
