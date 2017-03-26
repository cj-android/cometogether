package com.cj.cometogether.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cj.cometogether.R;
import com.cj.cometogether.database.Thing;
import com.cj.cometogether.util.BitmapLoader;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;

/**
 * 用于新建Thing的基本信息获取
 * Created by pc on 2017/3/1.
 */

public class NewActivity extends AppCompatActivity
{
    public static final int TAKE_PHOTO = 1;

    private Button postbutton;

    private ImageView photoImageView;

    private EditText titleEditText;

    private Button takePhotoButton;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        postbutton = (Button)findViewById(R.id.postButton);
        photoImageView = (ImageView)findViewById(R.id.photoImageView);
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        takePhotoButton = (Button) findViewById(R.id.takePhotoButton);

        postbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bundle.putString("title", titleEditText.getText().toString());
                Intent intentToMapActivity = new Intent(NewActivity.this, MapActivity.class);
                intentToMapActivity.putExtra("bundle", bundle);
                startActivity(intentToMapActivity);
            }
        });

        takePhotoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK)
                {
                    bundle = data.getExtras();
                    photoImageView.setImageBitmap((Bitmap) bundle.get("data"));
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
