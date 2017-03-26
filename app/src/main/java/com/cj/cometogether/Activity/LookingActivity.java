package com.cj.cometogether.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cj.cometogether.R;
import com.cj.cometogether.util.BitmapLoader;

/**
 * 详情Activity，用于显示Thing的具体信息
 * Created by pc on 2017/3/1.
 */

public class LookingActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking);

        TextView title = (TextView) findViewById(R.id.titleInLookingActivity);
        ImageView photo = (ImageView) findViewById(R.id.photoInLookingActivity);
        TextView location = (TextView) findViewById(R.id.locationInLookingActivity);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        BitmapLoader.LoadBitmapToShow(intent.getIntExtra("itemId", 0), BitmapLoader.MAX, photo);
        location.setText(intent.getStringExtra("location"));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
