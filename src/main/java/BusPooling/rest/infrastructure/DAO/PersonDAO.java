package BusPooling.rest.infrastructure.DAO;

import com.wordnik.swagger.annotations.ApiModel;

import java.util.Date;


public class PersonDAO {
    private String id;
    private String username;

    private String password;

    private String name;

    private String surname;

    private Date birthday;

    private String gender;

    private boolean active = false;

    private String facebookUID;

    private String geoLat;

    private String geoLng;

    public PersonDAO() {
    }

    public PersonDAO(String id,
                     String username,
                     String password,
                     String name,
                     String surname,
                     Date birthdate,
                     String gender,
                     boolean active,
                     String facebookUID,
                     String geoLat,
                     String geoLon,
                     double distance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthdate;
        this.gender = gender;
        this.active = active;
        this.facebookUID = facebookUID;
        this.geoLat = geoLat;
        this.geoLng = geoLon;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public boolean isActive() {
        return active;
    }

    public String getFacebookUID() {
        return facebookUID;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public String getGeoLng() {
        return geoLng;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFacebookUID(String facebookUID) {
        this.facebookUID = facebookUID;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public void setGeoLng(String geoLng) {
        this.geoLng = geoLng;
    }
}
