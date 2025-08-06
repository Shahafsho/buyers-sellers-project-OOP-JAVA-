package Shahaf_Einav;

public class DeluxItem extends Item {
    private String delux;
    private double extraPrice;

    public DeluxItem(String name, double price,Category category,String delux,double extraPrice) {
        super(name,price,category);
        this.delux = delux;
        this.extraPrice = extraPrice;
    }
    public String getDelux() {
        return delux;
    }
    public double getExtraPrice() {
        return extraPrice;
    }
    public void setDelux(String delux) {
        this.delux = delux;
    }
    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }
    public String toString() {
        {
            return super.toString() + "\nDelux: " + delux + "\nExtraPrice: " + extraPrice;
        }
    }
}
