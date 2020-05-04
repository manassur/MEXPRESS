package com.app.mexpress.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mexpress.NewsPageActivity;
import com.app.mexpress.R;
import com.app.mexpress.model.LatestModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class LastestNewsAadapter extends RecyclerView.Adapter<LastestNewsAadapter.ViewHolder> {

    Context context;
    View view;
    LastestNewsAadapter.ViewHolder viewHolder;
    ArrayList<LatestModel> latestModel;

    public LastestNewsAadapter(Context context1, ArrayList<LatestModel> latestModelArrayList) {

        latestModel = latestModelArrayList;
        context = context1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView username;
        public CircleImageView customerImage;

        public ViewHolder(View v){
            super(v);
//            username = v.findViewById(R.id.username);
//            customerImage = v.findViewById(R.id.profile_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, NewsPageActivity.class);
            context.startActivity(intent);

//            Intent intent = new Intent(context, UserProfile.class);
//            String userid = String.valueOf(itemView.getTag());
//            intent.putExtra("userid",userid );
//            context.startActivity(intent);
        }
    }

    @Override
    public  LastestNewsAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.latestnews_rowlayout,parent,false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LastestNewsAadapter.ViewHolder holder, final int position) {
        LatestModel currentLatestModel = latestModel.get(position);



//        holder.username.setText(currentLatestModel.getUsername());
//        holder.itemView.setTag(currentLatestModel.getId());
//        Glide.with(context).load(MediaManager.get().url().generate(currentLatestModel.getProfile_image())).into(holder.customerImage);

        // holder.username.setText(currentLatestModel.getCustomerNumber());
    }

    @Override
    public int getItemCount() {
        return latestModel.size();
    }
}
