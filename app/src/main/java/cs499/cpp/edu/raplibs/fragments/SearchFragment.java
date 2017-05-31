package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.AdLib;
import cs499.cpp.edu.raplibs.model.Album;
import cs499.cpp.edu.raplibs.model.Lyric;
import cs499.cpp.edu.raplibs.sections.AdLibsSection;
import cs499.cpp.edu.raplibs.sections.AlbumsSection;
import cs499.cpp.edu.raplibs.sections.ArtistsSection;
import cs499.cpp.edu.raplibs.model.Artist;
import cs499.cpp.edu.raplibs.sections.LyricsSection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by admin on 5/28/17.
 */

public class SearchFragment extends Fragment {

    private final String TAG = "BlankFragment";
    private String lastQuery = "";

    private FloatingSearchView mySearchView;
    private RecyclerView recyclerView;

    private SectionedRecyclerViewAdapter sectionAdapter;
    private boolean mIsDarkSearchTheme = false;


    private ArtistsSection artistsSection;
    private LyricsSection lyricsSection;
    private AlbumsSection albumsSection;
    private AdLibsSection adLibsSection;

    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_search, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        artistsSection = new ArtistsSection();
        sectionAdapter.addSection(artistsSection);

        albumsSection = new AlbumsSection();
        sectionAdapter.addSection(albumsSection);

        lyricsSection = new LyricsSection();
        sectionAdapter.addSection(lyricsSection);

        adLibsSection = new AdLibsSection();
        sectionAdapter.addSection(adLibsSection);



        recyclerView = (RecyclerView) view.findViewById(R.id.search_results_list);
        mySearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSearchBar();
    }

    private void setUpSearchBar() {

        mySearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {


            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    sectionAdapter = new SectionedRecyclerViewAdapter();
                    artistsSection = new ArtistsSection();
                    albumsSection = new AlbumsSection();
                    lyricsSection = new LyricsSection();
                    adLibsSection = new AdLibsSection();
                    sectionAdapter.addSection(artistsSection);
                    sectionAdapter.addSection(albumsSection);
                    sectionAdapter.addSection(lyricsSection);
                    sectionAdapter.addSection(adLibsSection);
                    recyclerView.setAdapter(sectionAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {

                    artistsSection.findArtists(getActivity(), newQuery,
                            new ArtistsSection.OnFindArtistsListener() {

                                @Override
                                public void onResults(List<Artist> artistResults) {
                                    artistsSection.swapArtists(artistResults);
                                    sectionAdapter.notifyDataSetChanged();
                                    if (!artistResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });

                    albumsSection.findAlbum(getActivity(), newQuery,
                            new AlbumsSection.OnFindAlbumListener() {

                                @Override
                                public void onResults(List<Album> albumResults) {
                                    albumsSection.swapAlbums(albumResults);
                                    sectionAdapter.notifyDataSetChanged();
                                    if (!albumResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });

                    lyricsSection.findLyrics(getActivity(), newQuery,
                            new LyricsSection.OnFindLyricsListener() {

                                @Override
                                public void onResults(List<Lyric> lyricResults) {
                                    lyricsSection.swapLyrics(lyricResults);
                                    sectionAdapter.notifyDataSetChanged();
                                    if (!lyricResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });

                    adLibsSection.findAdLibs(getActivity(), newQuery,
                            new AdLibsSection.OnFindAdLibsListener() {

                                @Override
                                public void onResults(List<AdLib> adLibResults) {
                                    adLibsSection.swapAdLibs(adLibResults);
                                    sectionAdapter.notifyDataSetChanged();
                                    if (!adLibResults.contains(newQuery)) {
                                        //handle
                                    }
                                }

                            });
                    lastQuery = newQuery;

                }
            }
        });
    }
}