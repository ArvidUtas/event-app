package eventapp.backend.entities;

public class Address {
    private String addressOne;
    private String addressTwo;
    private String postalCode;
    private String city;

    public Address(String addressOne, String addressTwo, String postalCode, String city) {
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
