package com.cj.cometogether.ListViewContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cj.cometogether.R;
import com.cj.cometogether.util.BitmapLoader;

import java.util.List;

/**
 * 显示项的适配器
 * Created by pc on 2017/3/1.
 */

public class ItemAdapter extends ArrayAdapter<Item>
{
    private int itemLayoutId;
    private  int i;

    public ItemAdapter(Context context, int itemLayoutId, List<Item> objects)
    {
        super(context, itemLayoutId, objects);

        this.itemLayoutId = itemLayoutId;
        i = 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Item item = getItem(position);

        View view;
        ItemHolder itemHolder;

        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(itemLayoutId, parent, false);

            itemHolder = new ItemHolder();
            itemHolder.photoInItem = (ImageView)view.findViewById(R.id.photoInItem);
            itemHolder. titleInItem = (TextView)view.findViewById(R.id.titleInItem);
            itemHolder.locationInItem = (TextView)view.findViewById(R.id.locationInItem);

            view.setTag(itemHolder);
        }
        else
        {
            view = convertView;
            itemHolder = (ItemHolder)view.getTag();
            itemHolder.photoInItem.setImageBitmap(null);
        }

        BitmapLoader.LoadBitmapToShow(item.getItemId(), BitmapLoader.MIN, itemHolder.photoInItem);
        itemHolder.titleInItem.setText(item.getTitle());
        itemHolder.locationInItem.setText(item.getLocation());

        return view;
    }

    class ItemHolder
    {
        ImageView photoInItem;

        TextView titleInItem;

        TextView locationInItem;
    }
}
