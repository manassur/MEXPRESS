package com.app.mexpress.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.app.mexpress.R;
import com.app.mexpress.adapter.CategoryAdapter;
import com.app.mexpress.adapter.FeaturedNewsAdapter;
import com.app.mexpress.model.CategoryModel;
import com.app.mexpress.model.FeaturedModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    ProgressBar progressDialog;
    Context context;
    TextView noResultText, seeAllTxt, seeAllTxt1;
    LinearLayout searching;
    SearchView search;
    RecyclerView featured_recyclerView,category_recyclerView;
    RecyclerView.Adapter featured_adapter,category_adapter;
    RecyclerView.LayoutManager featured_manager,category_manager;
    ProgressDialog pdialog;
    ArrayList<CategoryModel> category_list;
    ArrayList<FeaturedModel> featured_list;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        context = getActivity();
        pdialog= new ProgressDialog(context);
        pdialog.setMessage("Fetching Sports");

        searching = root.findViewById(R.id.searching);
        noResultText = root.findViewById(R.id.noResultText);



        searching.setVisibility(View.GONE);
        noResultText.setVisibility(View.GONE);

        progressDialog = root.findViewById(R.id.progressBar);


         search = root.findViewById(R.id.search);
        int id = search.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = (TextView) search.findViewById(id);
        Typeface tf = ResourcesCompat.getFont(context,R.font.circularstd_book);
        searchText.setTypeface(tf);

         search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                // searchUsers(query);
                 return true;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                // searchUsers(newText);
                 return true;
             }
         });



        featured_list = new ArrayList<>();
        category_list = new ArrayList<>();

        featured_recyclerView = root.findViewById(R.id.featuredrec);
        category_recyclerView = root.findViewById(R.id.categoryrec);

        featured_recyclerView.setHasFixedSize(true);
        category_recyclerView.setHasFixedSize(true);

        category_manager =  new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        featured_manager = new GridLayoutManager(context, 2);

        featured_recyclerView.setLayoutManager(featured_manager);
        category_recyclerView.setLayoutManager(category_manager);

        featured_adapter = new FeaturedNewsAdapter(context, featured_list);
        category_adapter = new CategoryAdapter(context, category_list);


        featured_recyclerView.setAdapter(featured_adapter);
        category_recyclerView.setAdapter(category_adapter);



        GetCategory();
        GetFeaturedNews();

        return root;
    }

    public void GetFeaturedNews(){
        featured_list = new ArrayList<>();
       featured_list.add(new FeaturedModel("https://media.giphy.com/media/ignaF0h8CcPt5OT4DL/giphy.gif","This samsung mobile phone is the best","Entertainment"));
        featured_list.add(new FeaturedModel("https://media.giphy.com/media/NWg7M1VlT101W/giphy.gif","Tiktok videos hit 1million desires","Sports"));
        featured_list.add(new FeaturedModel("https://media.giphy.com/media/oxFDq4E9CHb7W/giphy.gif","Corp member deceives secondary school students","Crime"));
        featured_list.add(new FeaturedModel("https://media.giphy.com/media/sH0EqrGxUCCGc/giphy.gif","Look at the side of this phone","Politics"));

        featured_adapter = new FeaturedNewsAdapter(context, featured_list);
        featured_recyclerView.setAdapter(featured_adapter);
    }

    public void GetCategory(){
        category_list = new ArrayList<>();
        category_list.add(new CategoryModel("Technology"));
        category_list.add(new CategoryModel("Science"));
        category_list.add(new CategoryModel("Finance"));
        category_list.add(new CategoryModel("Crime"));


        category_adapter = new CategoryAdapter(context, category_list);
        category_recyclerView.setAdapter(category_adapter);
    }

//    private void fetchUsers() {
//        pdialog.show();
//
//        UserSessionManager session = new UserSessionManager(context);
//        VolleyAssister volleyAssister = new VolleyAssister();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("session_id",session.getUserDetails().get("session_id"));
//        parameters.put("session_token",session.getUserDetails().get("session_token"));
//
//
//
//
//        volleyAssister.MakeRequest(Request.Method.POST, AppConfig.GET_USERS,parameters,context, new VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//                pdialog.dismiss();
//                Feedback.showShortText(context,message);
//            }
//
//            @Override
//            public void onResponse(String response) {
//                pdialog.dismiss();
//
//                if(VolleyAssister.getResponseStatus(response));{
//
//                    final ArrayList<LatestModel> users= new Gson().fromJson (
//                            VolleyAssister.getResponseData(response),
//                            new TypeToken<ArrayList<LatestModel>>() {
//                            }.getType());
//
//                    featured_list = users;
//
////                    //Alphabetic Order
////                    Collections.sort(featured_list, new Comparator<LatestModel>() {
////                        @Override
////                        public int compare(LatestModel leftSide, LatestModel rightSide) {
////                            return leftSide.getCustomerFirstName().compareTo(rightSide.getCustomerLastName());
////                        }
////                    });
//
//                    featured_adapter = new FeaturedNewsAdapter(context, featured_list);
//                    featured_recyclerView.setAdapter(featured_adapter);
//                    }
//                }
//
//
//        });
//
//    }
//    //displaying tailor customer
//    private void searchUsers(final String searchQuery) {
//        searching.setVisibility(View.VISIBLE);
//        noResultText.setVisibility(View.GONE);
//
//        UserSessionManager session = new UserSessionManager(context);
//        VolleyAssister volleyAssister = new VolleyAssister();
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("session_id",session.getUserDetails().get("session_id"));
//        parameters.put("session_token",session.getUserDetails().get("session_token"));
//        parameters.put("search_word",searchQuery);
//
//
//
//
//        volleyAssister.MakeRequest(Request.Method.POST,AppConfig.SEARCH_USERS,parameters,context, new VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//                searching.setVisibility(View.GONE);
//                progressDialog.setVisibility(View.GONE);
//                Feedback.showShortText(context,message);
//            }
//
//            @Override
//            public void onResponse(String response) {
//                searching.setVisibility(View.GONE);
//
//                noResultText.setVisibility(View.GONE);
//                featured_list.clear();
//                if(VolleyAssister.getResponseStatus(response));{
//
//                    final ArrayList<LatestModel> users= new Gson().fromJson (
//                            VolleyAssister.getResponseData(response),
//                            new TypeToken<ArrayList<LatestModel>>() {
//                            }.getType());
//
//                    featured_list = users;
//
//
//                    if (featured_list.isEmpty()){
//
//                        noResultText.setVisibility(View.VISIBLE);
//                    }
//                    else {noResultText.setVisibility(View.GONE);}
//
//                    //Alphabetic Order
////                    Collections.sort(featured_list, new Comparator<LatestModel>() {
////                        @Override
////                        public int compare(LatestModel leftSide, LatestModel rightSide) {
////                            return leftSide.getCustomerFirstName().compareTo(rightSide.getCustomerLastName());
////                        }
////                    });
//
//                    featured_adapter = new FeaturedNewsAdapter(context, featured_list);
//                    featured_recyclerView.setAdapter(featured_adapter);
//                }
//            }
//
//
//        });
//
//    }
//


    }