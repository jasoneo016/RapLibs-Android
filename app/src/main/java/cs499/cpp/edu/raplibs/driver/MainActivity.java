package cs499.cpp.edu.raplibs.driver;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.fragments.RecentFragment;
import cs499.cpp.edu.raplibs.fragments.FavoritesFragment;
import cs499.cpp.edu.raplibs.fragments.HomeFragment;
import cs499.cpp.edu.raplibs.fragments.SearchFragment;


public class MainActivity extends AppCompatActivity {

    public BottomBar bottomBar;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getBaseContext();


        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);
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
                    case R.id.tab_search:
                        fragment = new SearchFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch(tabId)
                {
                    case R.id.tab_home:
                        HomeFragment fragment = new HomeFragment();
                        fragmentManager.popBackStack();
//                        fragmentTransaction.replace(R.id.fragmentContainer, new HomeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_recent:
                        fragmentManager.popBackStack();
                        break;
                    case R.id.tab_favorites:
                        fragmentManager.popBackStack();
                        break;
                    case R.id.tab_search:
                        fragmentManager.popBackStack();
                        break;
                }
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }

//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
//            finish();
//        }
//        else {
//            super.onBackPressed();
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
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