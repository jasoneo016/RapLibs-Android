//package cs499.cpp.edu.raplibs.adapters;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cs499.cpp.edu.raplibs.R;
//import cs499.cpp.edu.raplibs.model.AdLib;
//import cs499.cpp.edu.raplibs.model.Album;
//import cs499.cpp.edu.raplibs.model.Artist;
//import cs499.cpp.edu.raplibs.model.Lyric;
//
///**
// * Created by admin on 5/29/17.
// */
//
//
//public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {
//
//    private List<Artist> artistList = new ArrayList<>();
//    private List<Album> albumList = new ArrayList<>();
//    private List<Lyric> lyricList = new ArrayList<>();
//    private List<AdLib> adLibList = new ArrayList<>();
//
//    private int mLastAnimatedItemPosition = -1;
//
//    public interface OnArtistClickListener{
//        void onClick(Artist artist);
//    }
//
//    public interface OnAlbumClickListener{
//        void onClick(Album album);
//    }
//
//    public interface OnLyricClickListener{
//        void onClick(Lyric lyric);
//    }
//
//    public interface OnAdLibClickListener{
//        void onClick(AdLib adLib);
//    }
//
//    private OnArtistClickListener artistOnClickListener;
//    private OnAlbumClickListener albumOnClickListener;
//    private OnLyricClickListener lyricOnClickListener;
//    private OnAdLibClickListener adLibOnClickListener;
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//
//        public final ImageView searchArtistImage;
//        public final TextView searchArtistName;
//        public final View searchArtistContainer;
//
//        Context context;
//
//        public ViewHolder(View view) {
//            super(view);
//            context = view.getContext();
//            searchArtistImage = (ImageView) view.findViewById(R.id.searchartistimage);
//            searchArtistName = (TextView) view.findViewById(R.id.searchartistname);
//            searchArtistContainer = view.findViewById(R.id.artist_container);
//        }
//    }
//
//    public void swapData(List<Artist> newArtistList) {
//        artistList = newArtistList;
//        notifyDataSetChanged();
//    }
//
//    public void setArtistsOnClickListener(OnArtistClickListener artistOnClickListener){
//        this.artistOnClickListener = artistOnClickListener;
//    }
//
//    @Override
//    public SearchResultsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.search_results_list_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(SearchResultsListAdapter.ViewHolder holder, final int position) {
//
//        Artist artistSuggestion = mDataSet.get(position);
//        holder.mColorName.setText(colorSuggestion.getName());
//        holder.mColorValue.setText(colorSuggestion.getHex());
//
//        int color = Color.parseColor(colorSuggestion.getHex());
//        holder.mColorName.setTextColor(color);
//        holder.mColorValue.setTextColor(color);
//
//        if(mLastAnimatedItemPosition < position){
//            animateItem(holder.itemView);
//            mLastAnimatedItemPosition = position;
//        }
//
//        if(mItemsOnClickListener != null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mItemsOnClickListener.onClick(mDataSet.get(position));
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataSet.size();
//    }
//
//    private void animateItem(View view) {
//        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
//        view.animate()
//                .translationY(0)
//                .setInterpolator(new DecelerateInterpolator(3.f))
//                .setDuration(700)
//                .start();
//    }
//}
