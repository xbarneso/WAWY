/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainClasses;

/**
 *
 * @author xbarn
 */
public class AdminUser extends User {

    private String NIE;
    private String fullName;
    private String regio;
    private String city;
    private String street;
    private String cp;
    private String username;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public AdminUser(String token, boolean admin, String username) {
        super(token, admin);
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNIE() {
        return NIE;
    }

    public void setNIE(String nIE) {
        NIE = nIE;
    }

}
