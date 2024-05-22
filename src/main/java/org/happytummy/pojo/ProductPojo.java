package org.happytummy.pojo;

public class ProductPojo {
    private String pID;
    private String pName;
    private double pPrice;
    private String pURL;

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpURL() {
        return pURL;
    }

    public void setpURL(String pURL) {
        this.pURL = pURL;
    }

    @Override
    public String toString() {
        return "ProductPojo{" +
                "pID='" + pID + '\'' +
                ", pName='" + pName + '\'' +
                ", pPrice=" + pPrice +
                ", pURL='" + pURL + '\'' +
                '}';
    }
}
