package com.cj.cometogether.ListViewContent;

/**
 * 用于MainActivity即HistoryActivity中ListView的显示项
 * Created by pc on 2017/3/1.
 */

public class Item
{
    private int itemId;

    private int thingId;

    private String title;

    private String location;

    private String photoUrl;

    public Item(int itemId, String title, String location, String photoUrl)
    {
        this.itemId = itemId;
        this.title = title;
        this.location = location;
        this.photoUrl = photoUrl;
    }

    public int getItemId()
    {
        return itemId;
    }

    public int getThingId()
    {
        return thingId;
    }

    public void setThingId(int thingId)
    {
        this.thingId = thingId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }
}
