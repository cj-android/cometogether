package com.cj.cometogether.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cj.cometogether.ListViewContent.Item;
import com.cj.cometogether.ListViewContent.ItemAdapter;
import com.cj.cometogether.R;
import com.cj.cometogether.Service.UpdateLocationService;
import com.cj.cometogether.database.Thing;
import com.cj.cometogether.util.GlobalPara;
import com.cj.cometogether.util.DataManager;
import com.cj.cometogether.util.LocationQueryer;
import com.cj.cometogether.util.MemoryCache;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity，包含显示Thing的ListView
 * Created by pc on 2017/3/1.
 */

public class HistoryActivity extends AppCompatActivity
{
    private ListView listView;

    private Button postThingButton;

    private ItemAdapter adapter;

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //初始化Context提供工具
        if(!GlobalPara.Init(getApplicationContext()))
        {
            finish();
        }
        //初始化位置工具
        if(LocationQueryer.GetIstance() == null)
        {
            finish();
        }
        //初始化数据管理器
        DataManager.InitDatabaseUtil();
        //初始化内存Cache工具
        MemoryCache.Init();
        //启动网络服务
        Intent startLocationUupdateService = new Intent(this, UpdateLocationService.class);
        startService(startLocationUupdateService);

        //关联View
        setContentView(R.layout.activity_history);

        //获取ListView，并设置监听及Adapter
        listView = (ListView)findViewById(R.id.list_view);

        adapter = new ItemAdapter(HistoryActivity.this, R.layout.item, itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(HistoryActivity.this, LookingActivity.class);

                Item item = itemList.get(position);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("location", item.getLocation());
                intent.putExtra("itemId", item.getItemId());
                startActivity(intent);
            }
        });

        //获取Button，并设置监听
        postThingButton = (Button)findViewById(R.id.postThingButton);
        postThingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentToNewActivity = new Intent(HistoryActivity.this, NewActivity.class);
                startActivity(intentToNewActivity);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    protected  void onResume()
    {
        super.onResume();

        //刷新ListView
        refreshItemListView();
    }

    private void refreshItemListView()
    {
        int sizeOfThingList = DataManager.ThingList.size();
        int sizeOfItemList = itemList.size();

        //新的Thing置于ThingList的首位，以出现在ListView顶端
        for(int i = sizeOfItemList; i < sizeOfThingList ; i++)
        {
            Thing thing = DataManager.ThingList.get(i);
            Item item = new Item(thing.getId(), thing.getTitle(), thing.getLocation(), thing.getPhotoUrl());
            adapter.insert(item, 0);
        }
     }
}
