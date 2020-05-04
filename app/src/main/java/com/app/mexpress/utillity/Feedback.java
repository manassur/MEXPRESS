package com.app.mexpress.utillity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.pd.chocobar.ChocoBar;

public class Feedback {

    public static void showShortText(Context context, String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();

    }

    public static void showLongText(Context context, String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
    public static void showChocoText(Context context, String text){
        ChocoBar.builder().setActivity((Activity) context)
                .setText(text)
                .setDuration(ChocoBar.LENGTH_LONG)
                .setBackgroundColor(Color.parseColor("#949494"))
                .setTextSize(12)
                .build()  // in built green ChocoBar
                .show();
    }


}
