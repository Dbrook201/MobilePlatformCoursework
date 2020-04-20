package org.me.gcu.trafficscotland.parser;

import android.util.Log;

import org.me.gcu.trafficscotland.Actitivies.MainActivity;
import org.me.gcu.trafficscotland.classes.RssItem;
import org.me.gcu.trafficscotland.classes.RssItemChannel;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PullParser extends MainActivity {

    private RssItem item = new RssItem();
    private RssItemChannel itemChannel = new RssItemChannel();
    private String url = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private ParserScope scope = ParserScope.Channel;

    public PullParser(String url) {
        this.url = url;
    }

    public ArrayList<RssItem> DataFetch() throws XmlPullParserException {

        Thread theThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL localurl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) localurl.openConnection();
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream inputstream = connection.getInputStream();

                    XmlPullParserFactory pullFactor = XmlPullParserFactory.newInstance();
                    pullFactor.setNamespaceAware(true);
                    XmlPullParser xpp = pullFactor.newPullParser();
                    xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    xpp.setInput(inputstream, null);

                    try {

                        int parserevent = xpp.getEventType();
                        while (parserevent != XmlPullParser.END_DOCUMENT) {
                            switch (parserevent) {
                                case XmlPullParser.START_TAG:
                                    switch (xpp.getName().toLowerCase()) {
                                        case "georss:point":
                                            String geoString = xpp.nextText();
                                            if (geoString.isEmpty()) {
//                                                Does Nothing, contains no data
                                            } else {
                                                try {
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            break;
                                        case "channel":
                                            // Do nothing
                                            scope = ParserScope.Channel;
                                            Log.i("tagLog", "channel");
                                            break;
                                        case "item":
                                            scope = ParserScope.Item;
                                            break;
                                        case "title":
                                            String title = xpp.nextText();
                                            if (scope.equals(ParserScope.Channel)) {
                                                itemChannel.setTitle(title);
                                            } else {
                                                item.setTitle(title);
                                            }
                                            break;

                                        case "description":
                                            String description = xpp.nextText();
                                            if (scope.equals(ParserScope.Channel)) {
                                                itemChannel.setDescription(description);
                                            } else {
                                                item.setDescription(description);
                                            }
                                            break;
                                        case "pubDate":
                                            String pubdate = xpp.nextText();
                                            try {
                                                Date date = new SimpleDateFormat("E, dd MM YYYY HH:mm:ss z").parse(pubdate);
                                                item.setPubDate(date);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case XmlPullParser.END_TAG:
                                    if (xpp.getName().toLowerCase().equalsIgnoreCase("item") && scope == ParserScope.Item) {
                                        item.setDescription(item.getDescription().replaceAll("<br />", "\\\n"));

                                        itemChannel.addItemList(item);
                                        item = new RssItem();
                                        scope = ParserScope.Channel;
                                    }
                                    break;
                                default:
                                    break;
                            }
                            parserevent = xpp.next();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        theThread.start();
        return itemChannel.getChannelItems();
    }

    public enum ParserScope {
        Channel,
        Item,
    }
}
