package com.app.mexpress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.mexpress.R;
import com.app.mexpress.model.SourceModel;

import java.util.ArrayList;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {

    Context context;
    View view;
    SourceAdapter.ViewHolder viewHolder;
    ArrayList<SourceModel> sourceModel;

    public SourceAdapter(Context context1, ArrayList<SourceModel> sourceModelArrayList) {

        sourceModel = sourceModelArrayList;
        context = context1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView sourceImage;
        public ImageView sourceImagebg;

        public ViewHolder(View v){
            super(v);
           sourceImage = v.findViewById(R.id.sourceImage);
            sourceImagebg = v.findViewById(R.id.sourceImagebg);
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
    public  SourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.newssourcerowlayout,parent,false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SourceAdapter.ViewHolder holder, final int position) {
        SourceModel currentSourceModel = sourceModel.get(position);

       holder.sourceImage.setImageDrawable(context.getResources().getDrawable(currentSourceModel.getSourceImage()));
        holder.sourceImagebg.setImageDrawable(context.getResources().getDrawable(currentSourceModel.getSourceImagebg()));

//        holder.username.setText(currentLatestModel.getUsername());
//        holder.itemView.setTag(currentLatestModel.getId());
//        Glide.with(context).load(MediaManager.get().url().generate(currentLatestModel.getProfile_image())).into(holder.customerImage);

        // holder.username.setText(currentLatestModel.getCustomerNumber());
    }

    @Override
    public int getItemCount() {
        return sourceModel.size();
    }
}
