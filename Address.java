package Shahaf_Einav;

public class Address {
    private String addressStreet;
    private int addressNumber;
    private String addressCity;
    private String addressCountry;

    public Address(String addressStreet, int addressNumber, String addressCity, String addressCountry) {
    this.addressStreet = addressStreet;
    this.addressNumber = addressNumber;
    this.addressCity = addressCity;
    this.addressCountry = addressCountry;
    }
    public String getAddressStreet() {
        return addressStreet;
    }
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
    public int getAddressNumber() {
        return addressNumber;
    }
    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }
    public String getAddressCity() {
        return addressCity;
    }
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
    public String getAddressCountry() {
        return addressCountry;
    }
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }
    public String toString() {
        return  "  Street: " + addressStreet + "\n" +
                "  Street Number: " + addressNumber + "\n" +
                "  City: " + addressCity + "\n" +
                "  Country: " + addressCountry + "\n";
    }
}