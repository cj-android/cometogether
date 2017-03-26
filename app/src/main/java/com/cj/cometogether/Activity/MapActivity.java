package com.cj.cometogether.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.cj.cometogether.R;
import com.cj.cometogether.database.Thing;
import com.cj.cometogether.util.DataManager;
import com.cj.cometogether.util.LocationQueryer;

import java.util.Observable;
import java.util.Observer;

/**
 * 用于系统获取新建Thing的位置信息
 * Created by pc on 2017/3/1.
 */

public class MapActivity extends AppCompatActivity implements Observer
{
    private Button postButton;

    private MapView mapView;

    private TextView addressFrontTextView;

    private EditText addressBackEditText;

    private BaiduMap baiduMap;

    private double targetLatitude;

    private double targetLongitude;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_map);

        postButton = (Button)findViewById(R.id.postButton);
        mapView = (MapView)findViewById(R.id.mapView);
        addressFrontTextView = (TextView)findViewById(R.id.addressFrontPartTextView);
        addressBackEditText = (EditText)findViewById(R.id.addressBackPartEditText);

        //地图显示参数
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        MapStatusUpdate update = MapStatusUpdateFactory.zoomTo(16f);
        baiduMap.setMapStatus(update);

        //拿经纬度和区县街道信息以显示，但数据不准确，会进行异步修正
        targetLatitude = LocationQueryer.GetIstance().getmLatitude();
        targetLongitude = LocationQueryer.GetIstance().getmLongitude();
        addressFrontTextView.setText(LocationQueryer.GetIstance().GetAddress());
        //居中我的位置
        centeredTargetLocation();
        //绘制位置指示点
        drawTargetLocationDot();

        //修正位置
        LocationQueryer.GetIstance().GetCurrentLocation();

        postButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentFromNewActivity = getIntent();
                bundle = intentFromNewActivity.getBundleExtra("bundle");

                Bitmap b = (Bitmap)bundle.get("data");

                Thing thing = new Thing();
                thing.setTitle(bundle.getString("title"));
                thing.setLatitude(targetLatitude);
                thing.setLongtitude(targetLongitude);
                thing.setLocation(addressFrontTextView.getText().toString() + addressBackEditText.getText().toString());
                //由于没有服务端，暂时同步写入数据库，以免拿不到缓存数据的key
                if(thing.save())
                {
                    DataManager.AddThing(thing, b);
                }
                Intent toHistoryActivity = new Intent(MapActivity.this, HistoryActivity.class);
                startActivity(toHistoryActivity);

                finish();
            }
        });

        LocationQueryer.GetIstance().addObserver(this);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        LocationQueryer.GetIstance().deleteObserver(this);
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    private void centeredTargetLocation()
    {
        LatLng ll = new LatLng(targetLatitude, targetLongitude);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baiduMap.animateMapStatus(update);
    }
    private void drawTargetLocationDot()
    {
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(targetLatitude);
        locationBuilder.longitude(targetLongitude);
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    //观察LocationQueryer的经纬度信息改变，以便位置修正后正确显示
    @Override
    public void update(Observable o, Object arg)
    {
        targetLatitude = LocationQueryer.GetIstance().getmLatitude();
        targetLongitude = LocationQueryer.GetIstance().getmLongitude();
        centeredTargetLocation();
        drawTargetLocationDot();
    }
}
