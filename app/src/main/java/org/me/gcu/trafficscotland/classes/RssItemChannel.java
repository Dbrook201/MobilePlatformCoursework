package org.me.gcu.trafficscotland.classes;

import java.util.ArrayList;
import java.util.Date;

public class RssItemChannel extends ArrayList<RssItemChannel> {

    private String Title;
    private String Description;
    private String Link;
    private Date pubDate;
    private int Ttl;
    private ArrayList<RssItem> ChannelItems;

    public RssItemChannel() {
        this.Title = "";
        this.Description = "";
        this.Link = "";
        this.Ttl = 0;
        this.ChannelItems = new ArrayList<RssItem>();
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    //Getters
    public String getTitle() {
        return Title;
    }

    //    The Setters
    public void setTitle(String title) {
        this.Title = title;
    }

    //public void addItemList(Item item){ChannelItems.add(item);}
    public void addItemList(RssItem item) {
        this.ChannelItems.add(item);
    }

    public ArrayList<RssItem> getChannelItems() {
        return ChannelItems;
    }


}
