package pl.shonsu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Address {

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;
    private String street;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;

    public Address() {
    }

    public Address(AddressType addressType, String street, String postalCode, String city) {
        this.addressType = addressType;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressType=" + addressType +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
