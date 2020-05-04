package com.app.mexpress.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mexpress.R;
import com.app.mexpress.adapter.SavedAdapter;
import com.app.mexpress.model.SavedModel;

import java.util.ArrayList;

public class SavedFragment extends Fragment {

    ImageView savedImage, savedIcon;
    TextView techText, descriptionText, vergeText;


    Context context;
    RecyclerView savedRecycler;
    RecyclerView.Adapter savedAdapter;
    RecyclerView.LayoutManager savedLayoutManager;

    ArrayList<SavedModel> savedModelArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_saved, container, false);

        context = getActivity();

        savedIcon = root.findViewById(R.id.savedIcon);
        savedImage= root.findViewById(R.id.savedImage);
        techText= root.findViewById(R.id.techText);
        descriptionText= root.findViewById(R.id.descriptionText);
        vergeText= root.findViewById(R.id.vergeText);

        savedModelArrayList = new ArrayList<>();


        savedRecycler = root.findViewById(R.id.savedRecycler);
        savedRecycler.setHasFixedSize(true);

        savedLayoutManager =  new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        savedRecycler.setLayoutManager(savedLayoutManager);
        savedAdapter = new SavedAdapter(context, savedModelArrayList);

        savedRecycler.setAdapter(savedAdapter);

        GetSavedSomething();

        return root;
    }

    public void GetSavedSomething(){
        savedModelArrayList = new ArrayList<>();
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));
        savedModelArrayList.add(new SavedModel(R.drawable.savedimage, "Tech", "Google saw more than 18 million daily malware and phishing emails related to COVID-19 last week.", "theVerge", R.drawable.savedicon));

        savedAdapter = new SavedAdapter(context, savedModelArrayList);
        savedRecycler.setAdapter(savedAdapter);
    }
}