package com.app.mexpress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.mexpress.R;
import com.app.mexpress.model.SavedModel;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {

    Context context;
    View view;
    SavedAdapter.ViewHolder viewHolder;
    ArrayList<SavedModel> savedModel;

    public SavedAdapter(Context context1, ArrayList<SavedModel> savedModelArrayList) {

        savedModel = savedModelArrayList;
        context = context1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView savedImage, savedIcon;
        public TextView techText, descriptionText, vergeText;


        public ViewHolder(View v){
            super(v);
           savedIcon = v.findViewById(R.id.savedIcon);
           savedImage= v.findViewById(R.id.savedImage);
            techText= v.findViewById(R.id.techText);
            descriptionText= v.findViewById(R.id.descriptionText);
            vergeText= v.findViewById(R.id.vergeText);

            v.setOnClickListener(this);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, UserProfile.class);
//            String userid = String.valueOf(itemView.getTag());
//            intent.putExtra("userid",userid );
//            context.startActivity(intent);
        }
    }

    @Override
    public  SavedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.savedrowlayout,parent,false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SavedAdapter.ViewHolder holder, final int position) {
        SavedModel currentSavedModel = savedModel.get(position);

       holder.savedImage.setImageDrawable(context.getResources().getDrawable(currentSavedModel.getSavedImage()));
        holder.savedIcon.setImageDrawable(context.getResources().getDrawable(currentSavedModel.getSavedIcon()));
        holder.techText.setText(currentSavedModel.getTechText());
        holder.descriptionText.setText(currentSavedModel.getDescriptionText());
        holder.vergeText.setText(currentSavedModel.getVergeText());




//        holder.username.setText(currentLatestModel.getUsername());
//        holder.itemView.setTag(currentLatestModel.getId());
//        Glide.with(context).load(MediaManager.get().url().generate(currentLatestModel.getProfile_image())).into(holder.customerImage);

        // holder.username.setText(currentLatestModel.getCustomerNumber());
    }

    @Override
    public int getItemCount() {
        return savedModel.size();
    }
}
