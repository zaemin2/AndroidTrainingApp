package jp.co.supersoftware.xmlparser;

public class Shop {
	 
    private String ShopName;
    private String ShopIdFront;
    private String MbLargeImg;
    private String MbSmallImg;
    private double Latitude;
    private double Longitude;
 
    public String getName() {
        return ShopName;
    }
 
    public void setName(String name) {
        this.ShopName = name;
    }
 
    public String getId() {
        return ShopIdFront;
    }
 
    public void setId(String id) {
        this.ShopIdFront = id;
    }
 
    public String getMbLargeImg() {
        return MbLargeImg;
    }
 
    public void setMbLargeImg(String img) {
        this.MbLargeImg = img;
    }
    
    public String getMbSmallImg() {
        return MbSmallImg;
    }
 
    public void setMbSmallImg(String img) {
        this.MbSmallImg = img;
    }
 
    public double getLatitude() {
        return Latitude;
    }
 
    public void setLatitude(String Latitude) {
        this.Latitude = Double.parseDouble(Latitude);
    }
    
    public double getLongitude() {
        return Longitude;
    }
 
    public void setLongitude(String Longitude) {
        this.Longitude = Double.parseDouble(Longitude);
    }
 
    @Override
    public String toString() {
        return ShopName + "\n" + ShopIdFront + "\n" + MbLargeImg + "\n" 
        		+ MbSmallImg + "\n" + Latitude + "\n" + Longitude;
    }
}
