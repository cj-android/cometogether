package com.cj.cometogether.database;

import org.litepal.crud.DataSupport;

/**
 * 数据库的映射表，存储需要的信息
 * Created by pc on 2017/3/4.
 */

public class Thing extends DataSupport
{
    private int id;

    private int thingId;

    private String title;

    private String photoUrl;

    private double longtitude;

    private double latitude;

    private String location;

    private int date;

    private int time;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getThingId()
    {
        return thingId;
    }

    public void setThingId(int thingId)
    {
        this.thingId = thingId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public double getLongtitude()
    {
        return longtitude;
    }

    public void setLongtitude(double longtitude)
    {
        this.longtitude = longtitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getDate()
    {
        return date;
    }

    public void setDate(int date)
    {
        this.date = date;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
}
