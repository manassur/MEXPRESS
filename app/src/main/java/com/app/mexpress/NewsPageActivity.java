package com.app.mexpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mexpress.adapter.FeaturedNewsAdapter;
import com.app.mexpress.model.FeaturedModel;
import com.app.mexpress.trimmer.utils.FileUtils;
import com.app.mexpress.utillity.Permissions;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NewsPageActivity extends AppCompatActivity {

    TextView readNewsTxt;
    RecyclerView featured_recyclerView;
    RecyclerView.Adapter featured_adapter;
    RecyclerView.LayoutManager featuredLayoutManager;
    ArrayList<FeaturedModel> featured_list;
    Context context;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap bm;
    private String userChoosenTask;
    ImageView image,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        context = NewsPageActivity.this;
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon((R.drawable.ic_arrow_back_black_24dp));

        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cameraresult= Permissions.checkCameraPermission(context);
                if(cameraresult){
                    boolean storageresult=Permissions.checkWriteStoragePermission(context);
                    if(storageresult)
                        galleryIntent();
                }
            }
        });


        FeaturedModel item = new Gson().fromJson(getIntent().getStringExtra("item"),FeaturedModel.class);
        Glide.with(context).asGif().load(item.getImage()).into(image);


        featured_list = new ArrayList<>();


        featured_recyclerView = findViewById(R.id.featuredrec);

        featuredLayoutManager = new GridLayoutManager(context, 2);

        featured_recyclerView.setLayoutManager(featuredLayoutManager);
        featured_adapter = new FeaturedNewsAdapter(context, featured_list);
        featured_recyclerView.setAdapter(featured_adapter);
        featured_recyclerView.setHasFixedSize(true);

        GetFeaturedNews();

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

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    // UPDATED!
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                Log.d("data.getdata",selectedImageUri.getPath());
                // OI FILE Manager
                selectedImageUri.getPath();
                Log.d("getpath",selectedImageUri.getPath());
                // MEDIA GALLERY

                if (FileUtils.getPath(context,selectedImageUri) != null) {
                   // Log.d("getpath2",getPath(selectedImageUri));
                    Intent intent = new Intent(NewsPageActivity.this,
                            Select.class);
                    intent.putExtra("path", FileUtils.getPath(context,selectedImageUri) );
                    startActivityForResult(intent,500);
                }
            }

        else  if(requestCode == 500){
                Glide.with(getApplicationContext()).asGif().load(getIntent().getStringExtra("gifUrl")).into(image2);

            }
        }



    }
    private void onCaptureImageResult(Intent data) {
        bm = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //byte[] b = bytes.toByteArray();
        //encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Log.d("encoded String = ",encImage);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  imageView.setImageBitmap(bm);
    }
    private void onSelectFromGalleryResult(Intent data) {

        bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  imageView.setImageBitmap(bm);

    }
}
