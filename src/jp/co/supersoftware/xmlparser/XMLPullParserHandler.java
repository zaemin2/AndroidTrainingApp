package jp.co.supersoftware.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
 
import jp.co.supersoftware.xmlparser.Shop;
 
public class XMLPullParserHandler {
    List<Shop> shops;
    private Shop shop;
    private String text;
 
    public XMLPullParserHandler() {
        shops = new ArrayList<Shop>();
    }
 
    public List<Shop> getEmployees() {
        return shops;
    }
 
    public List<Shop> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
 
            parser.setInput(is, null);
 
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("shop")) {
                        // create a new instance of shop
                        shop = new Shop();
                    }
                    break;
 
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
 
                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("Shop")) {
                        // add shop object to list
                        shops.add(shop);
                    } else if (tagname.equalsIgnoreCase("ShopName")) {
                        shop.setName(text);
                    } else if (tagname.equalsIgnoreCase("ShopIdFront")) {
                        shop.setId(text);
                    } else if (tagname.equalsIgnoreCase("Latitude")) {
                        shop.setLatitude(text);
                    } else if (tagname.equalsIgnoreCase("Longitude")) {
                        shop.setLongitude(text);
                    } else if (tagname.equalsIgnoreCase("MbLargeImg")) {
                        shop.setMbLargeImg(text);
                    } else if (tagname.equalsIgnoreCase("MbSmallImg")) {
                        shop.setMbSmallImg(text);
                    }
                    break;
 
                default:
                    break;
                }
                eventType = parser.next();
            }
 
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return shops;
    }
}
