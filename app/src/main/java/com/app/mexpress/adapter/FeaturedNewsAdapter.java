package com.app.mexpress.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mexpress.NewsPageActivity;
import com.app.mexpress.R;
import com.app.mexpress.model.FeaturedModel;
import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class FeaturedNewsAdapter extends RecyclerView.Adapter<FeaturedNewsAdapter.ViewHolder> {

    Context context;
    View view;
    FeaturedNewsAdapter.ViewHolder viewHolder;
    ArrayList<FeaturedModel> customerModel;


    public FeaturedNewsAdapter(Context context1, ArrayList<FeaturedModel> customerModelArrayList) {

        customerModel = customerModelArrayList;
        context = context1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title,category;
        public ImageView image;
        RoundRectView imageframe;

        public ViewHolder(View v){
            super(v);
            title = v.findViewById(R.id.title);
            category = v.findViewById(R.id.category);
            image = v.findViewById(R.id.image);
            imageframe = v.findViewById(R.id.imageframe);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NewsPageActivity.class);
            String item = new Gson().toJson(itemView.getTag());
            intent.putExtra("item",item );
            context.startActivity(intent);
        }
    }

    @Override
    public  FeaturedNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.featurednews_rowlayout,parent,false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FeaturedNewsAdapter.ViewHolder holder, final int position) {
        FeaturedModel currentCustomerModel = customerModel.get(position);




        holder.itemView.setTag(currentCustomerModel);
        holder.title.setText(currentCustomerModel.getTitle());
        holder.category.setText(currentCustomerModel.getCategory());

        if(position>=3 & position% 3 == 0){
            holder.imageframe.setBottomLeftDiameter(20);
            holder.imageframe.setTopLeftDiameter(0);
            holder.imageframe.setTopRightDiameter(20);
            holder.imageframe.setBottomRightDiameter(20);

        }

        else  if(position>=2 & position% 2 == 0){
            holder.imageframe.setBottomLeftDiameter(20);
            holder.imageframe.setTopLeftDiameter(20);
            holder.imageframe.setTopRightDiameter(0);
            holder.imageframe.setBottomRightDiameter(20);

        }

       else if(position % 2  == 0 ) {
            holder.imageframe.setBottomLeftDiameter(20);
            holder.imageframe.setTopRightDiameter(20);
            holder.imageframe.setTopLeftDiameter(20);
            holder.imageframe.setBottomRightDiameter(0);

        }else{
            holder.imageframe.setBottomLeftDiameter(0);
            holder.imageframe.setTopLeftDiameter(20);
            holder.imageframe.setTopRightDiameter(20);
            holder.imageframe.setBottomRightDiameter(20);

        }

        Glide.with(context).asGif().load(currentCustomerModel.getImage()).into(holder.image);

        // holder.username.setText(currentCustomerModel.getCustomerNumber());
    }

    @Override
    public int getItemCount() {
        return customerModel.size();
    }
}
