package cs499.cpp.edu.raplibs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs499.cpp.edu.raplibs.R;
import cs499.cpp.edu.raplibs.model.AdLib;
import cs499.cpp.edu.raplibs.view.AdLibViewHolder;

/**
 * Created by admin on 5/29/17.
 */

public class AdLibsFragment extends Fragment {

    private RecyclerView recyclerView;

    private DatabaseReference dbRef;
    private DatabaseReference adLibsRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbRef = FirebaseDatabase.getInstance().getReference();
        adLibsRef = dbRef.child("adlibs");

        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<AdLib,AdLibViewHolder> adapter = new FirebaseRecyclerAdapter<AdLib,AdLibViewHolder>(
                AdLib.class, R.layout.listview_adlib_item, AdLibViewHolder.class, adLibsRef) {

            @Override
            protected void populateViewHolder(AdLibViewHolder viewHolder, AdLib adLib, int position)
            {
                viewHolder.bindImage(adLib);
            }

            @Override
            public AdLibViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                AdLibViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new AdLibViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return recyclerView;
    }
}