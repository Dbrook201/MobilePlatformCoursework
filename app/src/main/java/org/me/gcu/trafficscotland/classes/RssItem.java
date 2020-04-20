// Matric Number: S1828977

package org.me.gcu.trafficscotland.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RssItem {
    private String Title;
    private String Description;
    private Date pubDate;
    private ArrayList<RssItem> item_list;

//

    //Initial constructor
    public RssItem() {

        this.Title = "";
        this.Description = "";
        this.pubDate = new Date();
        this.item_list = new ArrayList<RssItem>();
    }

    public RssItem(String title, String desc, Date DatePublished, ArrayList<RssItem> item_list) {
        //Set Item details
        this.Title = title;
        this.Description = desc;
        this.pubDate = new Date(String.valueOf(DatePublished));
        this.item_list = item_list;
    }

//

    //Getters
    public String getTitle() {
        return Title;
    }

    //Setters
    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String desc) {
        Description = desc;
    }

    public Date getPublishDate() {
        return pubDate;
    }

    public void setPubDate(Date DatePublished) {
        pubDate = DatePublished;
    }


    public ArrayList<RssItem> getItem_list() {
        return item_list;
    }

}
