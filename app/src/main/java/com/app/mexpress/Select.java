package com.app.mexpress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.app.mexpress.trimmer.K4LVideoTrimmer;
import com.app.mexpress.trimmer.interfaces.OnK4LVideoListener;
import com.app.mexpress.trimmer.interfaces.OnTrimVideoListener;
import com.app.mexpress.trimmer.utils.FileUtils;
import com.app.mexpress.trimmer.utils.TrimVideoUtils;
import com.app.mexpress.utillity.AppConfig;
import com.bumptech.glide.Glide;
import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


public class Select extends AppCompatActivity implements OnTrimVideoListener, OnK4LVideoListener {
    ProgressDialog p;
    K4LVideoTrimmer videoTrimmer;
    Context context = Select.this;
    OnTrimVideoListener tvl;
    String gifUrl, imagename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        videoTrimmer = findViewById(R.id.timeLine);
        p = new ProgressDialog(this);
        p.setMessage("Processing");
        //videoTrimmer.setDestinationPath("/storage/emulated/0/DCIM/CameraCustom/");

        if (videoTrimmer != null) {
            videoTrimmer.setMaxDuration(10);
            videoTrimmer.setOnTrimVideoListener(this);
            videoTrimmer.setOnK4LVideoListener(this);
            videoTrimmer.setVideoURI(Uri.parse(getIntent().getStringExtra("path")));
            videoTrimmer.setVideoInformationVisibility(true);


            Log.d("getpath3", String.valueOf(Uri.parse(getIntent().getStringExtra("path"))));



        }


    }

    @Override
    public void onTrimStarted() {
    }

    @Override
    public void getResult(final Uri uri) {
        // handle K4LVideoTrimmer result.

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("getpath4", String.valueOf(uri));
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//use one of overloaded setDataSource() functions to set your data source
                retriever.setDataSource(getApplicationContext(), uri);
                String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                //String lenght = retriever.extractMetadata(MediaMetadataRetriever.METADATA);

                long millis = Long.parseLong(time);
                //long minutes = (millis / 1000)  / 60;
                long seconds = (millis / 1000);
                retriever.release();
                // shurvrivate(seconds,uri);
                CompressandSaveImageToCloudinary(uri);
            }
        });

    }

    @Override
    public void cancelAction() {
        Log.d("cancled clicked", "");

        videoTrimmer.destroy();
        finish();
    }

    @Override
    public void onError(String message) {
        Log.d("error", message);
    }

    public void shurvrivate(long millis, Uri uri) {
        final File file = new File(uri.getPath());
        int whole_part = Math.round(millis / 30);
        int no_of_splits = 0;
        if (millis % 30 > 0) {
            no_of_splits = whole_part + 1;
        }
        Log.d("number_of_splits", String.valueOf(no_of_splits));
        Integer new_vid_length = (int) (long) millis;
        Log.d("total no of secs", String.valueOf(new_vid_length));
        ArrayList<Uri> mk = new ArrayList<Uri>();
        int part_not_up_to_30 = new_vid_length - (whole_part * 30);
        Log.d("part remaining by 30", String.valueOf(part_not_up_to_30));
        int start = 0;
        int finish;
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("video/mp4");
        if (part_not_up_to_30 <= 30 && no_of_splits == 1) {
            finish = part_not_up_to_30;
        } else {
            finish = 30;
        }

        String root = Environment.getExternalStorageDirectory().toString();

        File myDir = new File(root + "/.videoabet");
        myDir.mkdirs();
        if (myDir.isDirectory()) {

            File[] c = myDir.listFiles();
            for (File ch : c) {
                ch.delete();
            }
        }
        for (int i = 0; i < no_of_splits; i++) {
            try {

                mk.add(TrimVideoUtils.startTrim2(file, Environment.getExternalStorageDirectory() + "/.videoabet/", start * 1000, finish * 1000, tvl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("start", String.valueOf(start));
            Log.d("finish", String.valueOf(finish));
            Log.d("i", String.valueOf(i));
            start += 30;
            if (i == no_of_splits - 1) {
                finish = finish + part_not_up_to_30;

            } else {
                finish = finish + start;
            }

            //  if (tvl != null tvl.onTrimStarted();

       /*     BackgroundExecutor.execute(
                    new BackgroundExecutor.Task("", 0L, "") {
                        @Override
                        public void execute() {
                            try {
                                TrimVideoUtils.startTrim(file, getDestinationPath(), start, finish, tvl);
                            } catch (final Throwable e) {
                                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                            }
                        }
                    }
            );*/


        }
        sharingIntent.putExtra(Intent.EXTRA_STREAM, mk);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));


    }

    public void CompressandSaveImageToCloudinary(Uri uri) {

        imagename = System.currentTimeMillis() + "";


        MediaManager.get().upload(uri.getPath())
                // .option("unique_filename",false)
                .option("public_id", imagename)
                .unsigned(AppConfig.CLOUDINARY_UNSIGNED)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {

                        Toast.makeText(context, "Starting", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        long progress = bytes / totalBytes;
                        // pdialog.setProgress((int)progress);
                        Toast.makeText(context, "progress :" + (int) progress, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        Toast.makeText(context, "completed", Toast.LENGTH_SHORT).show();

                        //   Glide.with(context).load(MediaManager.get().url().generate(imageName)).into(profile_image);
                        String publicId = resultData.get("public_id").toString();

                        gifUrl = MediaManager.get().url().resourceType("video")
                                .transformation(new Transformation().videoSampling("25")
                                        .delay("200").height(200).effect("loop:10").crop("scale"))
                                .format("gif").generate(publicId);

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("gifUrl", gifUrl);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        Log.d("errordes",error.getDescription());
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                }).dispatch();


    }



    @Override
    public void onVideoPrepared() {

    }
}
