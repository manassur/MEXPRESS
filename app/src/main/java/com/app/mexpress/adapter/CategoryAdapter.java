package com.app.mexpress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mexpress.R;
import com.app.mexpress.model.CategoryModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    View view;
    CategoryAdapter.ViewHolder viewHolder;
    ArrayList<CategoryModel> customerModel;

    public CategoryAdapter(Context context1, ArrayList<CategoryModel> customerModelArrayList) {

        customerModel = customerModelArrayList;
        context = context1;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        public CircleImageView customerImage;

        public ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public  CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.categories_rowlayout,parent,false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, final int position) {
        CategoryModel currentCustomerModel = customerModel.get(position);



        holder.name.setText(currentCustomerModel.getName());
       // holder.itemView.setTag(currentCustomerModel.getId());

        // holder.username.setText(currentCustomerModel.getCustomerNumber());
    }

    @Override
    public int getItemCount() {
        return customerModel.size();
    }
}
