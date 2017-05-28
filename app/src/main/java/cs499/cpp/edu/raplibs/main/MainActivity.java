package cs499.cpp.edu.raplibs.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.fragments.RecentFragment;
import cs499.cpp.edu.raplibs.fragments.ArtistsFragment;
import cs499.cpp.edu.raplibs.fragments.FavoritesFragment;
import cs499.cpp.edu.raplibs.fragments.HomeFragment;
import cs499.cpp.edu.raplibs.model.Artist;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    private DatabaseReference artistsRef;

    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistList = new ArrayList<>();

        artistsRef = FirebaseDatabase.getInstance().getReference("artists");

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                Fragment fragment = null;

                switch (tabId) {
                    case R.id.tab_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.tab_recent:
                        fragment = new RecentFragment();
                        break;
                    case R.id.tab_favorites:
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.tab_artists:
                        fragment = new ArtistsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        artistsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                artistList.clear();
//
//                for (DataSnapshot artistSnapShot : dataSnapshot.getChildren()) {
//
//                    Artist artist = artistSnapShot.getValue(Artist.class);
//                    artistList.add(artist);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        MenuItem item = menu.findItem(R.id.menuSearch);
//        SearchView searchView = (SearchView)item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }