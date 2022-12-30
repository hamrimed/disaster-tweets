package org.mql.microservices.disastertweetsalerter.models;
public class Contact {
    private long id;
    private String country;
    private String email;
    private String phone;

    public Contact(String phone) {
        this.phone = phone;
    }
    public Contact(long id, String country, String email, String phone) {
        this.id = id;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getcountry() {
        return country;
    }
    public void setcountry(String country) {
        this.country = country;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
