package com.app.mexpress.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mexpress.R;
import com.app.mexpress.adapter.SourceAdapter;
import com.app.mexpress.model.SourceModel;

import java.util.ArrayList;

public class SourceFragment extends Fragment {

    ImageView sourceImage;
    ImageView sourceImagebg;


    Context context;
    RecyclerView sourceRecycler;
    RecyclerView.Adapter sourceAdapter;
    RecyclerView.LayoutManager sourceLayoutManager;
    SearchView search;

    ArrayList<SourceModel> sourceModelArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_source, container, false);

        context = getActivity();

        search = root.findViewById(R.id.search);
        int id = search.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = (TextView) search.findViewById(id);
        Typeface tf = ResourcesCompat.getFont(context,R.font.circularstd_book);
        searchText.setTypeface(tf);


        sourceImage = root.findViewById(R.id.sourceImage);

        sourceImagebg = root.findViewById(R.id.sourceImagebg);


        sourceModelArrayList = new ArrayList<>();


        sourceRecycler = root.findViewById(R.id.newsSourceRecycler);

        sourceLayoutManager = new GridLayoutManager(context,  3);

        sourceRecycler.setLayoutManager(sourceLayoutManager);
        sourceAdapter = new SourceAdapter(context, sourceModelArrayList);

        sourceRecycler.setAdapter(sourceAdapter);

        GetNewsSource();

        return root;
    }

    public void GetNewsSource(){
        sourceModelArrayList = new ArrayList<>();
        sourceModelArrayList.add(new SourceModel(R.drawable.npunch, R.drawable.newssource_punch));
        sourceModelArrayList.add(new SourceModel(R.drawable.ndailytrust, R.drawable.newssource_daily));
        sourceModelArrayList.add(new SourceModel(R.drawable.nbellanaija, R.drawable.newssource_bella));
        sourceModelArrayList.add(new SourceModel(R.drawable.nzikoko, R.drawable.newssource_zikoko));
        sourceModelArrayList.add(new SourceModel(R.drawable.nlegit, R.drawable.newssource_legit));
        sourceModelArrayList.add(new SourceModel(R.drawable.nynaija, R.drawable.newssource_ynaija));

        sourceAdapter = new SourceAdapter(context, sourceModelArrayList);
        sourceRecycler.setAdapter(sourceAdapter);
    }


}